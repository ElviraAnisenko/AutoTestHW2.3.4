package ru.netology.web.page;

import ru.netology.web.data.DataHelper;
import ru.netology.web.data.DataHelper.UserInfo;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public VerificationPage validLogin (DataHelper.UserInfo userInfo) {
        $("[data-test-id='login'] input").setValue(DataHelper.getUser().getLogin());
        $("[data-test-id='password'] input").setValue(DataHelper.getUser().getPassword());
        $(".button").click();
        return new VerificationPage();
    }

}
