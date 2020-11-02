package ru.kampaii.bot.data.services.impl;

import org.springframework.stereotype.Service;
import ru.kampaii.bot.data.entities.ChatEntity;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.bot.data.services.ChatService;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    private Map<Long, ChatEntity> chats = new HashMap<Long, ChatEntity>();

    public void addChat(Long chatId) throws DataException {
        if(this.chats.containsKey(chatId)){
            throw new DataException("Chat already enabled");
        }
        this.chats.put(chatId, new ChatEntity(chatId,null));
    }

    public void updateChat(Long chatId, String fileId) throws DataException {
        if(!this.chats.containsKey(chatId)){
            throw new DataException("Chat not registered");
        }
        this.chats.get(chatId).setFileId(fileId);
    }

    public void removeChat(Long chatId) {
        this.chats.remove(chatId);
    }
}
