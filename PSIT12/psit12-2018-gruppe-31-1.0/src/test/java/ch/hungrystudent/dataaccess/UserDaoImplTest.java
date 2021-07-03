package ch.hungrystudent.dataaccess;

import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User DAO Implementation Test class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public class UserDaoImplTest {

    private UserDaolmpl userDao;

    @Before
    public void init() {

        H2ConnectionProviderImpl h2DataProvider = H2ConnectionProviderImpl.getInstance();
        // makes sure every test starts with empty database
        h2DataProvider.cleanUpDatabase();
        userDao = new UserDaolmpl(h2DataProvider);
    }

    @Test
    public void testGetUserByEmail() {

        userDao.createUser(new User(10, "MaxMuster", "maxmusert@email.com", "secretPW123"));
        userDao.createUser(new User(20, "PeterM", "peter123@email.com", "password321"));

        User user = userDao.getUser("peter123@email.com", "password321");

        assertNotNull(user);
        assertEquals("PeterM", user.getUsername());
    }

    @Test
    public void testGetNonExistingUserByEmail() {

        User user = userDao.getUser("peter123@email.com", "passwort321");
        assertEquals(-1, user.getId());
        assertEquals("", user.getPassword());
    }

    @Test
    public void testAvailabilityOfUser() {

        userDao.createUser(new User(10, "MaxMuster", "maxmusert@email.com", "secretPW123"));
        userDao.createUser(new User(20, "PeterM", "peter123@email.com", "passwort321"));

        assertFalse(userDao.checkAvailability("PeterM", "peter123@email.com"));
        assertFalse(userDao.checkAvailability("NewAcc", "maxmusert@email.com"));
        assertFalse(userDao.checkAvailability("PeterM", "emailmail@email.com"));
        assertTrue(userDao.checkAvailability("NewAcc", "emailmail@email.com"));
    }
    
    @Test
    public void testCreateUser() {
    	long id = userDao.createUser(new User(10,"Mustermann","mustermann@mail.com","Muster1"));
    	
    	User user = userDao.getUser("mustermann@mail.com", "Muster1");
    	
    	assertNotEquals(id,-1);
    	assertEquals(id, user.getId());
        assertEquals("Mustermann", user.getUsername());
        assertEquals("mustermann@mail.com", user.getEmail());
    }
    

    @Test
    public void testGettingCreatedMarkersOfUser() {
        long userId1 = userDao.createUser(new User(0,"Musterfrau","musterfrau@mail.com","Muster2"));
        long userId2 = userDao.createUser(new User(0,"Mustermann","mustermann@mail.com","Muster1"));

        MarkerDao markerDao = new MarkerDaoImpl(H2ConnectionProviderImpl.getInstance());

        Marker testMarker = new Marker();
        testMarker.setId(1);
        markerDao.createMarker(testMarker, userId1);
        testMarker.setId(2);
        markerDao.createMarker(testMarker, userId2);
        testMarker.setId(3);
        markerDao.createMarker(testMarker, userId1);

        List<Marker> createdMarkers = userDao.getCreatedOfUser(userId1);
        assertEquals(2, createdMarkers.size());

        for(Marker marker : createdMarkers) {
            assertTrue(marker.getId() != 2);
        }
    }
    
    @Test
    public void testRemoveUser() {  
    	User testUser = new User(10,"Mustermann","mustermann@mail.com","Muster1");
    	testUser.setId(userDao.createUser(testUser));
    	
    	User readUser = userDao.getUser("mustermann@mail.com", "Muster1");
    	
    	assertEquals(testUser.getId(),readUser.getId());
    	
    	userDao.removeUser(testUser);
    	readUser = userDao.getUser(testUser.getEmail(), testUser.getPassword());
    	
    	assertEquals(-1, readUser.getId());
    }
}
