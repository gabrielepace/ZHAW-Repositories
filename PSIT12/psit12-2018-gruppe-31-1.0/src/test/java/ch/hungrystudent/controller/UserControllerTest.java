package ch.hungrystudent.controller;

import ch.hungrystudent.dataaccess.UserDao;
import ch.hungrystudent.domain.Kitchen;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Price;
import ch.hungrystudent.domain.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * User Controller Test class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public class UserControllerTest {

	private UserController userController;
	private UserDao mockUserDao;


	@Before
	public void init() {
		mockUserDao = mock(UserDao.class);
		userController = new UserController(mockUserDao);
	}

	@Test
	public void testDoActionFunctionMapping() {

		User user = new User(1, "mustermann", "mustermann@muster.ch", "abc123");

		// adding user
		when(mockUserDao.checkAvailability("mustermann", "mustermann@muster.ch")).thenReturn(true);
		when(mockUserDao.createUser(user)).thenReturn((long) 1);
		assertTrue(userController.doAction("add", user));

		// login as user
		when(mockUserDao.getUser("mustermann@muster.ch", "abc123")).thenReturn(user);
		assertTrue(userController.doAction("login", user));

		// editing user
		user.setEmail("test@testing.ch");
		assertTrue(userController.doAction("edit", user));
		assertEquals("test@testing.ch", userController.getUser().getEmail());

		// logout
		assertFalse(userController.doAction("logout", user));
		assertEquals(-1, userController.getUser().getId());

		// remove user
		assertTrue(userController.doAction("remove", user));
		assertEquals(-1, userController.getUser().getId());
	}

	@Test
	public void testLoadCreatedMarkers() {

		User user = new User(1, "max", "email@email.com", "pw");
		userController.setUser(user);
		List<Marker> markerList = new ArrayList<>();
		markerList.add(getDummyMarker(1, "Resti"));
		markerList.add(getDummyMarker(2, "Bistro"));

		when(mockUserDao.getCreatedOfUser(1)).thenReturn(markerList);
		userController.loadCreatedMarkers();

		assertEquals("Resti", userController.getCreatedMarkers().get(0).getName());
		assertEquals("Bistro", userController.getCreatedMarkers().get(1).getName());
	}

	@Test
	public void testLoadFavouritesMarkers() {

		User user = new User(1, "max", "email@email.com", "pw");
		userController.setUser(user);
		List<Marker> markerList = new ArrayList<>();
		markerList.add(getDummyMarker(1, "MacDonelds"));
		markerList.add(getDummyMarker(2, "Burger Queen"));

		when(mockUserDao.getFavouritesOfUser(1)).thenReturn(markerList);
		userController.loadFavouriteMarkers();

		assertEquals("MacDonelds", userController.getFavouriteMarkers().get(0).getName());
		assertEquals("Burger Queen", userController.getFavouriteMarkers().get(1).getName());
	}

	@Test
	public void testLoginByUsername() {
		User dummyUser = new User(1,"mustermann", "mustermann@muster.ch", "abc123");
		
		when(mockUserDao.getUser("mustermann@muster.ch", "abc123")).thenReturn(dummyUser);

		assertTrue(userController.login(dummyUser));
	}

	@Test
	public void testLoginUnavailableUser() {
		User dummyUser1 = new User(1,"NonExistingUser", "NonExistingUser@muster.ch", "abc123");
		User dummyUser2 = new User(1,"NonExistingUser", "", "abc123");
		User dummyUser3 = new User(1,"NonExistingUser", "NonExistingUser@muster.ch", "");
		User dummyUser4 = new User(1,"N", "NonExistingUser@muster.ch", "abc123");
		User dummyUser5 = new User(1,"NonExistingUser", "#*%@.", "abc123");
		
		when(mockUserDao.getUser("NonExistingUser@muster.ch", "abc123")).thenReturn(new User());

		assertFalse(userController.login(dummyUser1));
		assertFalse(userController.login(dummyUser2));
		assertFalse(userController.login(dummyUser3));
		assertFalse(userController.login(dummyUser4));
		assertFalse(userController.login(dummyUser5));
	}

	@Test
	public void testRegisterNewUser() {
		User newUser = new User(1, "mustermann", "mustermann@muster.ch", "abc123");
			
		when(mockUserDao.checkAvailability("mustermann", "mustermann@muster.ch")).thenReturn(true);
		when(mockUserDao.createUser(newUser)).thenReturn((long) 1);
		
		assertTrue(userController.register(newUser));
	}

	@Test
	public void testRegisterWithUnavailableUserData() {
		User newUser = new User(1, "mustermann", "mustermann@muster.ch", "abc123");

		when(mockUserDao.checkAvailability("mustermann", "mustermann@muster.ch")).thenReturn(false);

		assertFalse(userController.register(newUser));
	}
	
	@Test
	public void testRegisterWithIncompatibleEmail() {
		
		assertFalse(userController.emailIsValid("@muster.ch"));
		assertFalse(userController.emailIsValid("n@m"));
		assertFalse(userController.emailIsValid("@muster.ch"));
		assertFalse(userController.emailIsValid("#%%@muster.ch"));
		assertFalse(userController.emailIsValid("#&&&%@muster.ch"));
		assertFalse(userController.emailIsValid(" "));
	}
	
	@Test
	public void testRegisterWithIncompatibleUsername() {
		
		assertFalse(userController.usernameIsValid("m"));
		assertFalse(userController.usernameIsValid("Pa%554"));
		assertFalse(userController.usernameIsValid("Boy&Girl"));
		assertTrue(userController.usernameIsValid("DerMustermann"));
		assertFalse(userController.usernameIsValid(" "));
		assertFalse(userController.usernameIsValid("DerMustermannDerMustermannDerMustermannDerMustermann"));
	}

	@Test
	public void testRegisterWithEmptyUserData() {
		User newUser = new User(1, "mustermann", "mustermann@muster.ch", "abc123");
		User newUser2 = new User(1, "", "mustermann@muster.ch", "abc123");
		User newUser3 = new User(1, "mustermann", "", "abc123");
		User newUser4 = new User(1, "mustermann", "mustermann@muster.ch", "");
		User newUser5 = new User(1, "", "", "");

		when(mockUserDao.checkAvailability("mustermann", "mustermann@muster.ch")).thenReturn(false);
		when(mockUserDao.createUser(newUser2)).thenReturn((long)1);
		when(mockUserDao.createUser(newUser3)).thenReturn((long)1);
		when(mockUserDao.createUser(newUser4)).thenReturn((long)1);
		when(mockUserDao.createUser(newUser5)).thenReturn((long)1);

		assertFalse(userController.register(newUser));
		assertFalse(userController.register(newUser2));
		assertFalse(userController.register(newUser3));
		assertFalse(userController.register(newUser4));
		assertFalse(userController.register(newUser5));
	}
	
	@Test
	public void testUnavaliebleUserName() {
		
		User newUser = new User(1, "m", "mustermann@muster.ch", "abc123");

		when(mockUserDao.checkAvailability("m", "mustermann@muster.ch")).thenReturn(false);

		assertFalse(userController.register(newUser));
	}

	@Test
	public void testRemoveUser() {
		
		User newUser = new User(1, "Alex", "mustermann@muster.ch", "abc123");
		
		assertFalse(userController.removeUser(null));
		assertTrue(userController.removeUser(newUser));
		
	}
	
	private Marker getDummyMarker(long id, String name) {

		return new Marker.MarkerBuilder()
				.setId(id)
				.setName(name)
				.setAddress("Strasse")
				.setWebsite("www.restaurant.com")
				.setDescription("Das Restaurant war sehr gemuetlich.")
				.setOpeningHours("8:00 bis 20:00")
				.setKitchens(Collections.singletonList(Kitchen.FASTFOOD))
				.setPrice(Price.MEDIUM)
				.setVegetarian(true)
				.setTakeAway(true)
				.setImage(null)
				.setLocation("47.37447, 8.53033")
				.build();
	}
}
