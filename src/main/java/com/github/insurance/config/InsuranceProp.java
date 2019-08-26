package com.github.insurance.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "insurance")
@Data
public class InsuranceProp {

    private String serviceUrl;

    private String casUrlPrefix;

    private String casLoginUrl;

    private String casLogoutUrl;

    private String casFailureUrl;
}
