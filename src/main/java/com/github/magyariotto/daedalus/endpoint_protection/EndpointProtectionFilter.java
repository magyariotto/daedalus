package com.github.magyariotto.daedalus.endpoint_protection;

import com.github.magyariotto.daedalus.service.LoginSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static com.github.magyariotto.daedalus.util.Constants.SESSION_ID_COOKIE_NAME;
import static org.codehaus.plexus.util.StringUtils.isBlank;

@Component
@RequiredArgsConstructor
public class EndpointProtectionFilter extends OncePerRequestFilter {
    private final EndpointProtectionProperties endpointProtectionProperties;
    private final AntPathMatcher antPathMatcher;
    private final LoginSessionService loginSessionService;
    private final ErrorResponseSender errorResponseSender;

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {
        if (isProtectedEndpoint(request)) {
            if (!userHasValisSession(request)) {
                errorResponseSender.setErrorResponse(request, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean userHasValisSession(HttpServletRequest request) {
        Optional<UUID> cookieValue = getSessionCoockie(request);

        if (cookieValue.isPresent()) {
            return loginSessionService.findBySessionId(cookieValue.get()).isPresent();
        } else {
            return false;
        }
    }

    private Optional<UUID> getSessionCoockie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(SESSION_ID_COOKIE_NAME))
                .filter(cookie -> !isBlank(cookie.getValue()))
                .filter(cookie -> isUuid(cookie.getValue()))
                .map(cookie -> UUID.fromString(cookie.getValue()))
                .findFirst();
    }

    private boolean isUuid(String value) {
        try {
            UUID.fromString(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isProtectedEndpoint(HttpServletRequest request) {
        return endpointProtectionProperties.getProtectedEndpoint()
                .stream()
                .filter(endpoint -> antPathMatcher.match(endpoint.getPathPattern(), request.getRequestURI()))
                .anyMatch(endpoint -> endpoint.getMethod().equalsIgnoreCase(request.getMethod()));
    }
}
