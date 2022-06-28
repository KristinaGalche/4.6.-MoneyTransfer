package ru.netology.web.data;

import lombok.Value;
import lombok.Data;

@Data
public class DataHelper {
    private final String login = "vasya";
    private final String password = "qwerty123";
    private final String verificationCode = "12345";

    public final String[] cards = new String[]{"5559 0000 0000 0001", "5559 0000 0000 0002"};

    public String getCard(int index) {
        String card = cards[index];
        return card;
    }

    @Value
    public static class Cards {
        String cardNumber;
    }

    public static Cards getFirstCardNumber() {
        return new Cards("5559 0000 0000 0001");
    }

    public static Cards getSecondCardNumber() {
        return new Cards("5559 0000 0000 0002");
    }
}