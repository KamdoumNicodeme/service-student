package dev.hexa.studentservice.config;

import dev.hexa.studentservice.application.annotation.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "dev.hexa.studentservice.application.service",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION, classes = {DomainService.class}
                )
        })
public class DomainConfig {
}
