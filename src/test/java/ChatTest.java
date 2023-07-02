import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.pachca.LoginPage;;
import com.pachca.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.*;
import static com.pachca.UserData.*;

@ExtendWith({TextReportExtension.class, SoftAssertsExtension.class})
@DisplayName("Работа с беседами")
public class ChatTest {

    static LoginPage loginPage = page(LoginPage.class);
    static MainPage mainPage = page(MainPage.class);

    @BeforeAll
    static void beforeAll() {
        Configuration.headless = true;
        SelenideLogger.addListener("Allure Selenide Listener",
                new AllureSelenide()
                        .savePageSource(false)
                        .screenshots(true)
        );
        loginPage = open(LoginPage.URLs, LoginPage.class);
        loginPage.clickButtonEnterWithEmail()
                .fillInputEmail(LOGIN)
                .fillInputPass(PASSWORD)
                .clickButtonEnter();
        mainPage.clickButtonCreate()
                .selectElementToBeCreated("Новая беседа")
                .fillInputConversationName("Тестовая беседа 1")
                .clickButtonCreateConversation()
                .clickButtonSkip()
                .clickButtonCreate()
                .selectElementToBeCreated("Новая беседа")
                .fillInputConversationName("Тестовая беседа 2")
                .clickButtonCreateConversation()
                .clickButtonSkip()
                .clickButtonCreate()
                .selectElementToBeCreated("Новая беседа")
                .fillInputConversationName("Тестовая беседа 3")
                .clickButtonCreateConversation()
                .clickButtonSkip();
    }

    @AfterAll
    @DisplayName("Возвращение системы в состояние до тестов")
    public static void afterAll() {
        mainPage.archiveAllConversation();

    }

    @Test
    @DisplayName("Подтверждение выхода из беседы")
    public void confirmExitConversationTest() {
        mainPage.clickElement("Тестовая беседа 1");
        int countOfConversation = mainPage.countOfConversation();
        mainPage.clickButtonInfo()
                .clickButtonExitConversation()
                .clickButtonContinueExitConversation();

        Assertions.assertEquals(countOfConversation - 1, mainPage.countOfConversation());
    }

    @Test
    @DisplayName("Отмена выхода из беседы")
    public void cancelExitConversationTest() {
        mainPage.clickElement("Тестовая беседа 2");
        int countOfConversation = mainPage.countOfConversation();
        mainPage.clickButtonInfo()
                .clickButtonExitConversation()
                .clickButtonCancelExitOrArchiveConversation();

        Assertions.assertEquals(countOfConversation, mainPage.countOfConversation());
    }

    @Test
    @DisplayName("Отмена архивирования беседы")
    public void cancelArchiveConversationTest() {
        mainPage.clickElement("Тестовая беседа 3");
        int countOfConversation = mainPage.countOfConversation();
        mainPage.clickButtonInfo()
                .clickButtonAddToArchive()
                .clickButtonCancelExitOrArchiveConversation();

        Assertions.assertEquals(countOfConversation, mainPage.countOfConversation());
    }
}
