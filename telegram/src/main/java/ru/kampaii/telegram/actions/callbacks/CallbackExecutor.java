package ru.kampaii.telegram.actions.callbacks;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.telegram.exceptions.ChatBotException;

public interface CallbackExecutor<T extends Update> {

    void execute(T update) throws ChatBotException, DataException;

}
