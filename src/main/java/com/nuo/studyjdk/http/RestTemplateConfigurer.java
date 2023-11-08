package com.nuo.studyjdk.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfigurer {

    @Autowired
    public RestTemplateBuilder restTemplateBuilder;

    @Value("${http.rest.connection.timeout:30000}")
    private Integer connTimeout;

    @Value("${http.rest.read.timeout:180000}")
    private Integer readTimeout;

    @Value("${http.rest.long-connection.timeout:30000}")
    private Integer longConnTimeout;

    @Value("${http.rest.long-read.timeout:180000}")
    private Integer longReadTimeout;

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(connTimeout)).setReadTimeout(Duration.ofSeconds(readTimeout)).build();
    }
}
