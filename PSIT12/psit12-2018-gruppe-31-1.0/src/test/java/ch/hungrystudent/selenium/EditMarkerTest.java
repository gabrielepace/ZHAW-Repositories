package ch.hungrystudent.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class EditMarkerTest extends SeleniumTest {

    @Before
    public void loginAsUser() {

        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("inputEmail")).sendKeys("selenium@testing.ch");
        driver.findElement(By.id("inputPassword")).sendKeys("selenium123");
        driver.findElement(By.xpath("//button[@value='login']")).click();
    }
    

    @Test
    public void testEditMarker() throws InterruptedException {

        // uses Zurich See as test location
        String coordinates = "47.34235,8.55088";

        // go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        // fill out form for new marker
        driver.findElement(By.id("markerName")).sendKeys("Selenium Test Edit 1");
        driver.findElement(By.id("markerAddress")).sendKeys("editstrasse 1");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.editamarker.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("This is a test to which checks editing markers");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("Never");
        driver.findElement(By.xpath("//input[@value=\"fastFood\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"european\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"bars\"]")).click();
        driver.findElement(By.id("medium")).click();
        driver.findElement(By.id("vegetarianOption")).click();

        // submit form
        driver.findElement(By.name("submitButton")).click();

        // get id of created marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // goes to edit page
        driver.findElement(By.id("markerEditButton")).click();

        // edits marker
        Thread.sleep(1000);
        driver.findElement(By.id("markerName")).clear();
        driver.findElement(By.id("markerName")).sendKeys("Selenium-Test-Edit");
        driver.findElement(By.id("markerAddress")).clear();
        driver.findElement(By.id("markerAddress")).sendKeys("editstrasse 2");
        driver.findElement(By.id("markerWebsite")).clear();
        driver.findElement(By.id("markerWebsite")).sendKeys("www.editmarkers.ch");
        driver.findElement(By.id("markerDescription")).clear();
        driver.findElement(By.id("markerDescription")).sendKeys("This is a test which checks editing markers");
        driver.findElement(By.id("markerBusinessHours")).clear();
        driver.findElement(By.id("markerBusinessHours")).sendKeys("Sometimes");
        // edit kitchens
        driver.findElement(By.xpath("//input[@value=\"asian\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"fastFood\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"cafes\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"european\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"swiss\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"bars\"]")).click();
        // edit price
        driver.findElement(By.id("expensive")).click();
        // edit options
        driver.findElement(By.id("vegetarianOption")).click();
        driver.findElement(By.id("takeAwayOption")).click();

        // submit edits
        driver.findElement(By.name("submitButton")).click();

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // asserts edited values
        assertEquals("Selenium-Test-Edit", driver.findElement(By.id("markerName")).getText());
        assertEquals("http://www.editmarkers.ch/", driver.findElement(By.id("markerWebsiteButton")).getAttribute("href"));
        assertEquals("This is a test which checks editing markers", driver.findElement(By.id("markerDescription")).getText());
        assertEquals("Preisklasse: Teuer (> 15)", driver.findElement(By.id("markerPrice")).getText());
        assertEquals("Vegetarisch: Nein", driver.findElement(By.id("markerVegetarian")).getText());
        assertEquals("Take Away: Ja", driver.findElement(By.id("markerTakeAway")).getText());

        List<WebElement> selectedKitchens = driver.findElement(By.id("kitchen"))
                .findElement(By.tagName("div"))
                .findElements(By.tagName("p"));
        for(WebElement element : selectedKitchens) {
            assertNotEquals("Fast Food", element.getText());
            assertNotEquals("Europäische Küche", element.getText());
            assertNotEquals("Bars", element.getText());
        }
        assertEquals(3, selectedKitchens.size());

        // removes marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testThatPriceIsAppliedCorrectlyWhenEditing() {

        // uses Zurich See as test location
        String coordinates = "47.34236,8.55085";

        // go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        // fill out form for new marker
        driver.findElement(By.id("markerName")).sendKeys("Selenium Test Edit 2");
        driver.findElement(By.id("markerAddress")).sendKeys("editstrasse 2");
        driver.findElement(By.id("markerDescription")).sendKeys("Tests if Prices are applied correctly when editing");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.editmarkers.ch");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("Never");
        driver.findElement(By.id("medium")).click();

        // submit form
        driver.findElement(By.name("submitButton")).click();

        // get id of created marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // check if price is displayed correctly
        assertEquals("Preisklasse: Mittel (10-15)", driver.findElement(By.id("markerPrice")).getText());

        // goes to edit page
        driver.findElement(By.id("markerEditButton")).click();

        // check if the right radio box is selected
        assertNull(driver.findElement(By.id("cheap")).getAttribute("checked"));
        assertEquals("true", driver.findElement(By.id("medium")).getAttribute("checked"));
        assertNull(driver.findElement(By.id("expensive")).getAttribute("checked"));

        // submit edits
        driver.findElement(By.name("submitButton")).click();

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // removes marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testThatKitchensAreAppliedCorrectlyWhenEditing() {

        // uses Zurich See as test location
        String coordinates = "47.34237,8.55087";

        // go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        // fill out form for new marker
        driver.findElement(By.id("markerName")).sendKeys("Selenium Test Edit 3");
        driver.findElement(By.id("markerAddress")).sendKeys("eitstrasse 3");
        driver.findElement(By.id("markerDescription")).sendKeys("Tests if Kitchens are applied correctly when editing");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.editmarkers.ch");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("Never");
        driver.findElement(By.xpath("//input[@value=\"asian\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"european\"]")).click();

        // submit form
        driver.findElement(By.name("submitButton")).click();

        // get id of created marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // goes to edit page
        driver.findElement(By.id("markerEditButton")).click();

        // check if the right kitchen checkboxes are selected
        assertEquals("true", driver.findElement(By.xpath("//input[@value='asian']")).getAttribute("checked"));
        assertNull(driver.findElement(By.xpath("//input[@value='fastFood']")).getAttribute("checked"));
        assertNull(driver.findElement(By.xpath("//input[@value='cafes']")).getAttribute("checked"));
        assertEquals("true", driver.findElement(By.xpath("//input[@value='european']")).getAttribute("checked"));
        assertNull(driver.findElement(By.xpath("//input[@value='swiss']")).getAttribute("checked"));
        assertNull(driver.findElement(By.xpath("//input[@value='bars']")).getAttribute("checked"));

        // submit edits
        driver.findElement(By.name("submitButton")).click();

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // removes marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testThatOptionsAreAppliedCorrectlyWhenEditing() {

        // uses Zurich See as test location
        String coordinates = "47.34237,8.55087";

        // go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        // fill out form for new marker
        driver.findElement(By.id("markerName")).sendKeys("Selenium Test Edit 4");
        driver.findElement(By.id("markerAddress")).sendKeys("editstrasse 4");
        driver.findElement(By.id("markerDescription")).sendKeys("Tests if Kitchens are applied correctly when editing");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.editmarkers.ch");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("Never");
        driver.findElement(By.id("vegetarianOption")).click();
        driver.findElement(By.id("takeAwayOption")).click();

        // submit form
        driver.findElement(By.name("submitButton")).click();

        // get id of created marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // goes to edit page
        driver.findElement(By.id("markerEditButton")).click();

        // check if the right kitchen checkboxes are selected
        assertEquals("true", driver.findElement(By.id("vegetarianOption")).getAttribute("checked"));
        assertEquals("true", driver.findElement(By.id("takeAwayOption")).getAttribute("checked"));

        // submit edits
        driver.findElement(By.name("submitButton")).click();

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // removes marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testThatCancellingEditWontChangeValues() {

        // uses Zurich See as test location
        String coordinates = "47.34233,8.55085";

        // go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        // fill out form for new marker
        driver.findElement(By.id("markerName")).sendKeys("Selenium Test Edit 5");
        driver.findElement(By.id("markerAddress")).sendKeys("editstrasse 5");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.testmarkers.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Tests that cancelling edit won't change the values");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("Never");
        driver.findElement(By.id("vegetarianOption")).click();

        // submit form
        driver.findElement(By.name("submitButton")).click();

        // get id of created marker
        WebElement markerValueTag = driver.findElement(By.xpath("//div[@data-marker-coord='" + coordinates + "']"));
        String markerNr = markerValueTag.getAttribute("data-marker-id");
        assertNotNull(markerNr);

        // clicks on created marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // goes to edit page
        driver.findElement(By.id("markerEditButton")).click();

        // change values
        driver.findElement(By.id("markerWebsite")).clear();
        driver.findElement(By.id("markerWebsite")).sendKeys("www.editmarkers.ch");
        driver.findElement(By.id("markerDescription")).clear();
        driver.findElement(By.id("markerDescription")).sendKeys("This description is wrong");
        driver.findElement(By.id("vegetarianOption")).click();
        driver.findElement(By.id("takeAwayOption")).click();

        // goes back to the marker without saving
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // asserts unedited values
        assertEquals("http://www.testmarkers.ch/", driver.findElement(By.id("markerWebsiteButton")).getAttribute("href"));
        assertEquals("Tests that cancelling edit won't change the values", driver.findElement(By.id("markerDescription")).getText());
        assertEquals("Vegetarisch: Ja", driver.findElement(By.id("markerVegetarian")).getText());
        assertEquals("Take Away: Nein", driver.findElement(By.id("markerTakeAway")).getText());

        // removes marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }
}
