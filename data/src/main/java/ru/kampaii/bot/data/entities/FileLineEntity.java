package ru.kampaii.bot.data.entities;


import ru.kampaii.bot.data.utils.DateHelper;

import java.time.LocalDate;
import java.util.List;

public class FileLineEntity {

    private String chat;

    private boolean isContinuing;

    private LocalDate executionDate;

    private String theme;

    private String message;

    private String adresate;

    public FileLineEntity(String chat, boolean isContinuing, LocalDate executionDate, String theme, String message, String adresate) {
        this.chat = chat;
        this.isContinuing = isContinuing;
        this.executionDate = executionDate;
        this.theme = theme;
        this.message = message;
        this.adresate = adresate;
    }

    public FileLineEntity(List<Object> values) {
        this.chat = getStringValue(values, 0);
        this.isContinuing = "Да".equalsIgnoreCase(getStringValue(values, 1));
        this.executionDate = DateHelper.getDate(getStringValue(values, 2));
        this.theme = getStringValue(values, 3);
        this.message = getStringValue(values, 4);
        this.adresate = getStringValue(values, 5);
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public boolean isContinuing() {
        return isContinuing;
    }

    public void setContinuing(boolean continuing) {
        isContinuing = continuing;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public String getAdresate() {
        return adresate;
    }

    public void setAdresate(String adresate) {
        this.adresate = adresate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String constructMessage() {
        return this.theme == null ? message : "Тема: " + theme + ":\n" + message;
    }

    private String getStringValue(List<Object> object, int index) {
        try {
            return object.get(index).toString();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "FileLineEntity{" +
                "chat='" + chat + '\'' +
                ", isContinuing=" + isContinuing +
                ", executionDate=" + executionDate +
                ", message='" + message + '\'' +
                ", adresate='" + adresate + '\'' +
                '}';
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}