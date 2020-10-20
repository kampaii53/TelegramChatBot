package ru.kampaii.telegram.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class StartCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(StartCommand.class);

    public StartCommand() {
        super("start", "start using app");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendMessage(absSender,chat.getId(),"Приложение запущено");
    }

}
