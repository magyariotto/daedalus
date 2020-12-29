package com.github.magyariotto.daedalus.endpoint_protection;

import lombok.Data;

@Data
public class Endpoint {
    private String pathPattern;
    private String method;
}
