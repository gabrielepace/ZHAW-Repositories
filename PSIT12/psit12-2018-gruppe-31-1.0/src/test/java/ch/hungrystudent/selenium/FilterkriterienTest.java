package ch.hungrystudent.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ch.hungrystudent.dataaccess.MarkerDaoImpl;
import ch.hungrystudent.dataaccess.UserDaolmpl;
import ch.hungrystudent.domain.Kitchen;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Price;
import ch.hungrystudent.domain.User;

public class FilterkriterienTest extends SeleniumTest {
	

	private MarkerDaoImpl markerDao;
	private UserDaolmpl userDao;
	private final List<Marker> testMarkers = new ArrayList<>();
	private final List<User> testUsers = new ArrayList<>();

	@Before
	public void initDao() {
		
		markerDao = new MarkerDaoImpl();
		userDao = new UserDaolmpl();
		createSetOfDummyMarkers();
	}

	@After
	public void delTestDataFromDatabase() {
		delAllCreatedTestMarkers();
		delAllCreatedTestUsers();
	}
	
	@Test
	public void testSelectVegetarian() throws Exception {
	
		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();
	
		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);

		// Go to options
		driver.findElement(By.id("vegetarian")).click();
		
		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);
		
		// Check if the right checkbox is selected
		assertTrue(driver.findElement(By.id("vegetarian")).isSelected());
	}
	
	@Test
	public void testSelectTakeAway() throws Exception {
	
		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();
	
		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);

		// Go to options
		driver.findElement(By.id("takeAway")).click();
		
		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// Check if the right checkbox is selected
		assertTrue(driver.findElement(By.id("takeAway")).isSelected());
	}
	
	@Test
	public void testSelectVegetarianAndTakeAway() throws Exception {
	
		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();
	
		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);

		// Go to options
		driver.findElement(By.id("vegetarian")).click();
		driver.findElement(By.id("takeAway")).click();
		Thread.sleep(1000);
		
		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// Check if the right checkbox is selected
		assertTrue(driver.findElement(By.id("vegetarian")).isSelected());
		assertTrue(driver.findElement(By.id("takeAway")).isSelected());
	}
	
	@Test
	public void testSelectAsianAsKitchen() throws Exception {

		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();

		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);

		// Go to kitchen part
		driver.findElement(By.xpath("//input[@value=\"asian\"]")).click();

		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// Check if the right kitchen checkbox is selected
		assertEquals("true", driver.findElement(By.xpath("//input[@value='asian']")).getAttribute("checked"));
	}
	
	@Test
	public void testSelectBarAsKitchen() throws Exception {

		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();

		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);

		// Go to kitchen part
		driver.findElement(By.xpath("//input[@value=\"bars\"]")).click();

		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// Check if the right kitchen checkbox is selected
		assertEquals("true", driver.findElement(By.xpath("//input[@value='bars']")).getAttribute("checked"));
	}

	@Test
	public void testSwissAsKitchen() throws Exception {

		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();

		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);

		// Go to kitchen part
		driver.findElement(By.xpath("//input[@value=\"swiss\"]")).click();

		// submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// check if the right kitchen checkbox is selected
		assertEquals("true", driver.findElement(By.xpath("//input[@value='swiss']")).getAttribute("checked"));
	}
	
	@Test
	public void testEuropeanAsKitchen() throws Exception {

		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();

		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);

		// Go to kitchen part
		driver.findElement(By.xpath("//input[@value=\"european\"]")).click();

		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// Sheck if the right kitchen checkbox is selected
		assertEquals("true", driver.findElement(By.xpath("//input[@value='european']")).getAttribute("checked"));
	}
	
	@Test
	public void testMarkerWithoutCheckedKitchen() throws Exception {

		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();

		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);

		// Go to kitchen part
		driver.findElement(By.xpath("//input[@value=\"fastFood\"]")).sendKeys(Keys.SPACE);
		driver.findElement(By.xpath("//input[@value=\"cafes\"]")).sendKeys(Keys.SPACE);

		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// Check if the right kitchen checkbox is selected
		//It gives no markers with those kitchen-properties
		assertEquals("true", driver.findElement(By.xpath("//input[@value=\"fastFood\"]")).getAttribute("checked"));
		assertEquals("true", driver.findElement(By.xpath("//input[@value=\"cafes\"]")).getAttribute("checked"));
	}

	@Test
	public void testSelectCheapPrice() throws Exception {

		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();

		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);
		
		// Select price
		driver.findElement(By.id("cheap")).click();

		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// Check if the right price is selected
		assertEquals("true", driver.findElement(By.xpath("//input[@value='cheap']")).getAttribute("checked"));

		delTestDataFromDatabase();
	}
	
	@Test
	public void testSelectMediumPrice() throws Exception {

		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();

		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);
		
		// Select price
		driver.findElement(By.id("medium")).click();

		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// Check if the right price is selected
		assertEquals("true", driver.findElement(By.xpath("//input[@value='medium']")).getAttribute("checked"));

		delTestDataFromDatabase();
	}
	
	@Test
	public void testSelectExpensivePrice() throws Exception {

		driver.get(INDEX_PAGE);
		driver.manage().window().maximize();

		// Click on menu button
		driver.findElement(By.id("menuButton")).click();
		Thread.sleep(1000);
		
		// Select price
		driver.findElement(By.id("expensive")).click();

		// Submit form
		driver.findElement(By.name("filterButton")).sendKeys(Keys.ENTER);

		// Check if the right price is selected
		assertEquals("true", driver.findElement(By.xpath("//input[@value='expensive']")).getAttribute("checked"));

		delTestDataFromDatabase();
	}
	
	private void createSetOfDummyMarkers() {
		List<Kitchen> kitchenList = new ArrayList<>();
		User testUser;

		testUser = createDummyUser();

		kitchenList.add(Kitchen.ASIAN);
		createDummyMarker(testUser, 1, kitchenList);

		kitchenList.clear();
		kitchenList.add(Kitchen.EUROPEAN);
		kitchenList.add(Kitchen.BARS);
		createDummyMarker(testUser, 2, kitchenList);

		kitchenList.clear();
		kitchenList.add(Kitchen.BARS);
		createDummyMarker(testUser, 3, kitchenList);

		kitchenList.clear();
		kitchenList.add(Kitchen.SWISS);
		createDummyMarker(testUser, 4, kitchenList);
		
		kitchenList.clear();
		kitchenList.add(Kitchen.EUROPEAN);
		kitchenList.add(Kitchen.BARS);
		createDummyMarker(testUser, 5, kitchenList);
	}

	private void keepTrackofCreatedTestMarkers(Marker marker) {
		testMarkers.add(marker);
	}

	private void delAllCreatedTestMarkers() {
		for (Marker marker : testMarkers) {

			markerDao.deleteMarker(marker.getId());
		}
		testMarkers.clear();
	}

	private void keepTrackofCreatedTestUserIDs(User user) {
		testUsers.add(user);
	}

	private void delAllCreatedTestUsers() {
		for (User user : testUsers) {
			userDao.removeUser(user);
		}
		testUsers.clear();
	}

	private User createDummyUser() {
		User dummyUser = new User(0, "DummyUser" + 1, "DummyUser" + 1 + "@mail.com", "pw" + 1);

		dummyUser.setId(userDao.createUser(dummyUser));
		keepTrackofCreatedTestUserIDs(dummyUser);

		return dummyUser;
	}

	private void createDummyMarker(User user, int number,
                                   List<Kitchen> kitchenList) {

		Marker dummyMarker = new Marker.MarkerBuilder()
				.setId(-1)
				.setName("Restaurant "+ number)
				.setAddress("Strasse " + number)
				.setWebsite("www.restaurant" + number + ".com")
				.setDescription("Das Restaurant "  + number + " war sehr gemuetlich.")
				.setOpeningHours("8:00 bis 20:00")
				.setKitchens(kitchenList)
				.setPrice(Price.MEDIUM)
				.setVegetarian(true)
				.setTakeAway(true)
				.setImage(null)
				.setLocation("47.37447, 8.53033")
				.build();

		dummyMarker.setId(markerDao.createMarker(dummyMarker, user.getId()));
		keepTrackofCreatedTestMarkers(dummyMarker);
	}
}

