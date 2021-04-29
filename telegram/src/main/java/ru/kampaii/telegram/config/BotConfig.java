package ru.kampaii.telegram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.kampaii.bot.data.services.UserService;
import ru.kampaii.telegram.actions.commands.AbstractCommand;
import ru.kampaii.telegram.actions.updates.NonCommandUpdateExecutor;
import ru.kampaii.telegram.bots.ChatBot;
import ru.kampaii.telegram.services.CallbackService;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"ru.kampaii.telegram.actions", "ru.kampaii.telegram.services"})
public class BotConfig {

    @Bean
    public ChatBot getChatBot(
            List<AbstractCommand> commandList,
            CallbackService callbackService,
            UserService userService,
            List<NonCommandUpdateExecutor> nonCommandUpdateExecutors
    ) throws TelegramApiException {

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        ChatBot bot = new ChatBot(
                new DefaultBotOptions(), commandList, callbackService, userService, nonCommandUpdateExecutors);
        botsApi.registerBot(bot);
        return bot;
    }
}
