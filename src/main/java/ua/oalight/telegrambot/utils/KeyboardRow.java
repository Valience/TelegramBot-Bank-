package ua.oalight.telegrambot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class KeyboardRow {

    public static InlineKeyboardMarkup createKeyboardCurrency() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();

        InlineKeyboardButton usd = new InlineKeyboardButton();
        usd.setText("USD");
        usd.setCallbackData("USD");

        InlineKeyboardButton eur = new InlineKeyboardButton();
        eur.setText("EUR");
        eur.setCallbackData("EUR");

        InlineKeyboardButton pln = new InlineKeyboardButton();
        pln.setText("PLN");
        pln.setCallbackData("PLN");

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton cad = new InlineKeyboardButton();
        cad.setText("CAD");
        cad.setCallbackData("CAD");

        InlineKeyboardButton aud = new InlineKeyboardButton();
        aud.setText("AUD");
        aud.setCallbackData("AUD");

        firstRow.add(usd);
        firstRow.add(eur);
        firstRow.add(pln);

        secondRow.add(cad);
        secondRow.add(aud);

        rows.add(firstRow);
        rows.add(secondRow);

        keyboardMarkup.setKeyboard(rows);

        return keyboardMarkup;
    }
}
