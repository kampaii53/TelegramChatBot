package ru.kampaii.bot.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kampaii.bot.data.entities.ChatEntity;
import ru.kampaii.bot.data.services.ChatService;
import ru.kampaii.bot.entities.FileLineEntity;
import ru.kampaii.gdocs.services.GoogleSheetsService;
import ru.kampaii.telegram.bots.ChatBot;

import java.io.IOException;
import java.util.List;

@Component
public class ReadFileJob {

    private static final Logger log = LoggerFactory.getLogger(ReadFileJob.class);

    private final GoogleSheetsService googleSheetsService;

    private final ChatBot chatBot;

    private final ChatService chatService;

    @Autowired
    public ReadFileJob(GoogleSheetsService googleSheetsService, ChatBot chatBot, ChatService chatService) {
        this.googleSheetsService = googleSheetsService;
        this.chatBot = chatBot;
        this.chatService = chatService;
    }

    @Scheduled(fixedDelay = 10000)
    public void execute() throws IOException {
        log.debug("ReadFileJob executes");

        List<ChatEntity> chats = chatService.getActiveChats();

        for (ChatEntity chat : chats) {
            List<List<Object>> values = googleSheetsService.getSheetValue(chat.getFileId());

            for (int i = 1; i < values.size(); i++) {
                FileLineEntity line = new FileLineEntity(values.get(i));

                chatBot.sendMessageToChat(chat.getId(),"Нашел сообщение: "+line.toString());
            }
        }


    }
}
