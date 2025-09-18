package by.kvitki.ui;

import by.kvitki.webdriver.Webdriver;

public class HomePage {
    private final String URL = "https://www.kvitki.by/";
    private final String LINK_PERSONAL_CABINET = "//div[@class='header']//app-user-account-link";
    private final String BUTTON_DENY_COOKIES = "//button[@id='CybotCookiebotDialogBodyButtonDecline']";
    private final String BUTTON_CLOSE_ADVERTISING_BANNER = "//div[@class='popupbanner_close']";

    public HomePage() {
    }

    public HomePage openSite() {
        Webdriver.getDriver().get(URL);
        return this;
    }

    public HomePage denyCookie() {
        try{
            Webdriver.findElementByPath(BUTTON_DENY_COOKIES).click();
        } catch(Exception e) {
            System.out.println("There wasn't cookie banner. Cookie banner wasn't closed");
        }
        return this;
    }

    public HomePage closeAdvertisingBanner() {
        try {
            Webdriver.clickElement(BUTTON_CLOSE_ADVERTISING_BANNER);
        } catch (Exception e) {
            System.out.println("There wasn't a banner, banner wasn't closed");
        }
        return this;
    }

    public void clickPersonalCabinet() {
        Webdriver.clickElement(LINK_PERSONAL_CABINET);
    }
}
