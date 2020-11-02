package ru.kampaii.telegram.actions.updates;

import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.utils.BotAware;

public abstract class NonCommandUpdateExecutor extends BotAware {

    public NonCommandUpdateExecutor(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public abstract boolean applies(Update update);

    public abstract void execute(Update update);
}
