package ch.hungrystudent.selenium;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchTest extends SeleniumTest {

    @Test
    public void testSearch() {

        // Test location
        String searchInput = "Burger King";
        String coordinates = "47.37299, 8.53733";
        String markerId = "2";

        // Go to index page
        driver.get(INDEX_PAGE);

        //Write search input
        driver.findElement(By.id("searchBar")).sendKeys(searchInput);

        // Submit form
        driver.findElement(By.name("searchButton")).click();

        // Gets id of  Marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // Clicks on searched Marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        //Assert
        assertEquals(markerId, markerNr);

    }

    @Test
    public void testSearchCreatedMarker() {

        //Login User
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("inputEmail")).sendKeys("selenium@testing.ch");
        driver.findElement(By.id("inputPassword")).sendKeys("selenium123");
        driver.findElement(By.xpath("//button[@value='login']")).click();

        // Test location
        String coordinates = "47.37275,8.54046";

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

        // Gets id of created Marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // Returns to Index Page
        driver.get(INDEX_PAGE);

        //Searches Marker
        String searchInput = "Test Cafe";

        //Write search input
        driver.findElement(By.id("searchBar")).sendKeys(searchInput);

        // Submit form
        driver.findElement(By.name("searchButton")).click();

        // Gets id of displayed Marker after search
        WebElement searchMarkerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String searchMarkerNr = searchMarkerValueTag.getAttribute("data-marker-id");

        // Clicks on searched Marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        //Assert
        assertEquals(markerNr, searchMarkerNr);

        // Removes Marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testSearchWithOnlyPartOfInput() {
        // Test location
        String searchInput = "Pizzeria";
        String coordinates = "47.28668, 8.65723";
        String markerId = "1";

        // Go to index page
        driver.get(INDEX_PAGE);

        //Write search input
        driver.findElement(By.id("searchBar")).sendKeys(searchInput);

        // Submit form
        driver.findElement(By.name("searchButton")).click();

        // Gets id of  Marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // Clicks on searched Marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        //Assert
        assertEquals(markerId, markerNr);

    }
}
