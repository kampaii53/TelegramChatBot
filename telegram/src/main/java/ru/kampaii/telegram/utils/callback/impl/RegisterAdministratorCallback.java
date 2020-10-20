package ru.kampaii.telegram.utils.callback.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.utils.callback.Executor;

@Component
public class RegisterAdministratorCallback implements Executor<Update> {

    @Override
    public void execute(Update object) {
        System.out.println(1);
    }
}
