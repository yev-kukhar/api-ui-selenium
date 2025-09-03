package org.yevhenii.ui_tests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class GooglePage extends BasePage {

    private static final By ACCEPT_ALL_BUTTON = By.xpath("//div[text()='Aceptar todo']");
    private static final By SEARCH_BOX_LOCATOR = By.name("q");
    private static final By SEARCH_RESULTS_LOCATOR = By.id("search");

    final String googleUrl = "https://www.google.com/";

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @Step("Opening Google main page")
    public void openGoogle() {
        openPage(googleUrl);
        assertElementIsVisibleBy(ACCEPT_ALL_BUTTON);
        clickWhenVisible(getWebElement(ACCEPT_ALL_BUTTON));
    }

    @Step("Searching for '{query}' keyword")
    public void searchFor(String query) {
        setValueToFieldAndHitEnter(SEARCH_BOX_LOCATOR, query);
        assertElementIsVisibleBy(SEARCH_RESULTS_LOCATOR);
    }

    /**
     * Verifies that every single search result contains at least one of the specified keywords.
     * Important! .allMatch() on an empty collection returns 'true', but this method
     * returns 'false' for empty search results.
     *
     * @param keywords list of keywords to search for in the results (case-insensitive)
     * @return true if all search results contain at least one keyword, false if no search
     * results found or if any result doesn't contain any of the keywords
     */
    @Step("verifies that every single search result contains '{keywords}' keyword")
    public boolean isSearchedResultsContainsKeyword(List<String> keywords) {
        List<WebElement> searchResults =
        returnListOfElements(SEARCH_RESULTS_LOCATOR);

        if (searchResults.isEmpty()) {
            throw new IllegalStateException("Search results are empty - unable to verify keyword presence");
        }

        return searchResults.stream()
                .allMatch(element -> {
                    String elementText = element.getText().toLowerCase();
                    return keywords.stream().anyMatch(keyword ->
                            elementText.contains(keyword.toLowerCase()));
                });
    }
}