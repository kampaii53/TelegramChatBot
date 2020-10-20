package ru.kampaii.telegram.services.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.exceptions.CallbackNotFoundException;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.services.CallbackService;
import ru.kampaii.telegram.utils.callback.Executor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CallbackServiceImpl implements CallbackService {

    private Map<Integer,Class<? extends Executor>> callbacks;

    private Map<Class<?>, Executor> executors;

    public CallbackServiceImpl(Collection<Executor> executors) {
        this.callbacks = new HashMap<>();
        this.executors = new HashMap<>();
        executors.forEach(executor -> this.executors.put(executor.getClass(),executor));

    }

    @Override
    public void registerCallback(Integer messageId, Class<? extends Executor> executorClass) {
        this.callbacks.put(messageId,executorClass);
    }

    @Override
    public void executeCallback(Integer messageId, Update update) throws ChatBotException {
        Class<? extends Executor> callbackClass = callbacks.get(messageId);

        if(callbackClass == null){
            throw new CallbackNotFoundException();
        }

        Executor executor = executors.get(callbackClass);

        if(executor == null){
            throw new ChatBotException("Не найдено обработчика колбэка "+callbackClass.getName());
        }

        executor.execute(update);
    }
}
