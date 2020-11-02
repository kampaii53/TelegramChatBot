package ru.kampaii.telegram.actions.updates.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.bot.data.services.ChatService;
import ru.kampaii.telegram.actions.updates.NonCommandUpdateExecutor;

import java.util.List;

@Component
public class AddChatExecutor extends NonCommandUpdateExecutor {

    private static final Logger log = LoggerFactory.getLogger(AddChatExecutor.class);

    private final ChatService chatService;

    public AddChatExecutor(ChatService chatService, ApplicationContext context) {
        super(context);
        this.chatService = chatService;
    }

    @Override
    public boolean applies(Update update) {
        List<User> newChatMembers = update.getMessage().getNewChatMembers();
        if(newChatMembers == null || newChatMembers.size() == 0 ) {
            return false;
        }
        try {
            User botUser = getChatBot().getMe();
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
    public void execute(Update update) {
        Long chatId = update.getMessage().getChat().getId();
        log.debug("Добавляемся в группу {}",chatId);
        try {
            chatService.addChat(chatId);
            getChatBot().sendMessageToChat(chatId,"Всем кукуси я Натуся");
        } catch (DataException e) {
            getChatBot().sendMessageToChat(chatId,"Не удалось добавить чат");
        }
    }
}
