package ru.kampaii.telegram.utils.callback;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.exceptions.ChatBotException;

public interface CallbackExecutor<T extends Update> {

    void execute(T object) throws ChatBotException;

}
