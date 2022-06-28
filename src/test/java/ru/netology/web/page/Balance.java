package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import lombok.val;

public class Balance {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private static SelenideElement firstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button");
    private static SelenideElement secondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button");

    private ElementsCollection cards = $$(".list__item div");
    private final String startBalance = "баланс: ";
    private final String finishBalance = " р.";

    public Balance() {
    }

    public static TransferPage pushFirstCardButton() {
        firstCardButton.click();
        return new TransferPage();
    }
    public static TransferPage pushSecondCardButton() {
        secondCardButton.click();
        return new TransferPage();
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }
    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(startBalance);
        val finish = text.indexOf(finishBalance);
        val value = text.substring(start + startBalance.length(), finish);
        return Integer.parseInt(value);
    }
}
