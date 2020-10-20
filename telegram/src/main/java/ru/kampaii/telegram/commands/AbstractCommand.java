package ru.kampaii.telegram.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractCommand extends BotCommand {

    private static final Logger log = LoggerFactory.getLogger(AbstractCommand.class);

    public AbstractCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    void sendMessage(AbsSender absSender, long chatId, String text) {
        try {
            absSender.execute(new SendMessage(chatId, text));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
