package ru.kampaii.telegram.actions.callbacks;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.exceptions.ChatBotException;

import java.util.Map;

public interface CallbackExecutor<T extends Update> {

    void execute(T update, Map<String,Object> parameters) throws ChatBotException;

}
