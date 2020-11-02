package ru.kampaii.telegram.services.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.telegram.exceptions.CallbackNotFoundException;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.services.CallbackService;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CallbackServiceImpl implements CallbackService {

    private Map<Integer,Class<? extends CallbackExecutor>> callbacks;

    private Map<Class<?>, CallbackExecutor> executors;

    public CallbackServiceImpl(Collection<CallbackExecutor> callbackExecutors) {
        this.callbacks = new HashMap<>();
        this.executors = new HashMap<>();
        callbackExecutors.forEach(callbackExecutor -> this.executors.put(callbackExecutor.getClass(), callbackExecutor));

    }

    @Override
    public void registerCallback(Integer messageId, Class<? extends CallbackExecutor> executorClass) {
        this.callbacks.put(messageId,executorClass);
    }

    @Override
    public void executeCallback(Integer messageId, Update update) throws ChatBotException {
        Class<? extends CallbackExecutor> callbackClass = callbacks.get(messageId);

        if(callbackClass == null){
            throw new CallbackNotFoundException();
        }

        CallbackExecutor callbackExecutor = executors.get(callbackClass);

        if(callbackExecutor == null){
            throw new ChatBotException("Не найдено обработчика колбэка "+callbackClass.getName());
        }

        try {
            callbackExecutor.execute(update);
        } catch (DataException e) {
            throw new ChatBotException(e);
        }

        callbacks.remove(messageId);
    }
}
