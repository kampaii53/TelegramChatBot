package ru.kampaii.bot.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Import(value = ServicesConfiguration.class)
@EnableScheduling
@SpringBootApplication(scanBasePackages = "ru.kampaii.bot.jobs")
public class AppConfiguration{

    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }
}
