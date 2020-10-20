package ru.kampaii.telegram.services;

import ru.kampaii.telegram.exceptions.ChatBotException;

import java.util.Map;

public interface UserService {

    void addAdminUser(Integer userId, String username) throws ChatBotException;

    boolean isUserAdmin(Integer userId);

    Map<Integer, String> getAll();

}
