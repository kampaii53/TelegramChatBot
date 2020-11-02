package ru.kampaii.bot.data.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.kampaii.bot.data.services")
public class DataConfig {
}
