package com.github.magyariotto.daedalus.endpoint_protection;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "endpoint-protection")
@Configuration
@Data
public class EndpointProtectionProperties {
    public List<Endpoint> protectedEndpoint;
}
