package ru.kampaii.bot.data.entities;

import java.util.List;

public class UserEntity {

    private Long id;

    private String username;

    private List<ChatEntity> chats;

    private UserRights rights;

    public UserEntity(Long id, String username, List<ChatEntity> chats, UserRights rights) {
        this.id = id;
        this.username = username;
        this.chats = chats;
        this.rights = rights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ChatEntity> getChats() {
        return chats;
    }

    public void setChats(List<ChatEntity> chats) {
        this.chats = chats;
    }

    public UserRights getRights() {
        return rights;
    }

    public void setRights(UserRights rights) {
        this.rights = rights;
    }
}
