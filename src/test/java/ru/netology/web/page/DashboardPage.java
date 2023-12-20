package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;



public class DashboardPage {
    private SelenideElement dashboard=$("[data-test-id=dashboard]");
    public ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";



    public DashboardPage() {
        dashboard.shouldBe(visible);
    }

    public int getCardBalance(DataHelper.UserCard userCard) {
        var text = cards.findBy(Condition.text(userCard.getNumber().substring(15))).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferMoneyPage choiceCardForAddMoney (DataHelper.UserCard userCard) {
        cards.findBy(Condition.attribute("data-test-id",userCard.getId())).$("button").click();
        return new TransferMoneyPage();
    }


}


