package ch.hungrystudent.selenium;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LogoutTest extends SeleniumTest {

    @Before
    public void loginAsUser() {
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("inputEmail")).sendKeys("selenium@testing.ch");
        driver.findElement(By.id("inputPassword")).sendKeys("selenium123");
        driver.findElement(By.xpath("//button[@value='login']")).click();
    }

    @Test
    public void testLogout() {

        // Test location
        String coordinates = "47.23275,8.25046";

        // Go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        driver.findElement(By.id("markerName")).sendKeys("Test Cafe");
        driver.findElement(By.id("markerAddress")).sendKeys("Teststrasse 99");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.testcafe.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Ein ruhiges Cafe in der Altstadt");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("06:30-20:00");
        driver.findElement(By.xpath("//input[@value=\"bars\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"cafes\"]")).click();
        driver.findElement(By.id("cheap")).click();
        driver.findElement(By.id("takeAwayOption")).click();

        // Submit form
        driver.findElement(By.name("submitButton")).click();

        // Returns to Index Page
        driver.get(INDEX_PAGE);

        //Logout user
        driver.findElement(By.linkText("Logout")).click();

        assertEquals(LOGOUT_PAGE, driver.getCurrentUrl() );
    }
}