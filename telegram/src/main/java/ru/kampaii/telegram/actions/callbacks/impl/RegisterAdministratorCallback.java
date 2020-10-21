package ru.kampaii.telegram.actions.callbacks.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.actions.callbacks.CallbackExecutor;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.services.UserService;

@Component
public class RegisterAdministratorCallback implements CallbackExecutor<Update> {
    private static final Logger log = LoggerFactory.getLogger(RegisterAdministratorCallback.class);

    private final UserService userService;

    @Autowired
    public RegisterAdministratorCallback(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(Update update) throws ChatBotException {
        if(update.getMessage().getContact() == null){
            throw new ChatBotException("Не заполнен контакт");
        }

        Contact contact = update.getMessage().getContact();

        userService.addAdminUser(contact.getUserID(),getUserNameFromContact(contact));

        log.debug("Пользователь {} добавлен в администраторы",contact.getUserID());
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
