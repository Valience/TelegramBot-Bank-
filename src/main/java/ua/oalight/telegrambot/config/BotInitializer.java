package ua.oalight.telegrambot.config;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ua.oalight.telegrambot.service.TelegramBot;
@Component
public class BotInitializer {

    private final TelegramBot TELEGRAM_BOT;

    public BotInitializer(TelegramBot telegramBot) {
        this.TELEGRAM_BOT = telegramBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        botsApi.registerBot(TELEGRAM_BOT);
    }
}
