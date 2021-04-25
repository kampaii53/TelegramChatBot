package ru.kampaii.bot.configurations;

import org.springframework.boot.SpringBootConfiguration;

@Import(value = ServicesConfiguration.class)
@EnableScheduling
@ComponentScan(basePackages = "ru.kampaii.bot.jobs")
@SpringBootConfiguration
public class AppConfiguration{

    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }
}
