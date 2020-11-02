package ru.kampaii.bot.data.entities;

public enum UserRights {

    USER(0),
    ADMIN(1);

    private final int order;

    UserRights(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
