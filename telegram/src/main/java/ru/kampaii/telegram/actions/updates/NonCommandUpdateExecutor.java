package ru.kampaii.telegram.actions.updates;

import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kampaii.telegram.bots.ChatBot;

public abstract class NonCommandUpdateExecutor {

    private ChatBot chatBot;

    private ApplicationContext applicationContext;

    public NonCommandUpdateExecutor(ApplicationContext context){
        this.applicationContext = context;
    }

    public abstract boolean applies(Update update);

    public abstract void execute(Update update);

    protected ChatBot getChatBot(){
        if(this.chatBot == null){
            this.chatBot = applicationContext.getBean(ChatBot.class);
        }

        return this.chatBot;
    }
}
