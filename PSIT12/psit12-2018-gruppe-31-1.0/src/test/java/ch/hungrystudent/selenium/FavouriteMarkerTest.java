package ch.hungrystudent.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;


public class FavouriteMarkerTest extends SeleniumTest {

    @Before
    public void loginAsUser() {

        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("inputEmail")).sendKeys("selenium@testing.ch");
        driver.findElement(By.id("inputPassword")).sendKeys("selenium123");
        driver.findElement(By.xpath("//button[@value='login']")).click();
    }

    @Test
    public void testThatFavouritedMarkersAreDisplateInFavouritePage() throws InterruptedException {

        // uses Uetliberg as test location
        String coordinates = "47.35399,8.48946";

        // go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        // fill out form for new marker
        driver.findElement(By.id("markerName")).sendKeys("Selenium Test Favourite 1");
        driver.findElement(By.id("markerAddress")).sendKeys("favouritestrasse 1");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.favouritemarker.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("This tests the favourite page");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("Never");

        // submit form
        driver.findElement(By.name("submitButton")).click();

        // get id of created marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // mark marker as favourite
        driver.findElement(By.id("markerFavouriteButton")).click();
        Thread.sleep(1000);

        // goes to favourite page
        driver.get(FAVOURITE_PAGE);

        // asserts whether favourited markers are displayed
        Thread.sleep(1000);
        assertEquals("Selenium Test Favourite 1", driver.findElement(By.className("markerTitle")).getText());

        // removes marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testThatFavouriteStarIsDisplayedAccordinglyWhenClicked() {

        // uses Uetliberg as test location
        String coordinates = "47.35398,8.48944";

        // go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        // fill out form for new marker
        driver.findElement(By.id("markerName")).sendKeys("Selenium Test Favourite 2");
        driver.findElement(By.id("markerAddress")).sendKeys("favouritestrasse 2");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.favouritemarker.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("This tests the favourite button");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("Never");

        // submit form
        driver.findElement(By.name("submitButton")).click();

        // get id of created marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // assert that clicking the star will change the icon
        assertTrue(driver.findElement(By.id("markerFavouriteButton")).getAttribute("src").endsWith("/starIcon.png"));
        driver.findElement(By.id("markerFavouriteButton")).click();
        assertTrue(driver.findElement(By.id("markerFavouriteButton")).getAttribute("src").endsWith("/emptyStar.png"));
        driver.findElement(By.id("markerFavouriteButton")).click();
        assertTrue(driver.findElement(By.id("markerFavouriteButton")).getAttribute("src").endsWith("/starIcon.png"));

        // removes marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);
        driver.findElement(By.id("markerRemoveButton")).click();
    }
}
