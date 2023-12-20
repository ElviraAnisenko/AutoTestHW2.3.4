package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {
    private SelenideElement fieldAmount=$("[data-test-id='amount'] input");
    private SelenideElement toCard=$("[data-test-id='to'] input");
    private SelenideElement fromCard= $("[data-test-id='from'] input");
    private SelenideElement button= $(".button_view_extra");
    private  SelenideElement error= $("[data-test-id='error-notification']");


public TransferMoneyPage() {
    toCard.shouldBe(visible);
    fieldAmount.shouldBe(visible);
    fromCard.shouldBe(visible);
}


    public DashboardPage validOperationAddAmountToCard (int validAmount , DataHelper.UserCard userCard) {
        fieldAmount.setValue(String.valueOf(validAmount));
        fromCard.setValue(userCard.getNumber());
        button.click();
        return new DashboardPage();

    }

    public void InValidOperationAddAmountToCard (int inValidAmount , DataHelper.UserCard userCard, String textError) {
        fieldAmount.setValue(String.valueOf(inValidAmount));
        fromCard.setValue(userCard.getNumber());
        button.click();
        error.shouldHave(text(textError)).shouldBe(visible, Duration.ofSeconds(10));



    }


}
