package ru.kampaii.bot.data.services;

import ru.kampaii.bot.data.entities.ChatEntity;
import ru.kampaii.bot.data.exceptions.DataException;

import java.util.List;

public interface ChatService {

    void addChat(Long chatId) throws DataException;

    void updateChat(Long chatId,String fileId) throws DataException;

    List<ChatEntity> getActiveChats();

    void removeChat(Long chatId);
}
