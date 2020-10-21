package ru.kampaii.telegram.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.kampaii.telegram.actions","ru.kampaii.telegram.workers","ru.kampaii.telegram.services"})
public class BotConfig {


}
