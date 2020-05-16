package com.altimetrik.playground.covidtracker.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "covid.tracking.api")
@Getter
@Setter
public class CovidTrackerApiConfig {
    private String baseUrl;
    private String us;
    private String state;
}
