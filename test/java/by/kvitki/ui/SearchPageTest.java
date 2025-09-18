package by.kvitki.ui;

import by.kvitki.webdriver.Webdriver;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPageTest {
    @BeforeEach
    public void openHomePage() {
        HomePage homePage = new HomePage();
        homePage.openSite()
                .denyCookie()
                .closeAdvertisingBanner();
    }

    @AfterEach
    public void tearDown() {
        Webdriver.quit();
    }

    @Test
    @DisplayName("Checking if the list of the results contains the specific product \"Душа народная. Душевный фестиваль\"")
    public void test1() {
        SearchPage searchPage = new SearchPage();
        searchPage.sendKeysSearch("Душа народная. Душевный фестиваль");
        searchPage.startSearch();
        Webdriver.pauseSeconds(5);

        Assertions.assertEquals("Душа народная. Душевный фестиваль", searchPage.getSearchResultFirstItemTitleText());
    }

    @Test
    @DisplayName("Checking if the list of the results contains \"опера\"")
    public void test2() {
        SearchPage searchPage = new SearchPage();
        searchPage.sendKeysSearch("опера");
        searchPage.startSearch();
        Webdriver.pauseSeconds(5);

        List<String> searchResults = searchPage.getSearchResultItemsTitleText();
        System.out.println(searchResults.size());

        assertThat(searchResults)
                .as("The list of searches should not be empty")
                .isNotEmpty();

        SoftAssertions softly = new SoftAssertions();
        for (int i = 0; i < searchResults.size(); i++) {
            String title = searchResults.get(i);
            System.out.println(i + ": " + title);
            softly.assertThat(title)
                    .as("Result #%s should contain 'опера'", i + 1)
                    .containsIgnoringCase("опера");
        }
        softly.assertAll();
    }

    @Test
    @DisplayName("Checking No Result page")
    public void test3() {
        SearchPage searchPage = new SearchPage();
        searchPage.sendKeysSearch("dfdgdfgf");
        searchPage.startSearch();
        Webdriver.pauseSeconds(20);

        Assertions.assertEquals("По вашему запросу ничего не найдено", searchPage.getTitleNothingFound());
    }
}
