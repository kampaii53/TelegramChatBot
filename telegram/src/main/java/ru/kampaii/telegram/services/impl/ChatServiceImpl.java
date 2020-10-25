package ru.kampaii.telegram.services.impl;

import org.springframework.stereotype.Service;
import ru.kampaii.telegram.services.ChatService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {

    private Set<Long> enabledChats = new HashSet<>();

    @Override
    public void addChat(Long chatId) {
        this.enabledChats.add(chatId);
    }

    @Override
    public void removeChat(Long chatId) {
        this.enabledChats.remove(chatId);
    }

    @Override
    public boolean isChatEnabled(Long chatId) {
        return this.enabledChats.contains(chatId);
    }

    @Override
    public Collection<Long> getChats() {
        return enabledChats;
    }
}
