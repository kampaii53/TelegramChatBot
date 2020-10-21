package ru.kampaii.telegram.actions.updates.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.actions.updates.NonCommandUpdateExecutor;
import ru.kampaii.telegram.bots.ChatBot;
import ru.kampaii.telegram.exceptions.CallbackNotFoundException;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.services.CallbackService;

@Component
public class ReplyCallbackExecutor implements NonCommandUpdateExecutor {

    private static final Logger log = LoggerFactory.getLogger(ReplyCallbackExecutor.class);

    private final CallbackService callbackService;

    @Autowired
    public ReplyCallbackExecutor(CallbackService callbackService) {
        this.callbackService = callbackService;
    }

    @Override
    public boolean applies(Update update,ChatBot bot) {
        try{
            return update.getMessage().getReplyToMessage() != null;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public void execute(Update update, ChatBot bot) {
        // если это у нас ответ на сообщение, значит дергаем с коллбэками
        if(update.getMessage().getReplyToMessage() != null) {
            try {
                callbackService.executeCallback(update.getMessage().getReplyToMessage().getMessageId(), update);
            } catch (CallbackNotFoundException ex) {
                bot.sendMessageToChat(update.getMessage().getChatId(), "Не найдено обработчика ответа для вашего сообщения");
            } catch (ChatBotException | NullPointerException e) {
                log.error("Произошло исключение при запуске колбэка", e);
                bot.sendMessageToChat(update.getMessage().getChatId(), "Произошло непредвиденное исключение");
            }
        }
    }
}
