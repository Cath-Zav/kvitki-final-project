package by.kvitki.ui;

import by.kvitki.webdriver.Webdriver;
import org.junit.jupiter.api.*;

public class LoginPageTest {
    @BeforeEach
    public void openHomePageGoToLoginPage() {
        HomePage homePage = new HomePage();
        homePage.openSite()
                .denyCookie()
                .closeAdvertisingBanner()
                .clickPersonalCabinet();
    }

    @AfterEach
    public void tearDown() {
        Webdriver.quit();
    }

    @Test
    @DisplayName("Checking loading of the LoginPage")
    public void test1() {
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals("Вход", loginPage.getLoginFormTitle());
    }

    @Test
    @DisplayName("Valid email and password")
    public void test2() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        Assertions.assertEquals("Настройки аккаунта", loginPage
                .fillCorrectlyLoginFormAndGetAccountPage("cathzavizion@gmail.com", "itAcademy2025&")
                .getAccountPageTitle());
    }

    @Test
    @DisplayName("Wrong Login and Password")
    public void test3() {
        LoginPage  loginPage = new LoginPage();
        Assertions.assertEquals("Электронная почта или пароль недействительны. Система была обновлена, и в связи с этим мы перешли на вход по электронной почте.",
                loginPage.fillUncorrectlyLoginFormAndGetWarningText("test@mail.com", "test123"));
    }
}
