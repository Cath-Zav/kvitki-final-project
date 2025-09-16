package by.kvitki.ui;

import by.kvitki.webdriver.Webdriver;

public class LoginPage {
    private String LOGIN_FORM_TITLE = "//div[@id='mat-tab-label-0-0']//span[@class='mdc-tab__text-label']";
    private String EMAIL_FIELD = "//input[@type='email']";
    private String PASSWORD_FIELD = "//input[@type='password']";
    private String BUTTON_LOGIN = "//app-ui-button/button";
    private String DIALOG_CONTAINER_TEXT = "//div[@class='info-text']";

    public LoginPage() {
    }

    public String getLoginFormTitle() {
        return Webdriver.getTextFromElement(LOGIN_FORM_TITLE);
    }

    public void sendKeyLogin(String email) {
        Webdriver.findElementByPath(EMAIL_FIELD).sendKeys(email);
    }

    public void sendKeyPassword(String password) {
        Webdriver.findElementByPath(PASSWORD_FIELD).sendKeys(password);
    }

    public AccountPage fillCorrectlyLoginFormAndGetAccountPage(String email, String password) {
        sendKeyLogin(email);
        sendKeyPassword(password);
        Webdriver.clickElement(BUTTON_LOGIN);
        Webdriver.pauseSeconds(5);
        sendKeyLogin(email);
        sendKeyPassword(password);
        Webdriver.clickElement(BUTTON_LOGIN);

        return new AccountPage().waitUntilLoaded();
    }

    public String fillUncorrectlyLoginFormAndGetWarningText(String email, String password) {
        sendKeyLogin(email);
        sendKeyPassword(password);
        Webdriver.clickElement(BUTTON_LOGIN);
        return Webdriver.findElementByPath(DIALOG_CONTAINER_TEXT).getText();
    }
}
