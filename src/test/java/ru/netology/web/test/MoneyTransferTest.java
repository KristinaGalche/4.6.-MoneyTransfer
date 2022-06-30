package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.Balance;
import ru.netology.web.page.LoginPage;
import lombok.val;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

public class MoneyTransferTest {
    Balance balance;

    @BeforeEach
    void authorization() {
        open("http://localhost:9999");
        LoginPage login = new LoginPage();
        val userInfo = DataHelper.getUserInfo();
        val VerificationPage = login.login(userInfo);
        val verificationCode = DataHelper.getVerificationCode();
        balance = VerificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferFromFirstToSecondCard() {
        int amount = 1000;
        val cardBalance = new Balance();
        val firstCardBalanceStart = cardBalance.getFirstCardBalance();
        val secondCardBalanceStart = cardBalance.getSecondCardBalance();
        val transactionPage = balance.pushSecondCardButton();
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
        val transactionPage = balance.pushFirstCardButton();
        transactionPage.transfer(amount, getSecondCardNumber());
        val firstCardBalanceFinish = firstCardBalanceStart + amount;
        val secondCardBalanceFinish = secondCardBalanceStart - amount;
        assertEquals(firstCardBalanceFinish, cardBalance.getFirstCardBalance());
        assertEquals(secondCardBalanceFinish, cardBalance.getSecondCardBalance());
    }

    @Test
    public void extraBalance() {
        int amount = 50000;
        val cardBalance = new Balance();
        val firstCardBalanceStart = cardBalance.getFirstCardBalance();
        val secondCardBalanceStart = cardBalance.getSecondCardBalance();
        val transactionPage = balance.pushFirstCardButton();
        transactionPage.transfer(amount, getSecondCardNumber());
        transactionPage.errorLimit();
    }
}
