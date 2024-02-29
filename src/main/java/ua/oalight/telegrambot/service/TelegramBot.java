package ua.oalight.telegrambot.service;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.oalight.telegrambot.Emoji;
import ua.oalight.telegrambot.config.BotConfig;
import ua.oalight.telegrambot.utils.KeyboardRow;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class TelegramBot extends TelegramLongPollingBot {
    private BotConfig botConfig;
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (message){
                case "/info":
                    sendMessage(chatId, "This is Emoji_Test_Bot since 26/10/2023");
                    break;
                case "/start":
                    startCommand(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/json":
                    handleRequest(chatId, "Select currency");
                    break;
                default:
                    sendMessage(chatId, "Command is unknown. :(");
            }
        }else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            CurrencyJSONService service = new CurrencyJSONService();

            String message;
            try {
                message = service.getResponse(callbackData);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            sendMessage(chatId,message);
        }

    }

    private void handleRequest(long chatId, String selectCurrency) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(selectCurrency);

        InlineKeyboardMarkup markup = KeyboardRow.createKeyboardCurrency();
        message.setReplyMarkup(markup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void startCommand(long chatId, String firstName) {
        String answer = Emoji.BLUSH.getEmoji() + Emoji.SMILE.getEmoji() +firstName+", hello. Nice to meet you!" + Emoji.WINK_EYE.getEmoji();
        String response = EmojiParser.parseToUnicode(answer);
        sendMessage(chatId, response);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }
    @Autowired
    public TelegramBot(BotConfig botConfig) {
        super(botConfig.getBotToken());
        this.botConfig = botConfig;
    }
}
