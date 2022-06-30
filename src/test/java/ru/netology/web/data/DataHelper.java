package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class UserInfo {
        private String login;
        private String password;
    }

    public static UserInfo getUserInfo() {
        return new UserInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String verificationCode;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
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