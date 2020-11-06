package ru.kampaii.telegram.actions.callbacks;

import java.util.Map;

public class CallbackEntry {

    private Class<? extends CallbackExecutor> executorClass;

    private Long chatId;

    private Integer messageId;

    private Map<String,Object> properties;

    public CallbackEntry(Class<? extends CallbackExecutor> executorClass, Long chatId, Integer messageId, Map<String, Object> properties) {
        this.executorClass = executorClass;
        this.chatId = chatId;
        this.messageId = messageId;
        this.properties = properties;
    }

    public Class<? extends CallbackExecutor> getExecutorClass() {
        return executorClass;
    }

    public void setExecutorClass(Class<? extends CallbackExecutor> executorClass) {
        this.executorClass = executorClass;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
