package ru.kampaii.bot.data.services.impl;

import org.springframework.stereotype.Service;
import ru.kampaii.bot.data.entities.UserEntity;
import ru.kampaii.bot.data.entities.UserRights;
import ru.kampaii.bot.data.exceptions.DataException;
import ru.kampaii.bot.data.services.UserService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Map<Long,UserEntity> users;

    public UserServiceImpl() {
        this.users = new HashMap<Long, UserEntity>();

        users.put(699917602L,new UserEntity(699917602L,"Kirill Kuznetsov",null,UserRights.ADMIN));
        users.put(382798906L,new UserEntity(382798906L,"Sergey Grishin",null,UserRights.ADMIN));
    }

    public void add(UserEntity user) throws DataException {
        if(this.users.containsKey(user.getId())){
            throw new DataException("User already exists");
        }

        users.put(user.getId(),user);
    }

    public Collection<UserEntity> getAll() {
        return users.values();
    }

    public UserEntity get(Long id) {
        return users.get(id);
    }

    public boolean hasRights(Long userId, UserRights rights) {
        if(rights == null || userId == null){
            return false;
        }

        UserEntity user = get(userId);

        if(user == null){
            return false;
        }

        return user.getRights().getOrder() >= rights.getOrder();
    }
}
