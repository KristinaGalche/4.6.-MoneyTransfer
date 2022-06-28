package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.Balance;
import ru.netology.web.page.LoginPage;
import lombok.val;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.getSecondCardNumber;
import static ru.netology.web.data.DataHelper.getFirstCardNumber;
import static ru.netology.web.page.Balance.pushFirstCardButton;
import static ru.netology.web.page.Balance.pushSecondCardButton;

public class MoneyTransferTest {
    DataHelper user;
    Balance balance;

    @BeforeEach
    void authorization() {
        open("http://localhost:9999");
        LoginPage login = new LoginPage();
        user = new DataHelper();
        VerificationPage verificationPage = login.login(user);
        val Balance = verificationPage.validVerify(user);
    }

    @Test
    public void shouldTransferFromFirstToSecondCard() {
        int amount = 1000;
        val cardBalance = new Balance();
        val firstCardBalanceStart = cardBalance.getFirstCardBalance();
        val secondCardBalanceStart = cardBalance.getSecondCardBalance();
        val transactionPage = pushSecondCardButton();
        transactionPage.transfer(amount, getFirstCardNumber());
        val firstCardBalanceFinish = firstCardBalanceStart - amount;
        val secondCardBalanceFinish = secondCardBalanceStart + amount;
        assertEquals(firstCardBalanceFinish, cardBalance.getFirstCardBalance());
        assertEquals(secondCardBalanceFinish, cardBalance.getSecondCardBalance());
    }


    @Test
    public void shouldTransferFromSecondToFirstCard() {
        int amount = 5000;
        val cardBalance = new Balance();
        val firstCardBalanceStart = cardBalance.getFirstCardBalance();
        val secondCardBalanceStart = cardBalance.getSecondCardBalance();
        val transactionPage = pushFirstCardButton();
        transactionPage.transfer(amount, getSecondCardNumber());
        val firstCardBalanceFinish = firstCardBalanceStart + amount;
        val secondCardBalanceFinish = secondCardBalanceStart - amount;
        assertEquals(firstCardBalanceFinish, cardBalance.getFirstCardBalance());
        assertEquals(secondCardBalanceFinish, cardBalance.getSecondCardBalance());
    }


//    @Test
//    public void transferExtraBalance() {
//        int amount = 50000;
//        val cardBalance = new Balance();
//        val firstCardBalanceStart = cardBalance.getFirstCardBalance();
//        val secondCardBalanceStart = cardBalance.getSecondCardBalance();
//        val transactionPage = pushSecondCardButton();
//        transactionPage.transfer(amount, getFirstCardNumber());
//        transactionPage.errorLimit();
//    }

}
