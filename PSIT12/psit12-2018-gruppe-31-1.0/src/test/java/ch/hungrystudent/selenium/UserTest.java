package ch.hungrystudent.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest extends SeleniumTest {

    private static final Logger LOGGER = Logger.getLogger(UserTest.class.getName());

    @Before
    public void maximizeBrowserWindow() {
        driver.manage().window().maximize();
    }

    private void removeUser() {
        try {
            // Removes UserTest
            driver.get(USERSETTINGS_PAGE);

            driver.findElement(By.id("userRemoveButton")).click();

            // Go to Index Page
            driver.get(INDEX_PAGE);
        } catch (NoSuchElementException e) {
            LOGGER.log(Level.SEVERE, "Couldn't logout as user properly. " +
                    "User might not be logged in before.", e);
        }
    }

    private void registerUser(String username) {

        // Register new User as UserTest
        driver.get(REGISTER_PAGE);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("email")).sendKeys(username + "@testing.ch");
        driver.findElement(By.id("password")).sendKeys("test123");
        driver.findElement(By.id("confirmPassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@name='register']")).click();

        // Go to Index Page
        driver.get(INDEX_PAGE);
    }

    @Test
    public void registerInvalidEmail() throws Exception {
    	
        driver.get(REGISTER_PAGE);
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys("TestUser");
        // Test invalid Email
        driver.findElement(By.id("email")).sendKeys("%%$^&%@mail.ch");
        driver.findElement(By.id("password")).sendKeys("test123");
        driver.findElement(By.id("confirmPassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@name='register']")).click();
        Thread.sleep(1000);
        assertTrue(driver.findElement(By.id("errorMessage")).isDisplayed());
    }
    
    @Test
    public void registerInvalidName(){
    	
        driver.get(REGISTER_PAGE);
        // Test invalid Name
        driver.findElement(By.id("username")).sendKeys("T%&/()");
        driver.findElement(By.id("email")).sendKeys("mail@mail.ch");
        driver.findElement(By.id("password")).sendKeys("test123");
        driver.findElement(By.id("confirmPassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@name='register']")).click();
        assertTrue(driver.findElement(By.id("errorMessage")).isDisplayed());
    }
    
    @Test
    public void loginInvalidEmail() {
    	
        driver.get(LOGIN_PAGE);
        // Test invalid Email
        driver.findElement(By.id("inputEmail")).sendKeys(".%f@mail.ch");
        driver.findElement(By.id("inputPassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@value='login']")).click();
        assertTrue(driver.findElement(By.id("errorMessage")).isDisplayed());
    }

    @Test
    public void testUserUpdateOnlyUsername() {
        try {
            String username = "TestUser";
        	registerUser(username);
       
	        // Go to User Settings Page
	        driver.get(USERSETTINGS_PAGE);
	
	        // Edits only Username
	        driver.findElement(By.id("username")).clear();
	        driver.findElement(By.id("username")).sendKeys("TestUserEditedUsername");
	        driver.findElement(By.id("password")).sendKeys("test123");
	        driver.findElement(By.id("confirmPassword")).sendKeys("test123");
	        driver.findElement(By.xpath("//input[@name='submitButton']")).click();
	
	        // Returns Index Page
	        driver.get(INDEX_PAGE);
	
	        // Go to User Settings Page for Asserts
	        driver.get(USERSETTINGS_PAGE);
	        assertEquals("TestUserEditedUsername", driver.findElement(By.id("username")).getAttribute("value"));
	        assertEquals(username + "@testing.ch", driver.findElement(By.id("emailAddress")).getAttribute("value"));
        
        } finally {
        	
        	removeUser();
        }
    }

    @Test
    public void testUserUpdateOnlyEmail() {
    	try {
            String username = "TestUser2";
            registerUser(username);

            // Go to User Settings Page
            driver.get(USERSETTINGS_PAGE);

            // Edits only Email
            driver.findElement(By.id("emailAddress")).clear();
            driver.findElement(By.id("emailAddress")).sendKeys("testuser.edited.email@testing.ch");
            driver.findElement(By.id("password")).sendKeys("test123");
            driver.findElement(By.id("confirmPassword")).sendKeys("test123");
            driver.findElement(By.xpath("//input[@name='submitButton']")).click();

            // Returns Index Page
            driver.get(INDEX_PAGE);

            // Go to User Settings Page for Asserts
            driver.get(USERSETTINGS_PAGE);
            assertEquals(username, driver.findElement(By.id("username")).getAttribute("value"));
            assertEquals("testuser.edited.email@testing.ch", driver.findElement(By.id("emailAddress")).getAttribute("value"));
            
    	} finally {
        
    		removeUser();
    	}
    }

    @Test
    public void testUserUpdateOnlyPassword(){
    	try {
            String username = "TestUser3";
            registerUser(username);

            // Go to User Settings Page
            driver.get(USERSETTINGS_PAGE);

            // Edit only password
            driver.findElement(By.id("password")).sendKeys("asdf");
            driver.findElement(By.id("confirmPassword")).sendKeys("asdf");
            driver.findElement(By.xpath("//input[@name='submitButton']")).click();

            // logout as user
            driver.get(LOGOUT_PAGE);

            // Return login page
            driver.get(LOGIN_PAGE);

            // try login with new password
            driver.findElement(By.id("inputEmail")).sendKeys(username + "@testing.ch");
            driver.findElement(By.id("inputPassword")).sendKeys("asdf");
            driver.findElement(By.xpath("//button[@value='login']")).click();

            assertEquals(INDEX_PAGE, driver.getCurrentUrl());
    	} finally {
    		removeUser();
    	}
    }

    @Test
    public void testUserUpdateAll() throws Exception {
    	try {
            String username = "TestUser4";
            registerUser(username);

            // Go to User Settings Page
            driver.get(USERSETTINGS_PAGE);

            // Edits All (Username, Email and Password)
            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("username")).sendKeys("TestUserEditedUser");
            driver.findElement(By.id("emailAddress")).clear();
            driver.findElement(By.id("emailAddress")).sendKeys("testuser.edited.user@testing.ch");
            driver.findElement(By.id("password")).sendKeys("asdf");
            driver.findElement(By.id("confirmPassword")).sendKeys("asdf");
            driver.findElement(By.xpath("//input[@name='submitButton']")).click();

            Thread.sleep(1000);

            // logout as user
            driver.get(LOGOUT_PAGE);

            // try login with new password
            driver.findElement(By.id("inputEmail")).sendKeys("testuser.edited.user@testing.ch");
            driver.findElement(By.id("inputPassword")).sendKeys("asdf");
            driver.findElement(By.xpath("//button[@value='login']")).click();

            assertEquals(INDEX_PAGE, driver.getCurrentUrl());

            // Go to User Settings Page for Asserts
            driver.get(USERSETTINGS_PAGE);
            assertEquals("TestUserEditedUser", driver.findElement(By.id("username")).getAttribute("value"));
            assertEquals("testuser.edited.user@testing.ch", driver.findElement(By.id("emailAddress")).getAttribute("value"));
    	} finally {
    		removeUser();
    	}
    }

    @Test
    public void testLoginCreatedUser() {
    	try {
            String username = "TestUser5";
            registerUser(username);

            // Login with created User
            driver.get(LOGIN_PAGE);
            driver.findElement(By.id("inputEmail")).sendKeys(username + "@testing.ch");
            driver.findElement(By.id("inputPassword")).sendKeys("test123");
            driver.findElement(By.xpath("//button[@value='login']")).click();
    	
    	} finally {
    		removeUser();
    	}
    }

    @Test
    public void testUserCRUD() throws Exception {
    	try {
            String username = "TestUser6";
            registerUser(username);

            // Logout with created User
            driver.findElement(By.linkText("Logout")).click();

            // Login with created User
            driver.get(LOGIN_PAGE);
            driver.findElement(By.id("inputEmail")).sendKeys(username + "@testing.ch");
            driver.findElement(By.id("inputPassword")).sendKeys("test123");
            driver.findElement(By.xpath("//button[@value='login']")).click();

            Thread.sleep(1000);

            // Go to User Settings Page
            driver.get(USERSETTINGS_PAGE);

            // Edits All (Username, Email and Password)
            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("emailAddress")).clear();
            driver.findElement(By.id("emailAddress")).sendKeys(username + "@testing.ch");
            driver.findElement(By.id("password")).sendKeys("asdf");
            driver.findElement(By.id("confirmPassword")).sendKeys("asdf");
            driver.findElement(By.xpath("//input[@name='submitButton']")).click();

            Thread.sleep(1000);

            // Logout with created User (edited)
            driver.get(LOGOUT_PAGE);

            // Login with created User (edited)
            driver.get(LOGIN_PAGE);
            driver.findElement(By.id("inputEmail")).sendKeys(username + "@testing.ch");
            driver.findElement(By.id("inputPassword")).sendKeys("asdf");
            driver.findElement(By.xpath("//button[@value='login']")).click();
    	} finally {
    		removeUser();
    	}
    }
}