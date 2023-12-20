package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;
import ru.netology.web.test.MoneyTransferTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class VerificationPage {
    private SelenideElement codeField=$("[data-test-id='code'] input");
    private SelenideElement button=$(".button");

    public VerificationPage () {
        codeField.shouldBe(visible);
    }

    public DashboardPage validCode (DataHelper.VerificationCode verificationCode) {
       codeField.setValue(verificationCode.getCode());
       button.click();
         return new DashboardPage();
    }

}
