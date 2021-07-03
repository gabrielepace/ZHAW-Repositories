package ch.hungrystudent.selenium;

import java.util.NoSuchElementException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * Default ChromeDriver is for Windows.
 * To test with other OS change to chromedriverMacOS or chromedriverLinux (for Linux distributions) and
 * before commit please reset to Windows
 *
 * IMPORTANT: Before Testing with Selenium, check your Chrome Browser version and change it with your
 * currently version.
 * Default (currently): chromedriverWindows.exe
 */
public class SeleniumTest {

    static final String INDEX_PAGE = "http://localhost:8080/HungryStudent/index.jsp";
    static final String CREATE_PAGE = "http://localhost:8080/HungryStudent/pages/neuemarkierung.jsp";
    static final String REGISTER_PAGE = "http://localhost:8080/HungryStudent/pages/registrierung.jsp";
    static final String LOGIN_PAGE = "http://localhost:8080/HungryStudent/pages/login.jsp";
    static final String FAVOURITE_PAGE = "http://localhost:8080/HungryStudent/pages/favoriten.jsp";
    static final String LOGOUT_PAGE = "http://localhost:8080/HungryStudent/pages/login.jsp?action=logout";
    static final String USERSETTINGS_PAGE = "http://localhost:8080/HungryStudent/pages/benutzereinstellungen.jsp";
    static WebDriver driver;

    @BeforeClass
    public static void init() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriverWindows.exe");
        driver = new ChromeDriver();
    }

    protected boolean isElementPresent(By byId) {
        try {
            driver.findElement(byId);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @AfterClass
    public static void cleanUp() {
        driver.close();
    }
}
