package ru.kampaii.telegram.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.kampaii.telegram.services.UserService;

@Component
public class RegisterUserCommand extends AbstractCommand {

    private final UserService userService;

    @Autowired
    public RegisterUserCommand(UserService userService) {
        super("reg", "Добавления пользователя в администраторы");
        this.userService = userService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        if(userService.isUserAdmin(user.getId())){
            sendMessage(absSender,chat.getId(),"Пользователь является администратором");
        }
        else{
            sendMessage(absSender,chat.getId(),"Пользователь не является администратором");
        }
    }
}
