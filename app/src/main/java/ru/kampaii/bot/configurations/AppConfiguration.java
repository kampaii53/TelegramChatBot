package ru.kampaii.bot.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Import(value = ServicesConfiguration.class)
@EnableScheduling
@ComponentScan(basePackages = "ru.kampaii.bot.jobs")
@SpringBootConfiguration
public class AppConfiguration{

    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }
}
