package ru.kampaii.telegram.services;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.utils.callback.CallbackExecutor;

public interface CallbackService {

    void registerCallback(Integer messageId, Class<? extends CallbackExecutor> executorClass);

    void executeCallback(Integer messageId, Update update) throws ChatBotException;
}
