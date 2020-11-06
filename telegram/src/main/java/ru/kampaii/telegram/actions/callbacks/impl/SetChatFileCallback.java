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
import ru.kampaii.telegram.utils.BotMessages;

@Component
public class SetChatFileCallback extends BotAware implements CallbackExecutor<Update> {

    private static final Logger log = LoggerFactory.getLogger(SetChatFileCallback.class);

    private final ChatService chatService;

    public SetChatFileCallback(ApplicationContext applicationContext, ChatService chatService) {
        super(applicationContext);
        this.chatService = chatService;
    }

    @Override
    public void execute(Update update) throws ChatBotException{
        Long chatId = Long.parseLong(update.getMessage().getReplyToMessage().getText().replace(BotMessages.CHAT_GET_KEY.getValue(),""));
        String key = parseKey(update.getMessage().getText());

        try {
            chatService.updateChat(chatId,key);
        } catch (DataException e) {
            throw new ChatBotException(e);
        }

        getChatBot().sendMessageToChat(update.getMessage().getChatId(),"Успешно добавил ключ "+key+" к чату "+chatId);
    }

    private String parseKey(String url){
        log.debug("Пытаюсь разобрать URL {}",url);
        String result = url.replace("https://docs.google.com/spreadsheets/d/","");

        result = result.substring(0,result.indexOf("/edit"));
        return result;
    }
}
