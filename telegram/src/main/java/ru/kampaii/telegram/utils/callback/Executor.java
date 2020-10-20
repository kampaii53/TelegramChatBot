package ru.kampaii.telegram.utils.callback;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Executor<T extends Update> {

    void execute(T object);

}
