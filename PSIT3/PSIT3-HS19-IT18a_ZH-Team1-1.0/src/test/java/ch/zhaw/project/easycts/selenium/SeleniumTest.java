package ch.zhaw.project.easycts.selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * Default ChromeDriver is for Windows.
 * To test with other OS change to chromedriver_macOS_v78.0.3904.105 and before committing, please reset to Windows
 *
 * IMPORTANT: Before Testing with Selenium, currently Google Chrome version should be v78.
 * Default (currently): chromedriver_Windows_v78.0.3904.105.exe
 */
public class SeleniumTest {
    static final String INDEX_PAGE = "http://localhost:8080";
    static final String REGISTER_PAGE = "http://localhost:8080/register";
    static final String LOGIN_PAGE = "http://localhost:8080/login";
    static WebDriver driver;

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_Windows_v78.0.3904.105.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void cleanUp() {
        driver.close();
    }
}