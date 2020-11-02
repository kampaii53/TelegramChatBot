package ru.kampaii.bot.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import(value = ServicesConfiguration.class)
@EnableScheduling
@ComponentScan(basePackages = "ru.kampaii.bot.jobs")
public class AppConfiguration{
}
