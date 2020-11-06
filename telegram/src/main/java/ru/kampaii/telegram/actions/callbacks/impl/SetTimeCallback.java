package ru.kampaii.telegram.actions.callbacks.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.bot.data.services.ChatService;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.utils.BotAware;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Component
public class SetTimeCallback extends BotAware implements CallbackExecutor {

    private final ChatService chatService;

    public SetTimeCallback(ApplicationContext applicationContext, ChatService chatService) {
        super(applicationContext);
        this.chatService = chatService;
    }

    @Override
    public void execute(Update update, Map parameters) throws ChatBotException {
        Long chatId;
        try {
            chatId = (Long) parameters.get(SetChatFileCallback.CHAT_PARAMETER);
        }
        catch (Exception ex){
            throw new ChatBotException(ex);
        }

        if(chatId == null){
            throw new ChatBotException("Не задан чат");
        }

        LocalTime time = null;
        try {
            time = LocalTime.parse(update.getMessage().getText());
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Некорректный формат даты! Придется повторить процедуру регистрации чата в боте! Начните с удаления бота из чата");
        }

        try {
            chatService.updateChat(chatId,time);
        } catch (DataException e) {
            throw new ChatBotException(e);
        }

        getChatBot().sendMessageToChat(update.getMessage().getChatId(),"Успешно установлено время "+time.toString());
    }
}
