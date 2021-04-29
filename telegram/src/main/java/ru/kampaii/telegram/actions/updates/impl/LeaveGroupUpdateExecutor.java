package ru.kampaii.telegram.actions.updates.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kampaii.bot.data.services.ChatService;
import ru.kampaii.telegram.actions.updates.NonCommandUpdateExecutor;

@Component
public class LeaveGroupUpdateExecutor extends NonCommandUpdateExecutor {

    private static final Logger log = LoggerFactory.getLogger(LeaveGroupUpdateExecutor.class);

    private final ChatService chatService;

    public LeaveGroupUpdateExecutor(ApplicationContext applicationContext, ChatService chatService) {
        super(applicationContext);
        this.chatService = chatService;
    }

    @Override
    public boolean applies(Update update) {
        User left = update.getMessage().getLeftChatMember();

        if(left == null){
            return false;
        }

        try {
            return left.getId().equals(getChatBot().getMe().getId());
        } catch (TelegramApiException e) {
            log.error("Не удалось получить данные бота",e);
            return false;
        }
    }

    @Override
    public void execute(Update update) {
        Long user = update.getMessage().getFrom().getId();

        Long chatId = update.getMessage().getChat().getId();

        chatService.removeChat(chatId);

        getChatBot().sendMessageToUser(user,"Успешно удален чат "+chatId);
    }
}
