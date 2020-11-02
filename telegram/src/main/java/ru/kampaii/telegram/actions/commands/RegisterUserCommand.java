package ru.kampaii.telegram.actions.commands;

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
import ru.kampaii.bot.data.entities.UserRights;
import ru.kampaii.bot.data.services.UserService;
import ru.kampaii.telegram.actions.callbacks.impl.RegisterUserCallback;
import ru.kampaii.telegram.services.CallbackService;

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
        if(!userService.hasRights(user.getId(),UserRights.ADMIN)) {
            sendMessage(absSender,chat.getId(),"Пользователь не является администратором");
            return;
        }

        SendMessage reply = new SendMessage(chat.getId(),"Выберите пользователя для добавления в администраторы бота");
        reply.setReplyMarkup(new ForceReplyKeyboard());
        try {
            Message message = absSender.execute(reply);
            callbackService.registerCallback(message.getMessageId(), RegisterUserCallback.class);
        } catch (TelegramApiException e) {
            log.error("Не удалось отправить запрос на добавление пользователя",e);
        }
    }
}
