package by.kvitki.ui;

import by.kvitki.webdriver.Webdriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private final String INPUT_SEARCH = "//input[@class='search_component_input input_component']";
    private final String BUTTON_SEARCH = "//a[@class='iconpl-search search_component_button']";
    private final String TITLE_SEARCH_RESULT = "//span[@class='event_short_top_bottom']";
    private final String TITLE_NOTHING_FOUND = "//div[@class='search_results_no_results_msg']";

    public SearchPage() {
    }

    public void sendKeysSearch(String search) {
        Webdriver.sendKeys(INPUT_SEARCH, search);
    }

    public void startSearch() {
        Webdriver.clickElement(BUTTON_SEARCH);
    }

    public String getSearchResultFirstItemTitleText() {
        return Webdriver.getTextFromElement(TITLE_SEARCH_RESULT);
    }

    public List<String> getSearchResultItemsTitleText() {
        List<WebElement> listOfSearchResultElements = Webdriver.findElements(TITLE_SEARCH_RESULT);
        List<String> listOfSearchResultTitles = new ArrayList<>();

        for(WebElement element : listOfSearchResultElements) {
            listOfSearchResultTitles.add(element.getText().toLowerCase());
        }
        return listOfSearchResultTitles;
    }

    public String getTitleNothingFound() {
        return Webdriver.findElementByPath(TITLE_NOTHING_FOUND).getText().trim();
    }
}
