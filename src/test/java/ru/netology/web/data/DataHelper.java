package ru.netology.web.data;

import lombok.Value;
import lombok.Data;

@Data
public class DataHelper {
    private final String login = "vasya";
    private final String password = "qwerty123";
    private final String verificationCode = "12345";
    //public final String firstCardNo = "5559 0000 0000 0001";
    //private final String secondCardNo = "5559 0000 0000 0002";

    public final String[] cards = new String[] {"5559 0000 0000 0001", "5559 0000 0000 0002"};

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

//public class DataHelper {
//    private DataHelper() {
//    }
//    @Value
//    public static class AuthInfo {
//        private String login;
//        private String password;
//    }
//
//    public static AuthInfo getAuthInfo() {
//        return new AuthInfo("vasya", "qwerty123");
//    }
//
//    @Value
//    public static class VerificationCode {
//        String code;
//    }
//
//    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
//        return new VerificationCode("12345");
//    }
//
//    @Value
//    public static class Cards {
//        String cardNumber;
//    }
//

//}
