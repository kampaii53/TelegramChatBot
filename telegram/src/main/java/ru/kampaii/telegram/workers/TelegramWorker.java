package ru.kampaii.telegram.workers;

import ru.kampaii.telegram.exceptions.ChatBotException;

public interface TelegramWorker {

    void sendMessage(String message, Long chatId) throws ChatBotException;

}
