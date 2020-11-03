package ru.kampaii.telegram.actions.updates.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kampaii.bot.data.services.ChatService;
import ru.kampaii.telegram.actions.callbacks.impl.SetChatFileCallback;
import ru.kampaii.telegram.actions.updates.NonCommandUpdateExecutor;
import ru.kampaii.telegram.services.CallbackService;
import ru.kampaii.telegram.utils.BotMessages;

import java.util.List;

@Component
public class AddChatUpdateExecutor extends NonCommandUpdateExecutor {

    private static final Logger log = LoggerFactory.getLogger(AddChatUpdateExecutor.class);

    private final ChatService chatService;

    private final CallbackService callbackService;

    public AddChatUpdateExecutor(ChatService chatService, ApplicationContext context, CallbackService callbackService) {
        super(context);
        this.chatService = chatService;
        this.callbackService = callbackService;
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
            getChatBot().sendMessageToChat(chatId,"Всем привет!");

            SendMessage message = new SendMessage(update.getMessage().getFrom().getId().toString(),BotMessages.CHAT_ADD_MESSAGE.getValue() +chatId);
            message.setReplyMarkup(new ForceReplyKeyboard());
            Message result = getChatBot().execute(message);

            callbackService.registerCallback(result.getMessageId(), SetChatFileCallback.class);
        } catch (Exception e) {
            getChatBot().sendMessageToUser(update.getMessage().getFrom().getId(),"Произошла ошибка: "+e.getMessage());
            log.error("Не удалось подключить бота к чату",e);
        }
    }
}
