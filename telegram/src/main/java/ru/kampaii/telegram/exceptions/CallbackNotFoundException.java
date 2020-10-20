package ru.kampaii.telegram.exceptions;

public class CallbackNotFoundException extends ChatBotException {
    public CallbackNotFoundException(String s) {
        super(s);
    }

    public CallbackNotFoundException(String reason, Exception e) {
        super(reason, e);
    }

    public CallbackNotFoundException() {
        super();
    }
}
