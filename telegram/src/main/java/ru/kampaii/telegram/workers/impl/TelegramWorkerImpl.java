package ru.kampaii.telegram.workers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.kampaii.telegram.bots.ChatBot;
import ru.kampaii.telegram.commands.AbstractCommand;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.services.CallbackService;
import ru.kampaii.telegram.workers.TelegramWorker;

import java.util.List;

@Component
public class TelegramWorkerImpl implements TelegramWorker {

    private static final Logger log = LoggerFactory.getLogger(TelegramWorkerImpl.class);

    ChatBot bot;

    public TelegramWorkerImpl(List<AbstractCommand> commandList, CallbackService callbackService) throws TelegramApiRequestException {
        log.info("Стартую TelegramWorker");
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        ChatBot bot = new ChatBot(botOptions,commandList,callbackService);
        botsApi.registerBot(bot);
        this.bot = bot;
        log.info("TelegramWorker запущен");
    }

    @Override
    public void sendMessage(String message, Long chatId) throws ChatBotException {
        try {
            bot.execute(new SendMessage(chatId,message));
        } catch (TelegramApiException e) {
            throw new ChatBotException("Не удалось отправить сообщение",e);
        }
    }
}
