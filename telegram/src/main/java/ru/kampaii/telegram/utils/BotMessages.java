package ru.kampaii.telegram.utils;

public enum BotMessages {

    CHAT_ADD_MESSAGE("Бот успешно подключен к чату ");

    private final String value;

    BotMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
