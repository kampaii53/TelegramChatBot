package ru.kampaii.bot.data.entities;

public class ChatEntity {

    private Long id;

    private String fileId;

    public ChatEntity(Long id, String fileId) {
        this.id = id;
        this.fileId = fileId;
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
}
