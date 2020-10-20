package ru.kampaii.telegram.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.kampaii.telegram.commands","ru.kampaii.telegram.workers","ru.kampaii.telegram.services","ru.kampaii.telegram.utils"})
public class BotConfig {
}
