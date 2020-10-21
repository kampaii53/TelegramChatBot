package ru.kampaii.telegram.actions.updates.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kampaii.telegram.actions.updates.NonCommandUpdateExecutor;
import ru.kampaii.telegram.bots.ChatBot;

import java.util.List;

@Component
public class AddChatExecutor implements NonCommandUpdateExecutor {

    private static final Logger log = LoggerFactory.getLogger(AddChatExecutor.class);

    @Override
    public boolean applies(Update update,ChatBot bot) {
        List<User> newChatMembers = update.getMessage().getNewChatMembers();
        if(newChatMembers == null || newChatMembers.size() == 0) {
            return false;
        }
        try {
            User botUser = bot.getMe();
            for (User newChatMember : newChatMembers) {
                if(newChatMember.getId().equals(botUser.getId())){
                    return true;
                }
            }
            return false;
        } catch (TelegramApiException e) {
            log.error("Не сумел получить данные бота");
            return false;
        }

    }

    @Override
    public void execute(Update update, ChatBot bot) {

    }
}
