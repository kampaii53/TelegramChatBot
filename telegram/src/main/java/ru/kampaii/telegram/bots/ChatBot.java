package ru.kampaii.telegram.bots;


import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class ChatBot extends TelegramLongPollingCommandBot {

    String token = "1378282874:AAEazWiv4UsjAMPxyEfX_25Aw7s8t5siEeM";
    String name = "PlayReminderBot";

    public ChatBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        System.out.println(1);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        updates.forEach(update -> onUpdateReceived(update));
    }
}

