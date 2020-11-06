package ru.kampaii.telegram.utils;

public enum BotMessages {

    CHAT_ADD_MESSAGE("Бот успешно подключен к чату "),
    CHAT_GET_KEY("Отправьте ссылку на файл боту чтобы было откуда читать сообщения для чата ");

    private final String value;

    BotMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
