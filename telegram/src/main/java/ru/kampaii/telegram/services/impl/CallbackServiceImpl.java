package ru.kampaii.telegram.services.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.actions.callbacks.CallbackEntry;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;
import ru.kampaii.telegram.exceptions.CallbackNotFoundException;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.services.CallbackService;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CallbackServiceImpl implements CallbackService {

    private Map<AbstractMap.SimpleEntry<Long,Integer>, CallbackEntry> callbacks;

    private Map<Class<?>, CallbackExecutor> executors;

    public CallbackServiceImpl(Collection<CallbackExecutor> executors) {
        this.callbacks = new HashMap<>();
        this.executors = new HashMap<>();
        executors.forEach(executor -> this.executors.put(executor.getClass(), executor));

    }

    @Override
    public void registerCallback(Long chatId, Integer messageId, Class<? extends CallbackExecutor> executorClass,Map<String,Object> parameters) {
        this.callbacks.put(new AbstractMap.SimpleEntry<>(chatId,messageId),new CallbackEntry(executorClass,chatId,messageId,parameters));
    }

    @Override
    public void executeCallback(Long chatId, Integer messageId, Update update) throws ChatBotException {
        AbstractMap.SimpleEntry<Long, Integer> key = new AbstractMap.SimpleEntry<>(chatId, messageId);
        CallbackEntry callback = callbacks.get(key);
        callbacks.remove(key);

        if(callback == null){
            throw new CallbackNotFoundException();
        }

        CallbackExecutor callbackExecutor = executors.get(callback.getExecutorClass());

        if(callbackExecutor == null){
            throw new ChatBotException("Не найдено обработчика колбэка "+callback.getExecutorClass().getName());
        }

        callbackExecutor.execute(update,callback.getProperties());
    }
}
