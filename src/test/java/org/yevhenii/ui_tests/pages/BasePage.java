package org.yevhenii.ui_tests.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;


/**
 * Common features for all pages
 * add logback-classic to pom
 */
public class BasePage {

    public static final Logger log = LoggerFactory.getLogger(BasePage.class);
    private final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement waitForVisibility(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForVisibilityBy(By element) {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void assertElementIsVisibleBy(By element) {
        try {
            waitForVisibilityBy(element);
        } catch (NoSuchElementException e) {
            throw new ElementNotInteractableException("Element was not visible within the specified time.");
        }
    }

    public void clickWhenVisible(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    protected void openPage(String url) {
        driver.get(url);
    }

    protected WebElement getWebElement(By xPath) {
        return driver.findElement(xPath);
    }

    protected void setValueToFieldAndHitEnter(By by, String query) {
        assertElementIsVisibleBy(by);
        WebElement searchBox = driver.findElement(by);
        searchBox.sendKeys(query + Keys.ENTER);
    }

    protected List<WebElement> returnListOfElements(By by){
        return driver.findElements(by);
    }

}
