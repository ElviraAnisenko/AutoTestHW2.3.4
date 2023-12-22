package ru.netology.web.data;

import lombok.Value;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.test.MoneyTransferTest;

import java.util.Random;
import java.lang.Math;

public class DataHelper {
    private DataHelper() {
    }
    @Value
    public static class UserInfo {
        String login;
        String password;
    }
    public static UserInfo getUser() {
            UserInfo user = new UserInfo("vasya", "qwerty123");
            return user;
    }

    @Value
    public static class VerificationCode {
        String code;

    }


    public static  VerificationCode getCode (UserInfo user) {
            VerificationCode code=new VerificationCode("12345");
            return code;
        }


    @Value
    public static class UserCard {
        String number;
        String balance;
        String id;
    }

    public static UserCard getFirstCard () {
        UserCard firstCard=new UserCard("5559 0000 0000 0001", "10 000", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
            return firstCard;
        }

    public static UserCard getSecondCard () {
        UserCard secondCard=new UserCard("5559 0000 0000 0002", "10 000", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        return secondCard;
    }

    public static int generateValidAmount(int balance) {
         balance=Math.abs((balance));
         int [] validAmounts= new int [] {balance,balance-1,1};
         int validAmount=validAmounts[new Random().nextInt(validAmounts.length)-1];
         return validAmount;
    }

    public static int generateInValidAmount(int balance) {
        balance=Math.abs((balance));
        int [] inValidAmounts= new int [] {balance+1,0,-1};
        int inValidAmount=inValidAmounts[new Random().nextInt(inValidAmounts.length)-1];
        return inValidAmount;
    }

    }
