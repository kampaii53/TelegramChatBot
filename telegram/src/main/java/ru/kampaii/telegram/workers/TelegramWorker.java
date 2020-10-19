package ru.kampaii.telegram.workers;

public interface TelegramWorker {

    void sendMessage(String message);
}
