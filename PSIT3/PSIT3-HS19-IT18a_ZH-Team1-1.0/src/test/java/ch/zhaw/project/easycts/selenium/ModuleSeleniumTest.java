package ch.zhaw.project.easycts.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModuleSeleniumTest extends SeleniumTest {

    private static final long sleep_time = 1200L;

    @BeforeClass
    public static void maximizeBrowserWindow() throws Exception {
        driver.manage().window().maximize();

        // Register new User as module.testing@testing.ch
        driver.get(LOGIN_PAGE);
        driver.findElement(By.id("mail")).sendKeys("test@easycts.ch");
        driver.findElement(By.id("password")).sendKeys("123123123");

        Thread.sleep(sleep_time);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(sleep_time);
    }

    @Before
    public void setup() throws Exception {
        driver.get(INDEX_PAGE);
        Thread.sleep(sleep_time);
    }

    @After
    public void teardown() throws Exception {
        Thread.sleep(sleep_time);
        driver.findElement(By.xpath("//div[@class='semester'][2]/table/tbody/tr/td[5]/button")).click();  // delete module
        Thread.sleep(sleep_time);
    }

    @Test
    public void testCreateModule() throws Exception {
        // Open Box Modal for Module creating
        driver.findElement(By.xpath("//button[@id='create-modal-btn']")).click();
        Thread.sleep(sleep_time);
        fillRequiredFields("CTIT2", "4", "IT3", "4");
        driver.findElement(By.id("mark")).sendKeys("5");
        driver.findElement(By.id("weighting")).sendKeys("20");

        // Asserts
        assertFieldsFilledCorrectly("CTIT2", "4", "IT3", "4");
        assertEquals("5", driver.findElement(By.id("mark")).getAttribute("value"));
        assertEquals("20", driver.findElement(By.id("weighting")).getAttribute("value"));

        driver.findElement(By.xpath("(//button[@id='save-module'])")).click();
        Thread.sleep(sleep_time);
        assertModule("CTIT2", "4", "4");
    }

    @Test
    public void testCreateDispensedModule() throws Exception {
        // Open Box Modal for Module creating
        driver.findElement(By.xpath("//button[@id='create-modal-btn']")).click();
        Thread.sleep(sleep_time);
        fillRequiredFields("BSY", "4", "IT3", "4");
        driver.findElement(By.id("dispensed")).isSelected();

        // Asserts
        assertFieldsFilledCorrectly("BSY", "4", "IT3", "4");
        assertTrue(driver.findElement(By.id("dispensed")).isEnabled());

        driver.findElement(By.xpath("(//button[@id='save-module'])")).click();
        Thread.sleep(sleep_time);
        assertModule("BSY", "4", "4");
    }

    @Test
    public void testAddMoreMarksInModule() throws Exception {
        // Open Box Modal for Module creating
        driver.findElement(By.xpath("//button[@id='create-modal-btn']")).click();
        Thread.sleep(sleep_time);
        fillRequiredFields("PHIT", "3", "IT3", "4");
        driver.findElement(By.id("mark")).sendKeys("5");
        driver.findElement(By.id("weighting")).sendKeys("20");
        driver.findElement(By.id("addMark")).click();
        Thread.sleep(sleep_time);
        driver.findElement(By.cssSelector(".row:nth-child(2) #mark")).sendKeys("4");
        driver.findElement(By.cssSelector(".row:nth-child(2) #weighting")).sendKeys("20");

        // Asserts
        assertFieldsFilledCorrectly("PHIT", "3", "IT3", "4");
        assertEquals("5", driver.findElement(By.id("mark")).getAttribute("value"));
        assertEquals("20", driver.findElement(By.id("weighting")).getAttribute("value"));
        assertEquals("4", driver.findElement(By.cssSelector(".row:nth-child(2) #mark")).getAttribute("value"));
        assertEquals("20", driver.findElement(By.cssSelector(".row:nth-child(2) #weighting")).getAttribute("value"));

        driver.findElement(By.xpath("(//button[@id='save-module'])")).click();
        Thread.sleep(sleep_time);
        assertModule("PHIT", "3", "4");
    }

    @Test
    public void testModuleCRUD() throws Exception {
        // Open Box Modal for Module creating
        driver.findElement(By.xpath("//button[@id='create-modal-btn']")).click();
        Thread.sleep(sleep_time);
        fillRequiredFields("MANIT3", "3", "IT3", "4");
        driver.findElement(By.id("mark")).sendKeys("5");
        driver.findElement(By.id("weighting")).sendKeys("100");

        // Asserts
        assertFieldsFilledCorrectly("MANIT3", "3", "IT3", "4");
        assertEquals("5", driver.findElement(By.id("mark")).getAttribute("value"));
        assertEquals("100", driver.findElement(By.id("weighting")).getAttribute("value"));

        driver.findElement(By.xpath("(//button[@id='save-module'])")).click();

        // Go to Index Page
        driver.get(INDEX_PAGE);
        Thread.sleep(sleep_time);

        // Edits created Module
        driver.findElement(By.xpath("(//div[@class='semester'][2]/table/tbody/tr/td/button[@type='button'])[1]")).click();
        Thread.sleep(sleep_time);
        fillRequiredFields("CTIT1", "3", "IT3", "4");
        driver.findElement(By.id("mark")).clear();
        driver.findElement(By.id("mark")).sendKeys("5");
        driver.findElement(By.id("weighting")).clear();
        driver.findElement(By.id("weighting")).sendKeys("20");

        // Asserts
        assertFieldsFilledCorrectly("CTIT1", "3", "IT3", "4");
        assertEquals("5", driver.findElement(By.id("mark")).getAttribute("value"));
        assertEquals("20", driver.findElement(By.id("weighting")).getAttribute("value"));

        driver.findElement(By.xpath("(//button[@id='save-module'])")).click();
        Thread.sleep(sleep_time);
        assertModule("CTIT1", "3", "4");
    }

    // ---------- helper methods ----------
    private void fillRequiredFields(String name, String sem, String moduleGroup, String ects) {
        driver.findElement(By.xpath("//input[@id='name']")).clear();
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys(name);
        driver.findElement(By.id("semester")).clear();
        driver.findElement(By.id("semester")).sendKeys(sem);
        driver.findElement(By.xpath("(//input[@id='name'])[2]")).clear();
        driver.findElement(By.xpath("(//input[@id='name'])[2]")).sendKeys(moduleGroup);
        driver.findElement(By.id("ects")).clear();
        driver.findElement(By.id("ects")).sendKeys(ects);
    }

    private void assertFieldsFilledCorrectly(String name, String sem, String moduleGroup, String ects) {
        assertEquals(name, driver.findElement(By.xpath("//input[@id='name']")).getAttribute("value"));
        assertEquals(sem, driver.findElement(By.id("semester")).getAttribute("value"));
        assertEquals(moduleGroup, driver.findElement(By.xpath("(//input[@id='name'])[2]")).getAttribute("value"));
        assertEquals(ects, driver.findElement(By.id("ects")).getAttribute("value"));
    }

    private void assertModule(String name, String sem, String ects) {
        assertEquals(sem + ". Semester", driver.findElement(By.xpath("//div[@class='semester'][2]/table/thead/tr/th[1]")).getText());
        assertEquals(name, driver.findElement(By.xpath("//div[@class='semester'][2]/table/tbody/tr/td[1]")).getText());
        assertEquals(ects, driver.findElement(By.xpath("//div[@class='semester'][2]/table/tbody/tr/td[2]")).getText());
    }
}