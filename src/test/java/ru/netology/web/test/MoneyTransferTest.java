package ru.netology.web.test;


import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper.UserCard;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {


    DashboardPage dashboard;
    UserCard firstCard;
    UserCard secondCard;
    int currentBalanceFirstCard;
    int currentBalanceSecondCard;

    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var userInfo = DataHelper.getUser();
        var verificationPage = loginPage.validLogin(userInfo);
        var verificationCode = DataHelper.getCode(userInfo);
        dashboard = verificationPage.validCode(verificationCode);
        firstCard = DataHelper.getFirstCard();
        secondCard = DataHelper.getSecondCard();
        currentBalanceFirstCard = dashboard.getCardBalance(firstCard);
        currentBalanceSecondCard = dashboard.getCardBalance(secondCard);
    }

    @Test
    void shouldTransferMoneyToFirstCardFromSecondWithValidData() {
        var transferMoney = dashboard.choiceCardForAddMoney(firstCard);
        int validAmount = DataHelper.generateValidAmount(currentBalanceSecondCard);
        transferMoney.validOperationAddAmountToCard(validAmount, secondCard);
        var expectedBalanceFirstCard = currentBalanceFirstCard + validAmount;
        var expectedBalanceSecondCard = currentBalanceSecondCard - validAmount;
        var actualBalanceFirstCard = dashboard.getCardBalance(firstCard);
        var actualBalanceSecondCard = dashboard.getCardBalance(secondCard);
        assertAll(() -> assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard),
                () -> assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard));
    }

    @Test
    void shouldNotTransferMoneyToFirstCardFromSecondWithInValidData() {
        var transferMoney = dashboard.choiceCardForAddMoney(firstCard);
        int inValidAmount = (int) DataHelper.generateInValidAmount(currentBalanceSecondCard);
        transferMoney.inValidOperationAddAmountToCard(inValidAmount, secondCard, "Выполнена попытка перевода суммы, равной нулю или превышающей баланс карты.");
        var expectedBalanceFirstCard = currentBalanceFirstCard;
        var expectedBalanceSecondCard = currentBalanceSecondCard;
        var actualBalanceFirstCard = dashboard.getCardBalance(firstCard);
        var actualBalanceSecondCard = dashboard.getCardBalance(secondCard);
        assertAll(() -> assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard),
                () -> assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard));
    }

}












