package ru.kampaii.telegram.utils;

import org.springframework.context.ApplicationContext;
import ru.kampaii.telegram.bots.ChatBot;

public abstract class BotAware {

    private ChatBot chatBot;

    private ApplicationContext applicationContext;

    public BotAware(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected ChatBot getChatBot(){
        if(this.chatBot == null){
            this.chatBot = applicationContext.getBean(ChatBot.class);
        }

        return this.chatBot;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
