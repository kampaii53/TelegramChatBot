package ru.kampaii.telegram.actions.callbacks.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.bot.data.services.ChatService;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.utils.BotAware;

import java.util.Map;

@Component
public class SetChatFileCallback extends BotAware implements CallbackExecutor<Update> {

    public static final String CHAT_PARAMETER = "chat";

    private static final Logger log = LoggerFactory.getLogger(SetChatFileCallback.class);

    private final ChatService chatService;

    public SetChatFileCallback(ApplicationContext applicationContext, ChatService chatService) {
        super(applicationContext);
        this.chatService = chatService;
    }

    @Override
    public void execute(Update update, Map<String,Object> parameters) throws ChatBotException{
        Long chatId;

        try{
            chatId = (Long) parameters.get(CHAT_PARAMETER);
        }
        catch (Exception e){
            throw new ChatBotException(e);
        }

        if(chatId == null){
            throw new ChatBotException("Не найден идентификатор чата для ответа");
        }

        String key = update.getMessage().getText();

        try {
            chatService.updateChat(chatId,key);
        } catch (DataException e) {
            throw new ChatBotException(e);
        }

        getChatBot().sendMessageToChat(update.getMessage().getChatId(),"Успешно добавил ключ "+key+" к чату "+chatId);
    }
}
