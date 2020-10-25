package ru.kampaii.bot.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.kampaii.gdocs.config.GDocsConfiguration;
import ru.kampaii.telegram.config.BotConfig;

@Configuration
@EnableWebMvc
@Import({BotConfig.class, GDocsConfiguration.class})
@EnableScheduling
@ComponentScan(basePackages = "ru.kampaii.bot.jobs")
public class AppConfiguration{
}
