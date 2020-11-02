package ru.kampaii.bot.data.services;

import ru.kampaii.bot.data.exceptions.DataException;

public interface ChatService {

    void addChat(Long chatId) throws DataException;

    void updateChat(Long chatId,String fileId) throws DataException;

    void removeChat(Long chatId);
}
