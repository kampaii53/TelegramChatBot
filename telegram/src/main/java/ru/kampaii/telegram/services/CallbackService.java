package ru.kampaii.telegram.services;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;
import ru.kampaii.telegram.exceptions.ChatBotException;

import java.util.Map;

public interface CallbackService {

    void registerCallback(Long chatId, Integer messageId, Class<? extends CallbackExecutor> executorClass, Map<String,Object> parameters);

    void executeCallback(Long chatId, Integer messageId, Update update) throws ChatBotException;
}
