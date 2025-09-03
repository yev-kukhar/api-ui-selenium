package org.yevhenii.ui_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.yevhenii.ui_tests.pages.GooglePage;

import java.util.Collections;

public class BaseUiTest {
    protected WebDriver driver;
    protected GooglePage googlePage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // 🔐 Security and privacy
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-features=TranslateUI");
        // 🧍‍♂️ Simulate a real user
        options.addArguments("--lang=en-US"); // или es-ES, если нужен испанский
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/138.0.0.0 Safari/537.36");
        // 🛡️ Disable WebDriver detection (often used in bot detection)
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        // ⚙️ Additionally
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public synchronized GooglePage googlePage() {
        if (googlePage == null) {
            googlePage = new GooglePage(driver);
        }
        return googlePage;
    }

    @AfterEach
    public void tearDown() {
        // Close the WebDriver instance after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
