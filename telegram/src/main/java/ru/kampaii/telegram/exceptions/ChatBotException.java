package ru.kampaii.telegram.exceptions;

public class ChatBotException extends Exception {
    public ChatBotException(String s) {
        super(s);
    }

    public ChatBotException(String reason, Exception e) {
        super(reason,e);
    }

    public ChatBotException() {

    }
}
