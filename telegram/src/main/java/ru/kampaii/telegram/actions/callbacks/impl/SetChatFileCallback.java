package ru.kampaii.telegram.actions.callbacks.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.bot.data.services.ChatService;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.services.CallbackService;
import ru.kampaii.telegram.utils.BotAware;

import java.util.Collections;
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

        CallbackService callbackService = getApplicationContext().getBean(CallbackService.class);
        if(callbackService == null){
            throw new ChatBotException("Не сумел получить доступ к сервису колбэков");
        }

        Message message = null;
        try {
            message = createTimeMessage(update.getMessage().getChatId());
        } catch (TelegramApiException e) {
            throw new ChatBotException(e);
        }

        callbackService.registerCallback(update.getMessage().getChatId(),message.getMessageId(),SetTimeCallback.class, Collections.singletonMap(CHAT_PARAMETER,chatId));
    }

    private Message createTimeMessage(Long chatId) throws TelegramApiException {
        SendMessage message = new SendMessage(chatId,"Теперь необходимо установить время в формате ЧЧ:ММ");
        message.setReplyMarkup(new ForceReplyKeyboard());

        return getChatBot().execute(message);
    }
}
