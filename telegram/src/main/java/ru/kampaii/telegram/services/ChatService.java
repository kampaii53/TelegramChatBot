package ru.kampaii.telegram.services;

import java.util.Collection;

public interface ChatService {

    void addChat(Long chatId);

    void removeChat(Long chatId);

    boolean isChatEnabled(Long chatId);

    Collection<Long> getChats();
}
