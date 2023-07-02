package com.pachca;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public static final String URLs = com.pachca.URLs.MainPage;

    //Поиск кнопки "Создать"
    private final SelenideElement buttonCreate = $(byText("Создать"));

    //Поиск текстового ввода для названия Беседы
    private final SelenideElement inputConversationName = $x("//*[text()=\"Название\"]/input");

    //Поиск кнопки "Создать беседу"
    private final SelenideElement buttonCreateConversation = $(byText("Создать беседу"));

    //Поиск кнопки "Пропустить" для диалога добавления участников
    private final SelenideElement buttonSkip = $(byText("Пропустить"));

    //Поиск кнопки "Информация"
    private final SelenideElement buttonInfo = $x("//*[@aria-label=\"Информация\"]");

    //Поиск кнопки "Добавить в архив"
    private final SelenideElement buttonAddToArchive = $(byText("Отправить в архив"));

    //Поиск по кнопке "Архивировать" беседу
    private final SelenideElement buttonArchive = $(byText("Архивировать"));

    //Поиск кнопки "Выход" из беседы
    private final SelenideElement buttonExitConversation = $(byText("Выйти из беседы"));

    //Поиск кнопки "Продолжить" для подтверждения выхода из беседы
    private final SelenideElement buttonContinueExitConversation = $(byText("Продолжить"));

    //Поиск кнопки "Отменить" для отмены выхода/архивирования беседы
    private final SelenideElement buttonCancelExitOrArchiveConversation = $(byText("Отменить"));

    /**
     * Клик по кнопке создания нового элемента (Беседы или Канала)
     */
    @Step("Клик по кнопке создания нового элемента (Беседы или Канала)")
    public MainPage clickButtonCreate() {
        buttonCreate.click();
        return this;
    }

    /**
     * Выбор элемента для создания
     * @param menuItem ("Новая беседа" или "Новый канал")
     */
    @Step("Выбор элемента для создания")
    public MainPage selectElementToBeCreated(String menuItem) {
        $(byText(menuItem)).click();
        return this;
    }

    /**
     * Ввод названия беседы
     * @param conversationName (String)
     */
    @Step("Ввод названия беседы")
    public MainPage fillInputConversationName(String conversationName) {
        inputConversationName.sendKeys(conversationName);
        return this;
    }

    /**
     * Клик по кнопке подтверждения создания нового элемента (Беседы или Канала)
     */
    @Step("Клик по кнопке подтверждения создания нового элемента (Беседы или Канала)")
    public MainPage clickButtonCreateConversation() {
        buttonCreateConversation.click();
        return this;
    }

    /**
     * Клик по кнопке "Пропустить" для диалога добавления участников
     */
    @Step("Клик по кнопке \"Пропустить\" для диалога добавления участников")
    public MainPage clickButtonSkip() {
        buttonSkip.click();
        return this;
    }

    /**
     * Клик по беседе или каналу
     */
    @Step("Клик по беседе или каналу")
    public void clickElement(String name) {
        $(byText(name)).shouldBe(visible, enabled).click();
    }

    /**
     * Клик по кнопке "Информация"
     */
    @Step("Клик по кнопке \"Информация\"")
    public MainPage clickButtonInfo() {
        if ((buttonInfo.getCssValue("background-color")).equals("rgba(0, 0, 0, 0)")) {
            buttonInfo.shouldBe(visible, enabled).click();
            System.out.println("Background color: " + buttonInfo.getCssValue("background-color"));
        }
        System.out.println("Background color: " + buttonInfo.getCssValue("background-color"));
        return this;
    }

    /**
     * Клик по кнопке "Добавить в архив"
     */
    @Step("Клик по кнопке \"Добавить в архив\"")
    public MainPage clickButtonAddToArchive() {
        buttonAddToArchive.shouldBe(visible, enabled).click();
        return this;
    }

    /**
     * Подтверждение архивирования
     */
    @Step("Подтверждение архивирования")
    public void clickButtonArchive() {
        buttonArchive.click();

    }

    /**
     * Клик по кнопке "Выйти из беседы"
     */
    @Step("Клик по кнопке \"Выйти из беседы\"")
    public MainPage clickButtonExitConversation() {
        buttonExitConversation.click();
        return this;
    }

    /**
     * Клик по кнопке "Продолжить" для подтверждения выхода из беседы
     */
    @Step("Клик по кнопке \"Продолжить\" для подтверждения выхода из беседы")
    public void clickButtonContinueExitConversation() {
        buttonContinueExitConversation.shouldBe(visible).click();
    }

    /**
     * Клик по кнопке "Отменить" для отмены выхода/архивирования беседы
     */
    @Step("Клик по кнопке \"Отменить\" для отмены выхода/архивирования беседы")
    public void clickButtonCancelExitOrArchiveConversation() {
        buttonCancelExitOrArchiveConversation.click();
    }

    /**
     * Подсчет кол-ва бесед
     */
    @Step("Подсчет кол-ва бесед")
    public int countOfConversation() {
        return $$x("//*[text()=\"Беседы и каналы\"]/../../div[3]/a").size();
    }

    /**
     * Архивирование всех бесед
     */
    @Step("Архивирование всех бесед")
    public void archiveAllConversation() {
        int count = countOfConversation();
        while (count > 0) {
            System.out.println("Осталось бесед: " + count);
            $x("//*[text()=\"Беседы и каналы\"]/../../div[3]/a[1]").click();
            clickButtonAddToArchive();
            clickButtonArchive();
            count = countOfConversation();
            sleep(1000);
        }
    }
}
