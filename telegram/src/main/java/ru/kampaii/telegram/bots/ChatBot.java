package ru.kampaii.telegram.bots;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kampaii.bot.data.entities.UserRights;
import ru.kampaii.bot.data.services.UserService;
import ru.kampaii.telegram.actions.commands.AbstractCommand;
import ru.kampaii.telegram.actions.updates.NonCommandUpdateExecutor;
import ru.kampaii.telegram.services.CallbackService;

import java.util.List;

public class ChatBot extends TelegramLongPollingCommandBot {

    private static final Logger log = LoggerFactory.getLogger(ChatBot.class);

    private static final String token = "1378282874:AAEazWiv4UsjAMPxyEfX_25Aw7s8t5siEeM";
    private static final String name = "PlayReminderBot";

    private final CallbackService callbackService;
    private final UserService userService;
    private final List<NonCommandUpdateExecutor> nonCommandUpdateExecutors;

    public ChatBot(DefaultBotOptions botOptions, List<AbstractCommand> commands, CallbackService callbackService, UserService userService, List<NonCommandUpdateExecutor> nonCommandUpdateExecutors) {
        super(botOptions);
        this.callbackService = callbackService;
        this.userService = userService;
        this.nonCommandUpdateExecutors = nonCommandUpdateExecutors;

        for (AbstractCommand command : commands) {
            this.register(command);
            log.debug("command {} registered",command.getCommandIdentifier());
        }

        this.registerDefaultAction(((absSender, message) -> log.debug("defaultAction on {}",message)));
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if(!userService.hasRights(update.getMessage().getFrom().getId(), UserRights.USER)){
            sendMessageToChat(update.getMessage().getChatId(),"Пользователь не зарегистрирован");
            return;
        }

        for (NonCommandUpdateExecutor executor : nonCommandUpdateExecutors) {
            if(executor.applies(update)){
                executor.execute(update);
            }
        }
    }

    public void sendMessageToChat(Long chatId,String message){
        try {
            execute(new SendMessage(chatId,message));
            log.debug("message to {} : {}",chatId,message);
        } catch (TelegramApiException e) {
            log.error("Не удалось отправить сообщение пользователю",e);
        }
    }

}

