package ru.kampaii.bot.data.services;

import ru.kampaii.bot.data.entities.UserEntity;
import ru.kampaii.bot.data.entities.UserRights;
import ru.kampaii.bot.data.exceptions.DataException;

import java.util.Collection;

public interface UserService {

    void add(UserEntity user) throws DataException;

    Collection<UserEntity> getAll();

    UserEntity get(Long id);

    boolean hasRights(Long userId,UserRights rights);
}
