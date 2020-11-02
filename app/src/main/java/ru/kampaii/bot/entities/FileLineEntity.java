package ru.kampaii.bot.entities;

import ru.kampaii.bot.utils.DateHelper;

import java.time.LocalDateTime;
import java.util.List;

public class FileLineEntity {

    private String chat;

    private boolean isContinuing;

    private LocalDateTime executionDate;

    private String message;

    private String adresate;

    public FileLineEntity(String chat, boolean isContinuing, LocalDateTime executionDate, String message, String adresate) {
        this.chat = chat;
        this.isContinuing = isContinuing;
        this.executionDate = executionDate;
        this.message = message;
        this.adresate = adresate;
    }

    public FileLineEntity(List<Object> values){
        this.chat = getStringValue(values,0);
        this.isContinuing = getStringValue(values,1).equals("Да");
        this.executionDate = DateHelper.getDateTime(getStringValue(values,2));
        this.message = getStringValue(values,3);
        this.adresate = getStringValue(values,4);
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

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
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

    private String getStringValue(List<Object> object,int index){
        try{
            return object.get(index).toString();
        }
        catch (Exception ex){
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
}
