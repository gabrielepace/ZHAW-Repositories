package ch.hungrystudent.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ShowMarkerDetailsTest extends SeleniumTest {

    @Before
    public void loginAsUser() {

        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("inputEmail")).sendKeys("selenium@testing.ch");
        driver.findElement(By.id("inputPassword")).sendKeys("selenium123");
        driver.findElement(By.xpath("//button[@value='login']")).click();
    }

    @Test
    public void testShowMarkerDetailsOnlyPriceCheapAndVegetarian() throws Exception {

        // Uses as Test location Zurich Main Station
        String coordinates = "47.37774,8.54077";

        // Go to Index Page
        driver.get(INDEX_PAGE);
        Thread.sleep(1000);

        // Go to create Marker Page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);
        driver.findElement(By.id("markerName")).sendKeys("Test Markierung 1");

        driver.findElement(By.id("markerAddress")).sendKeys("Teststrasse 1");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.test.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Test Beschreibung 1");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("8:00-20:00");
        driver.findElement(By.xpath("//input[@value=\"european\"]")).click();
        driver.findElement(By.id("cheap")).click();
        driver.findElement(By.id("vegetarianOption")).click();

        // Submit form
        driver.findElement(By.name("submitButton")).click();

        // Gets id of created Marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // Returns to Index Page
        driver.get(INDEX_PAGE);

        // Clicks on created Marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // Asserts created values
        assertEquals("Test Markierung 1", driver.findElement(By.id("markerName")).getText());
        assertEquals("http://www.test.ch/", driver.findElement(By.id("markerWebsiteButton")).getAttribute("href"));
        assertEquals("Test Beschreibung 1", driver.findElement(By.id("markerDescription")).getText());
        assertEquals("Preisklasse: Billig (< 10)", driver.findElement(By.id("markerPrice")).getText());
        assertEquals("Vegetarisch: Ja", driver.findElement(By.id("markerVegetarian")).getText());
        assertEquals("Take Away: Nein", driver.findElement(By.id("markerTakeAway")).getText());

        Thread.sleep(1000);
        driver.findElement(By.id("sidebar-marker")).click();

        // Removes Marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testShowMarkerDetailsOnlyPriceMediumAndTakeAway() throws Exception {

        // Uses as Test location nearby Zurich Main Station
        String coordinates = "47.37623,8.53926";

        // Go to Index Page
        driver.get(INDEX_PAGE);
        Thread.sleep(1000);

        // Go to create Marker Page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);
        driver.findElement(By.id("markerName")).sendKeys("Test Markierung 2");
        driver.findElement(By.id("markerAddress")).sendKeys("Teststrasse 2");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.testingtesting.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Test Beschreibung 2");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("6:00-19:00");
        driver.findElement(By.xpath("//input[@value=\"fastFood\"]")).click();
        driver.findElement(By.id("medium")).click();
        driver.findElement(By.id("takeAwayOption")).click();

        // Submit form
        driver.findElement(By.name("submitButton")).click();

        // Gets id of created Marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // Returns to Index Page
        driver.get(INDEX_PAGE);

        // Clicks on created Marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // Asserts created values
        assertEquals("Test Markierung 2", driver.findElement(By.id("markerName")).getText());
        assertEquals("http://www.testingtesting.ch/", driver.findElement(By.id("markerWebsiteButton")).getAttribute("href"));
        assertEquals("Test Beschreibung 2", driver.findElement(By.id("markerDescription")).getText());
        assertEquals("Preisklasse: Mittel (10-15)", driver.findElement(By.id("markerPrice")).getText());
        assertEquals("Vegetarisch: Nein", driver.findElement(By.id("markerVegetarian")).getText());
        assertEquals("Take Away: Ja", driver.findElement(By.id("markerTakeAway")).getText());

        Thread.sleep(1000);
        driver.findElement(By.id("sidebar-marker")).click();

        // Removes Marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testShowValidMarkerDetailsAllCheckboxes() throws Exception {

        // Uses as Test location nearby Zurich Main Station
        String coordinates = "47.37712,8.53359";

        // Go to Index Page
        driver.get(INDEX_PAGE);
        Thread.sleep(1000);

        // Go to create Marker Page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);
        driver.findElement(By.id("markerName")).sendKeys("Test Markierung 3");
        driver.findElement(By.id("markerAddress")).sendKeys("Teststrasse 3");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.testing.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Test Beschreibung 3");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("8:00-20:00");
        driver.findElement(By.xpath("//input[@value=\"swiss\"]")).click();
        driver.findElement(By.id("expensive")).click();
        driver.findElement(By.id("vegetarianOption")).click();
        driver.findElement(By.id("takeAwayOption")).click();

        // Submit form
        driver.findElement(By.name("submitButton")).click();

        // Gets id of created Marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // Returns to Index Page
        driver.get(INDEX_PAGE);

        // Clicks on created Marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // Asserts created values
        assertEquals("Test Markierung 3", driver.findElement(By.id("markerName")).getText());
        assertEquals("http://www.testing.ch/", driver.findElement(By.id("markerWebsiteButton")).getAttribute("href"));
        assertEquals("Test Beschreibung 3", driver.findElement(By.id("markerDescription")).getText());
        assertEquals("Preisklasse: Teuer (> 15)", driver.findElement(By.id("markerPrice")).getText());
        assertEquals("Vegetarisch: Ja", driver.findElement(By.id("markerVegetarian")).getText());
        assertEquals("Take Away: Ja", driver.findElement(By.id("markerTakeAway")).getText());

        Thread.sleep(1000);
        driver.findElement(By.id("sidebar-marker")).click();

        // Removes Marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }
}