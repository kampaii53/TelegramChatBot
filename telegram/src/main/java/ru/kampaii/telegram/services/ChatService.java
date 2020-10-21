package ru.kampaii.telegram.services;

public interface ChatService {

    void addChat(Long chatId);

    void removeChat(Long chatId);

    boolean isChatEnabled(Long chatId);
}
