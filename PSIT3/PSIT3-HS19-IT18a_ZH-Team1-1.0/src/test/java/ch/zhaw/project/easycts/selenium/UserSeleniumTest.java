package ch.zhaw.project.easycts.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 * IMPORTANT:
 * Spring Boot Server (EasyctsApplication) should be after every complete Test class execution restarted,
 * due to not implemented user delete on frontend, otherwise UserSeleniumTest will fail!
 * Also mocking a User is not possible for Selenium Tests.
 *
 * As SQL query in H2 Console, DELETE query on USER table does also not work, due to constraint violation error message.
 */
public class UserSeleniumTest extends SeleniumTest {

    @Before
    public void maximizeBrowserWindow() {
        driver.manage().window().maximize();
    }

    @Test
    public void testRegisterUser() throws Exception {
        // Register new User as testing@testing.ch
        driver.get(REGISTER_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing@testing.ch");
        driver.findElement(By.id("password")).sendKeys("123456789");
        driver.findElement(By.id("confirm")).sendKeys("123456789");

        // Asserts
        assertEquals("testing@testing.ch", driver.findElement(By.id("mail")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("password")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("confirm")).getAttribute("value"));

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!

        // Go to Index Page
        driver.get(INDEX_PAGE);
        // Logout current User
        driver.findElement(By.cssSelector(".user-img")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void testRegisterExistingUser() throws Exception {
        // Register new User as testing@testing.ch
        driver.get(REGISTER_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing2@testing.ch");
        driver.findElement(By.id("password")).sendKeys("123456789");
        driver.findElement(By.id("confirm")).sendKeys("123456789");

        // Asserts
        assertEquals("testing2@testing.ch", driver.findElement(By.id("mail")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("password")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("confirm")).getAttribute("value"));

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!

        // Go to Index Page
        driver.get(INDEX_PAGE);

        // Logout current User
        driver.findElement(By.cssSelector(".user-img")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
        driver.findElement(By.linkText("Logout")).click();

        // Re-register same User as testing@testing.ch
        driver.get(REGISTER_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing2@testing.ch");
        driver.findElement(By.id("password")).sendKeys("123456789");
        driver.findElement(By.id("confirm")).sendKeys("123456789");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Asserts
        assertEquals("testing2@testing.ch", driver.findElement(By.id("mail")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("password")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("confirm")).getAttribute("value"));
        assertTrue(driver.findElement(By.cssSelector(".alert")).isDisplayed());

        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
    }

    @Test
    public void testRegisterInvalidUser() throws Exception {
        driver.get(REGISTER_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing");

        // Asserts
        assertEquals("testing", driver.findElement(By.id("mail")).getAttribute("value"));
        assertTrue(driver.findElement(By.id("mail-feedback")).isDisplayed());

        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
    }

    @Test
    public void testRegisterNotMachingPassword() throws Exception {
        driver.get(REGISTER_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing3@testing.ch");
        driver.findElement(By.id("password")).sendKeys("123456789");
        driver.findElement(By.id("confirm")).sendKeys("12345");

        // Asserts
        assertEquals("testing3@testing.ch", driver.findElement(By.id("mail")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("password")).getAttribute("value"));
        assertEquals("12345", driver.findElement(By.id("confirm")).getAttribute("value"));
        assertTrue(driver.findElement(By.id("confirm-feedback")).isDisplayed());

        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
    }

    @Test
    public void testUserLogin() throws Exception {
        // Register new User as testing.login@test.ch
        driver.get(REGISTER_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing.login2@test.ch");
        driver.findElement(By.id("password")).sendKeys("123456789");
        driver.findElement(By.id("confirm")).sendKeys("123456789");

        // Asserts
        assertEquals("testing.login2@test.ch", driver.findElement(By.id("mail")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("password")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("confirm")).getAttribute("value"));

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!

        // Go to Index Page
        driver.get(INDEX_PAGE);

        // Logout current User
        driver.findElement(By.cssSelector(".user-img")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
        driver.findElement(By.linkText("Logout")).click();

        // Go to Login Page
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing.login2@test.ch");
        driver.findElement(By.id("password")).sendKeys("123456789");


        // Asserts
        assertEquals("testing.login2@test.ch", driver.findElement(By.id("mail")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("password")).getAttribute("value"));

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!

        // Go to Index Page
        driver.get(INDEX_PAGE);

        // Logout current User
        driver.findElement(By.cssSelector(".user-img")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void testLoginNotExistingUser() throws Exception {
        // Go to Login Page
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing.notexist@testing.ch");
        driver.findElement(By.id("password")).sendKeys("123456789");

        // Asserts
        assertEquals("testing.notexist@testing.ch", driver.findElement(By.id("mail")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("password")).getAttribute("value"));

        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
    }

    @Test
    public void testInvalidPasswordLengthLogin() throws Exception {
        // Register new User as testing.invalid2@test.ch
        driver.get(REGISTER_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing.invalid2@test.ch");
        driver.findElement(By.id("password")).sendKeys("123456789");
        driver.findElement(By.id("confirm")).sendKeys("123456789");

        // Asserts
        assertEquals("testing.invalid2@test.ch", driver.findElement(By.id("mail")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("password")).getAttribute("value"));
        assertEquals("123456789", driver.findElement(By.id("confirm")).getAttribute("value"));

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!

        // Go to Index Page
        driver.get(INDEX_PAGE);

        // Logout current User
        driver.findElement(By.cssSelector(".user-img")).click();
        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
        driver.findElement(By.linkText("Logout")).click();

        // Go to Login Page
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("mail")).sendKeys("testing.invalid2@test.ch");
        driver.findElement(By.id("password")).sendKeys("1234");

        // Asserts
        assertEquals("testing.invalid2@test.ch", driver.findElement(By.id("mail")).getAttribute("value"));
        assertEquals("1234", driver.findElement(By.id("password")).getAttribute("value"));

        Thread.sleep(1200); // This Thread.sleep(1200) cannot fall throw, otherwise Test will fail!
    }
}