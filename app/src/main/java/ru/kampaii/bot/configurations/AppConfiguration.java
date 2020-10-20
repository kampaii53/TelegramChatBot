package ru.kampaii.bot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.kampaii.telegram.config.BotConfig;

@Configuration
@EnableWebMvc
@Import(BotConfig.class)
public class AppConfiguration{
}
