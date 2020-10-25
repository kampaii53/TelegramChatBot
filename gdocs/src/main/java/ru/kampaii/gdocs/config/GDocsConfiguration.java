package ru.kampaii.gdocs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.kampaii.gdocs.services")
public class GDocsConfiguration {
}
