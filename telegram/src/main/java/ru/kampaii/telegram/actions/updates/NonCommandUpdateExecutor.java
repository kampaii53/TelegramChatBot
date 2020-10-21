package ru.kampaii.telegram.actions.updates;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.bots.ChatBot;

public interface NonCommandUpdateExecutor {

    boolean applies(Update update,ChatBot bot);

    void execute(Update update, ChatBot bot);
}
