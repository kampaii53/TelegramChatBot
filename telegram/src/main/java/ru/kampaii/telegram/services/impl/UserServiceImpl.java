package ru.kampaii.telegram.services.impl;

import org.springframework.stereotype.Service;
import ru.kampaii.telegram.exceptions.ChatBotException;
import ru.kampaii.telegram.services.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Map<Integer,String> users;

    public UserServiceImpl(){
        this.users = new HashMap<>();
        users.put(1,"Test");
    }

    @Override
    public void addAdminUser(Integer userId, String username) throws ChatBotException {
        if(!this.users.containsKey(userId)){
            this.users.put(userId,username);
        }
        else{
            throw new ChatBotException("user "+userId+" is already registered");
        }
    }

    @Override
    public boolean isUserAdmin(Integer userId) {
        return this.users.containsKey(userId);
    }

    @Override
    public Map<Integer, String> getAll() {
        return new HashMap<>(users);
    }
}
