package ru.kampaii.bot.data.services.impl;

import org.springframework.stereotype.Service;
import ru.kampaii.bot.data.entities.ChatEntity;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.bot.data.services.ChatService;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private Map<Long, ChatEntity> chats = new HashMap<Long, ChatEntity>();

    public void addChat(Long chatId) throws DataException {
        if(this.chats.containsKey(chatId)){
            throw new DataException("Chat already enabled");
        }
        this.chats.put(chatId, new ChatEntity(chatId,null, LocalTime.of(9,0)));
    }

    @Override
    public void updateChat(Long chatId, LocalTime time) throws DataException {
        if(!this.chats.containsKey(chatId)){
            throw new DataException("Chat not registered");
        }
        this.chats.get(chatId).setTime(time);
    }

    public void updateChat(Long chatId, String fileId) throws DataException {
        if(!this.chats.containsKey(chatId)){
            throw new DataException("Chat not registered");
        }
        this.chats.get(chatId).setFileId(fileId);
    }

    public List<ChatEntity> getActiveChats() {
        return this.chats.values().stream().filter(chatEntity -> chatEntity.getFileId() != null).collect(Collectors.toList());
    }

    public void removeChat(Long chatId) {
        this.chats.remove(chatId);
    }
}
