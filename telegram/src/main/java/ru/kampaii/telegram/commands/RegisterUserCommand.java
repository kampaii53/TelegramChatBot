package ru.kampaii.telegram.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kampaii.telegram.services.CallbackService;
import ru.kampaii.telegram.services.UserService;
import ru.kampaii.telegram.utils.callback.impl.RegisterAdministratorCallback;

@Component
public class RegisterUserCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(RegisterUserCommand.class);

    private final UserService userService;

    private final CallbackService callbackService;

    @Autowired
    public RegisterUserCommand(UserService userService, CallbackService callbackService) {
        super("reg", "Добавления пользователя в администраторы");
        this.userService = userService;
        this.callbackService = callbackService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        //TODO вынести проверку в аннотации
        if(!userService.isUserAdmin(user.getId())) {
            sendMessage(absSender,chat.getId(),"Пользователь не является администратором");
            return;
        }

        SendMessage reply = new SendMessage(chat.getId(),"Выберите пользователя для добавления в администраторы бота");
        reply.setReplyMarkup(new ForceReplyKeyboard());
        try {
            Message message = absSender.execute(reply);
            callbackService.registerCallback(message.getMessageId(), RegisterAdministratorCallback.class);
        } catch (TelegramApiException e) {
            log.error("Не удалось отправить запрос на добавление пользователя",e);
        }
    }
}
