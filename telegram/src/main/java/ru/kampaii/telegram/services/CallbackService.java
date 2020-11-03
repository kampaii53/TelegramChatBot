package ru.kampaii.telegram.services;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;

public interface CallbackService {

    //TODO fix: messageId уникален только в рамках чата
    void registerCallback(Integer messageId, Class<? extends CallbackExecutor> executorClass);

    void executeCallback(Integer messageId, Update update) throws ChatBotException;
}
