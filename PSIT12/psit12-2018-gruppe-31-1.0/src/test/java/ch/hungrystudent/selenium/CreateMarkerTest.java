package ch.hungrystudent.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.logging.Logger;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class CreateMarkerTest extends SeleniumTest {

    private static final Logger LOGGER = Logger.getLogger(CreateMarkerTest.class.getName());

    @Before
    public void loginAsUser() {

        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("inputEmail")).sendKeys("selenium@testing.ch");
        driver.findElement(By.id("inputPassword")).sendKeys("selenium123");
        driver.findElement(By.xpath("//button[@value='login']")).click();
    }

    @Test
    public void testCreateMarker(){

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

        // Clicks on created Marker
        driver.get(INDEX_PAGE + "?marker=" + markerNr);

        // Asserts created values
        assertEquals("Test Cafe", driver.findElement(By.id("markerName")).getText());
        assertEquals("Teststrasse 99", driver.findElement(By.id("markerAddress")).getText());
        assertEquals("http://www.testcafe.ch/", driver.findElement(By.id("markerWebsiteButton")).getAttribute("href"));
        assertEquals("Ein ruhiges Cafe in der Altstadt", driver.findElement(By.id("markerDescription")).getText());
        assertEquals("06:30-20:00", driver.findElement(By.id("markerOpeningHours")).getText());
        assertEquals("Preisklasse: Billig (< 10)", driver.findElement(By.id("markerPrice")).getText());
        assertEquals("Vegetarisch: Nein", driver.findElement(By.id("markerVegetarian")).getText());
        assertEquals("Take Away: Ja", driver.findElement(By.id("markerTakeAway")).getText());

        // Removes Marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testPriceRange() {

        // Test location
        String coordinates = "47.37675,8.64076";

        // Go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        //Identifying radio buttons using the ID

        WebElement cheapRadioBtn = driver.findElement(By.id("cheap"));
        WebElement mediumRadioBtn = driver.findElement(By.id("medium"));
        WebElement expensiveRadioBtn = driver.findElement(By.id("expensive"));


        //Checking if the Radio button is displayed on the Webpage and logging the status

        boolean cheapRadioBtnIsDisplayed = cheapRadioBtn.isDisplayed();
        LOGGER.info("Is cheap radio button displayed: " + cheapRadioBtnIsDisplayed);

        boolean mediumRadioBtnIsDisplayed = mediumRadioBtn.isDisplayed();
        LOGGER.info("Is medium radio button displayed: "+ mediumRadioBtnIsDisplayed);

        boolean expensiveRadioBtnIsDisplayed = expensiveRadioBtn.isDisplayed();
        LOGGER.info("Is expensive radio button displayed: "+ expensiveRadioBtnIsDisplayed);


        //Checking if the Radio button is enabled on the webpage and logging the status

        boolean cheapRadioBtnIsEnabled = cheapRadioBtn.isEnabled();
        LOGGER.info("Is cheap radio button enabled: "+ cheapRadioBtnIsEnabled);

        boolean mediumRadioBtnIsEnabled = mediumRadioBtn.isEnabled();
        LOGGER.info("Is medium radio button enabled: "+ mediumRadioBtnIsEnabled);

        boolean expensiveRadioBtnIsEnabled = expensiveRadioBtn.isEnabled();
        LOGGER.info("Is expensive radio button enabled: "+ expensiveRadioBtnIsEnabled);


        //Checking the default radio button selection status

        boolean cheapRadioBtnIsSelected = cheapRadioBtn.isSelected();
        LOGGER.info("Default cheap Radio button selection Status: "+cheapRadioBtnIsSelected);

        boolean mediumRadioBtnIsSelected = mediumRadioBtn.isSelected();
        LOGGER.info("Default medium Radio button selection Status: "+ mediumRadioBtnIsSelected);

        boolean expensiveRadioBtnIsSelected = expensiveRadioBtn.isSelected();
        LOGGER.info("Default expensive Radio button selection Status: "+ expensiveRadioBtnIsSelected);


        //Fills in the form
        driver.findElement(By.id("markerName")).sendKeys("Test Restaurant 2");
        driver.findElement(By.id("markerAddress")).sendKeys("Teststrasse 2");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.testrestaurant2.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Ein Restaurant in der Stadt");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("10:30-20:00");
        driver.findElement(By.xpath("//input[@value=\"asian\"]")).click();
        expensiveRadioBtn.click();


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


        // Check if the right radio box is selected
        assertNotEquals("Preisklasse: Billig (< 10)", driver.findElement(By.id("markerPrice")).getText());
        assertNotEquals("Preisklasse: Mittel (10-15)", driver.findElement(By.id("markerPrice")).getText());
        assertEquals("Preisklasse: Teuer (> 15)", driver.findElement(By.id("markerPrice")).getText());

        // Removes Marker
        driver.findElement(By.id("markerRemoveButton")).click();

    }

    @Test
    public void testSelectedKitchen(){

        // Test location
        String coordinates = "47.37785,8.54176";

        // Go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);


        //Identifying radio buttons using the ID

        WebElement asianCheckbox = driver.findElement(By.id("asian"));
        WebElement fastFoodCheckbox = driver.findElement(By.id("fastFood"));
        WebElement cafesCheckbox = driver.findElement(By.id("cafes"));
        WebElement europeanCheckbox = driver.findElement(By.id("european"));
        WebElement swissCheckbox = driver.findElement(By.id("swiss"));
        WebElement barsCheckbox = driver.findElement(By.id("bars"));


        //Checking if the Checkbox button is displayed on the Webpage and logging the status

        boolean asianCheckboxIsDisplayed = asianCheckbox.isDisplayed();
        LOGGER.info("Is asian checkbox button displayed: " + asianCheckboxIsDisplayed);

        boolean fastFoodCheckboxIsDisplayed = fastFoodCheckbox.isDisplayed();
        LOGGER.info("Is fast food checkbox button displayed: "+ fastFoodCheckboxIsDisplayed);

        boolean cafesCheckboxIsDisplayed = cafesCheckbox.isDisplayed();
        LOGGER.info("Is cafes checkbox button displayed: "+ cafesCheckboxIsDisplayed);

        boolean europeanCheckboxIsDisplayed = europeanCheckbox.isDisplayed();
        LOGGER.info("Is european checkbox button displayed: "+ europeanCheckboxIsDisplayed);

        boolean swissCheckboxIsDisplayed = swissCheckbox.isDisplayed();
        LOGGER.info("Is swiss checkbox button displayed: "+ swissCheckboxIsDisplayed);

        boolean barsCheckboxIsDisplayed = barsCheckbox.isDisplayed();
        LOGGER.info("Is swiss checkbox button displayed: "+ barsCheckboxIsDisplayed);


        //Checking if the Radio button is enabled on the webpage and logging the status

        boolean asianCheckboxIsEnabled = asianCheckbox.isEnabled();
        LOGGER.info("Is asian checkbox enabled: "+ asianCheckboxIsEnabled);

        boolean fastFoodCheckboxIsEnabled = fastFoodCheckbox.isEnabled();
        LOGGER.info("Is fast food checkbox button enabled: "+ fastFoodCheckboxIsEnabled);

        boolean cafesCheckboxIsEnabled = cafesCheckbox.isEnabled();
        LOGGER.info("Is cafes checkbox button enabled: "+ cafesCheckboxIsEnabled);

        boolean europeanCheckboxRadioBtnIsEnabled = europeanCheckbox.isEnabled();
        LOGGER.info("Is european checkbox button enabled: "+ europeanCheckboxRadioBtnIsEnabled);

        boolean swissCheckboxIsEnabled = swissCheckbox.isEnabled();
        LOGGER.info("Is swiss checkbox button enabled: "+ swissCheckboxIsEnabled);

        boolean barsCheckboxIsEnabled = barsCheckbox.isEnabled();
        LOGGER.info("Is swiss checkbox button enabled: "+ barsCheckboxIsEnabled);


        //Checking the default radio button selection status

        boolean asianCheckboxIsSelected = asianCheckbox.isSelected();
        LOGGER.info("Default asian checkbox button selection Status: "+ asianCheckboxIsSelected);

        boolean fastFoodCheckboxIsSelected = fastFoodCheckbox.isSelected();
        LOGGER.info("Default fast food checkbox selection Status: "+ fastFoodCheckboxIsSelected);

        boolean cafesCheckboxIsSelected = cafesCheckbox.isSelected();
        LOGGER.info("Default cafes checkbox button selection Status: "+ cafesCheckboxIsSelected);

        boolean europeanCheckboxIsSelected = europeanCheckbox.isSelected();
        LOGGER.info("Default european checkbox  button selection Status: "+ europeanCheckboxIsSelected);

        boolean swissCheckboxIsSelected = swissCheckbox.isSelected();
        LOGGER.info("Default swiss checkbox button selection Status: "+ swissCheckboxIsSelected);

        boolean barsCheckboxIsSelected = barsCheckbox.isSelected();
        LOGGER.info("Default swiss checkbox button selection Status: "+ barsCheckboxIsSelected);


        //Fills in the form
        driver.findElement(By.id("markerName")).sendKeys("Test Restaurant 2");
        driver.findElement(By.id("markerAddress")).sendKeys("Teststrasse 2");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.testrestaurant2.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Ein Restaurant in der Stadt");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("10:30-20:00");
        swissCheckbox.click();
        europeanCheckbox.click();
        barsCheckbox.click();
        driver.findElement(By.id("expensive")).click();


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


        // Check if the right checkbox is selected
        List<WebElement> selectedKitchens = driver.findElement(By.id("kitchen"))
                .findElement(By.tagName("div"))
                .findElements(By.tagName("p"));
        for(WebElement element : selectedKitchens) {
            assertNotEquals("Asiatische Küche", element.getText());
            assertNotEquals("Fast Food", element.getText());
            assertNotEquals("Cafes", element.getText());
        }

        assertEquals(3, selectedKitchens.size());

        // Removes Marker
        driver.findElement(By.id("markerRemoveButton")).click();

    }

    @Test
    public void testOptions(){

        // Test location
        String coordinates = "47.27775,8.54276";

        // Go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);


        //Identifying checkbox buttons using the ID

        WebElement vegetarianOption = driver.findElement(By.id("vegetarianOption"));
        WebElement takeAwayOption = driver.findElement(By.id("takeAwayOption"));

        //Checking if the Checkbox button is displayed on the Webpage and logging the status

        boolean vegetarianOptionIsDisplayed = vegetarianOption.isDisplayed();
        LOGGER.info("Is asian checkbox button displayed: " + vegetarianOptionIsDisplayed);

        boolean takeAwayOptionIsDisplayed = takeAwayOption.isDisplayed();
        LOGGER.info("Is fast food checkbox button displayed: "+ takeAwayOptionIsDisplayed);


        //Checking if the Checkbox button is enabled on the webpage and logging the status

        boolean vegetarianOptionIsEnabled = vegetarianOption.isEnabled();
        LOGGER.info("Is asian checkbox enabled: "+ vegetarianOptionIsEnabled);

        boolean takeAwayOptionIsEnabled = takeAwayOption.isEnabled();
        LOGGER.info("Is fast food checkbox button enabled: "+ takeAwayOptionIsEnabled);


        //Checking the default checkbox button selection status

        boolean vegetarianOptionIsSelected = vegetarianOption.isSelected();
        LOGGER.info("Default vegetarian checkbox button selection Status: "+vegetarianOptionIsSelected);

        boolean takeAwayOptionIsSelected = takeAwayOption.isSelected();
        LOGGER.info("Default take away checkbox selection Status: "+ takeAwayOptionIsSelected);


        //Fills in the form
        driver.findElement(By.id("markerName")).sendKeys("Test Restaurant 3");
        driver.findElement(By.id("markerAddress")).sendKeys("Teststrasse 3");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.testrestaurant3.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Das Restaurant in der Stadt");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("11:30-20:00");
        vegetarianOption.click();
        driver.findElement(By.id("cheap")).click();


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

        // Check if the right checkbox is selected
        assertEquals("Take Away: Nein", driver.findElement(By.id("markerTakeAway")).getText());
        assertEquals("Vegetarisch: Ja", driver.findElement(By.id("markerVegetarian")).getText());

        // Removes Marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }

    @Test
    public void testCreateMarkerWithUmlaut(){

        // Test location
        String coordinates = "47.37277,8.54043";

        // Go to create page
        driver.get(CREATE_PAGE + "?coordinates=" + coordinates);

        driver.findElement(By.id("markerName")).sendKeys("Test Café?");
        driver.findElement(By.id("markerAddress")).sendKeys("Teststrässe 99");
        driver.findElement(By.id("markerWebsite")).sendKeys("www.testcafe.ch");
        driver.findElement(By.id("markerDescription")).sendKeys("Ein rühiges Cäfè in där Altst£dt");
        driver.findElement(By.id("markerBusinessHours")).sendKeys("06:30-20:00");

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
        assertEquals("Test Café?", driver.findElement(By.id("markerName")).getText());
        assertEquals("Teststrässe 99", driver.findElement(By.id("markerAddress")).getText());
        assertEquals("http://www.testcafe.ch/", driver.findElement(By.id("markerWebsiteButton")).getAttribute("href"));
        assertEquals("Ein rühiges Cäfè in där Altst£dt", driver.findElement(By.id("markerDescription")).getText());
        assertEquals("06:30-20:00", driver.findElement(By.id("markerOpeningHours")).getText());

        // Removes Marker
        driver.findElement(By.id("markerRemoveButton")).click();
    }
}
