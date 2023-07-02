package com.pachca;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public static final String URLs = com.pachca.URLs.LoginPage;

    //Поиск кнопки войти через почту
    private final SelenideElement buttonEnterWithEmail = $(byText("Через почту"));

    //Поиск текстового ввода для почты
    private final SelenideElement inputEmail = $(byName("email"));

    //Поиск текстового ввода для пароля
    private final SelenideElement inputPass = $(byName("password"));

    //Поиск кнопки "Войти"
    private final SelenideElement buttonEnter = $(byText("Войти"));

    /**
     * Клик по кнопке "Через почту"
     */
    @Step("Клик по кнопке \"Через почту\"")
    public LoginPage clickButtonEnterWithEmail() {
        buttonEnterWithEmail.click();
        return this;
    }

    /**
     * Ввод почты
     * @param email (String)
     */
    @Step("Ввод почты")
    public LoginPage fillInputEmail(String email) {
        inputEmail.sendKeys(email);
        return this;
    }

    /**
     * Ввод пароля
     * @param pass (String)
     */
    @Step("Ввод пароля")
    public LoginPage fillInputPass(String pass) {
        inputPass.sendKeys(pass);
        return this;
    }

    /**
     * Клик по кнопке "Войти"
     */
    @Step("Клик по кнопке \"Войти\"")
    public void clickButtonEnter() {
        buttonEnter.click();
    }

}
