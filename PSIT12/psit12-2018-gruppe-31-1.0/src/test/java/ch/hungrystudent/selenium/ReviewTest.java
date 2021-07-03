package ch.hungrystudent.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReviewTest extends SeleniumTest {

    @Before
    public void loginAsUser() {
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("inputEmail")).sendKeys("selenium@testing.ch");
        driver.findElement(By.id("inputPassword")).sendKeys("selenium123");
        driver.findElement(By.xpath("//button[@value='login']")).click();
    }

    @Test
    public void testWritingReviewOnMarker() {

        // Test location at "Greifensee"
        String coordinates = "47.35916,8.67020";

        // Go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        driver.findElement(By.id("markerName")).sendKeys("Test Review 1");
        driver.findElement(By.id("markerAddress")).sendKeys("Teststrasse 99");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.test.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Ein ruhiges Cafe im See");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("06:30-20:00");
        driver.findElement(By.xpath("//input[@value=\"cafes\"]")).click();

        // Submit form
        driver.findElement(By.name("submitButton")).click();

        // get id of created marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // Returns to Index Page
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // Write review
        driver.findElement(By.id("reviewField")).sendKeys("First Test Review");
        driver.findElement(By.name("submitReview")).click();

        // assert new review
        WebElement reviewText = driver.findElement(By.xpath("//p[contains(text(), 'First Test Review')]"));
        assertNotNull(reviewText);
        WebElement reviewer = driver.findElement(By.xpath("//p[contains(text(), 'First Test Review')]/preceding-sibling::p"));
        assertNotNull(reviewer);
        assertEquals("seleniumuser", reviewer.getText());

        // remove marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testThatNotLoggedInUserCantWriteReview() {

        // Test location at "Greifensee"
        String coordinates = "47.35916,8.67020";

        // Go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        driver.findElement(By.id("markerName")).sendKeys("Test Review 1");
        driver.findElement(By.id("markerAddress")).sendKeys("Teststrasse 99");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.test.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Ein ruhiges Cafe im See");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("06:30-20:00");
        driver.findElement(By.xpath("//input[@value=\"cafes\"]")).click();

        // submit form
        driver.findElement(By.name("submitButton")).click();

        // get id of created marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        //Logout user
        driver.findElement(By.linkText("Logout")).click();

        // Returns to Index Page
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // assert review text
        WebElement reviewText = driver.findElement(By.xpath("//p[contains(text(), 'Sie müssen eingeloggt sein, damit Sie eine Rezension schreiben dürfen.')]"));
        assertNotNull(reviewText);

        // remove marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }
}