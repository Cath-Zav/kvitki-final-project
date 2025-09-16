package by.kvitki.ui;

import by.kvitki.webdriver.Webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    public static final String ACCOUNT_TITLE= "//app-views-heading";

    public AccountPage() {
    }

    public String getAccountPageTitle(){
        return Webdriver.getTextFromElement(ACCOUNT_TITLE);
    }

    public AccountPage waitUntilLoaded() {
        new WebDriverWait(Webdriver.getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_TITLE)));
        return this;
    }
}
