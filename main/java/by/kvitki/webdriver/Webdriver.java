package by.kvitki.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Webdriver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

    public static WebElement findElementByPath(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }

    public static void clickElement(String xpath) {
        findElementByPath(xpath).click();
    }

    public static String getTextFromElement(String xpath) {
        return findElementByPath(xpath).getText();
    }

    public static void pauseSeconds(long seconds) {
        try {
            Thread.sleep(java.time.Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Pause was interrupted", e);
        }
    }

    public static void sendKeys(String xpath, String value) {
        findElementByPath(xpath).sendKeys(value);
    }

    public static List<WebElement> findElements(String xpath) {
        return Webdriver.getDriver().findElements(By.xpath(xpath));
    }
}
