package ru.kampaii.bot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kampaii.bot.data.config.DataConfig;
import ru.kampaii.gdocs.config.GDocsConfiguration;
import ru.kampaii.telegram.config.BotConfig;

@Configuration
@Import({
        BotConfig.class
        , GDocsConfiguration.class
        , DataConfig.class
})
public class ServicesConfiguration {
}
