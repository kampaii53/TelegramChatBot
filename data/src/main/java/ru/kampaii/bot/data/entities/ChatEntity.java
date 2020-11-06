package ru.kampaii.bot.data.entities;

import java.time.LocalTime;

public class ChatEntity {

    private Long id;

    private String fileId;

    private LocalTime time;

    public ChatEntity(Long id, String fileId, LocalTime time) {
        this.id = id;
        this.fileId = fileId;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
