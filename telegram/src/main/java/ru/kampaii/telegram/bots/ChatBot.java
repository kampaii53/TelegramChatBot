package ru.kampaii.telegram.bots;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kampaii.telegram.commands.AbstractCommand;
import ru.kampaii.telegram.exceptions.CallbackNotFoundException;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.services.CallbackService;

import java.util.List;

public class ChatBot extends TelegramLongPollingCommandBot {

    private static final Logger log = LoggerFactory.getLogger(ChatBot.class);

    private static final String token = "1378282874:AAEazWiv4UsjAMPxyEfX_25Aw7s8t5siEeM";
    private static final String name = "PlayReminderBot";
    private final CallbackService callbackService;

    public ChatBot(DefaultBotOptions botOptions, List<AbstractCommand> commands, CallbackService callbackService) {
        super(botOptions);
        this.callbackService = callbackService;

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
        try {
            callbackService.executeCallback(update.getMessage().getReplyToMessage().getMessageId(),update);
        } catch (CallbackNotFoundException ex){
            ex.printStackTrace();
        } catch (ChatBotException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            log.error("this is not a callback");
        }
    }

}

