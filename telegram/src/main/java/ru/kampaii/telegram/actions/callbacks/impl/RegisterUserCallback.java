package ru.kampaii.telegram.actions.callbacks.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.bot.data.entities.UserEntity;
import ru.kampaii.bot.data.entities.UserRights;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.bot.data.services.UserService;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.utils.BotAware;

import java.util.ArrayList;
import java.util.Map;

@Component
public class RegisterUserCallback extends BotAware implements CallbackExecutor<Update> {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserCallback.class);

    private final UserService userService;

    @Autowired
    public RegisterUserCallback(ApplicationContext applicationContext,UserService userService) {
        super(applicationContext);
        this.userService = userService;
    }

    @Override
    public void execute(Update update, Map<String,Object> parameters) throws ChatBotException{
        if(update.getMessage().getContact() == null){
            throw new ChatBotException("Не заполнен контакт");
        }

        Contact contact = update.getMessage().getContact();

        if(userService.get(contact.getUserID()) == null) {
            try {
                userService.add(new UserEntity(contact.getUserID(), getUserNameFromContact(contact), new ArrayList<>(), UserRights.USER));
            } catch (DataException e) {
                throw new ChatBotException(e);
            }
            getChatBot().sendMessageToChat(update.getMessage().getChatId(),"Пользователь успешно зарегистрирован");
        }
        else{
            getChatBot().sendMessageToChat(update.getMessage().getChatId(),"Такой пользователь уже существует");
        }
    }

    private String getUserNameFromContact(Contact contact){
        String result = "";
        if(contact.getFirstName() != null){
            result = contact.getFirstName();
        }
        if(contact.getLastName() != null){
            if(result.length() == 0){
                result = contact.getLastName();
            }
            else{
                result = result + " " +contact.getLastName();
            }
        }
        return result;
    }
}
