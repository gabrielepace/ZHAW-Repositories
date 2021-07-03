package ch.hungrystudent.dataaccess;

import ch.hungrystudent.domain.Kitchen;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Price;
import ch.hungrystudent.domain.Review;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

/**
 * Marker DAO Implementation Test class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public class MarkerDaoImplTest{
	
	private static MarkerDaoImpl markerDao;
	
	@Before
	public void init() {

		H2ConnectionProviderImpl h2DataProvider = H2ConnectionProviderImpl.getInstance();
		// prepare database for tests
		h2DataProvider.cleanUpDatabase();
        h2DataProvider.createSingleUser();

        markerDao = new MarkerDaoImpl(h2DataProvider);
    }

	private Marker getDummyMarker() {
		List<Kitchen> kitchenList = new ArrayList<>();
		
		kitchenList.add(Kitchen.ASIAN);
		kitchenList.add(Kitchen.BARS);
		kitchenList.add(Kitchen.CAFE);

		return new Marker.MarkerBuilder()
				.setId(-1)
				.setName("Restaurant")
				.setAddress("Strasse")
				.setWebsite("www.restaurant.com")
				.setDescription("Das Restaurant war sehr gemuetlich.")
				.setOpeningHours("8:00 bis 20:00")
				.setKitchens(kitchenList)
				.setPrice(Price.MEDIUM)
				.setVegetarian(true)
				.setTakeAway(true)
				.setImage(null)
				.setLocation("47.37447, 8.53033")
				.build();
	}

	@Test
	public void testCreateMarkers() {

		Marker marker = getDummyMarker();

		long markerId = markerDao.createMarker(marker, 1);

		Marker savedMarker = markerDao.getMarker(markerId);

		assertNotNull(savedMarker);

		assertEquals(savedMarker.getName(), marker.getName());
		assertEquals(savedMarker.getAddress(), marker.getAddress());
		assertEquals(savedMarker.getWebsite(), marker.getWebsite());
		assertEquals(savedMarker.getDescription(), marker.getDescription());
		assertEquals(savedMarker.getOpeningHours(), marker.getOpeningHours());
		assertEquals(savedMarker.getKitchens(), marker.getKitchens());
		assertEquals(savedMarker.getPrice(), marker.getPrice());
		assertEquals(savedMarker.isVegetarian(), marker.isVegetarian());
		assertEquals(savedMarker.isTakeAway(), marker.isTakeAway());
        assertNull(savedMarker.getImage());
		assertEquals(savedMarker.getLocation(), marker.getLocation());

	}

	@Test
	public void testEditMarker() {

		Marker marker = getDummyMarker();
		List<Kitchen> kitchens = marker.getKitchens();
        
		markerDao.createMarker(marker, 1);

		marker.setName("Coop-restaurant");
		marker.setAddress("Regenstrasse 2");
		marker.setWebsite("migrolino.com");
		marker.setDescription("Schlechtes Restaurant");
		marker.setOpeningHours("12:00 bis 20:00");
		kitchens.add(Kitchen.EUROPEAN);
		marker.setKitchens(kitchens);
		marker.setPrice(Price.CHEAP);
		marker.setTakeAway(false);
		marker.setVegetarian(false);
		marker.setImage(null);

		marker.setId(1);
		markerDao.editMarker(marker);

		Marker editedMarker = markerDao.getAllMarker().get(0);
        assertNotNull(editedMarker);
		assertEquals("Coop-restaurant", editedMarker.getName());
		assertEquals("Regenstrasse 2", editedMarker.getAddress());
		assertEquals("migrolino.com", editedMarker.getWebsite());
		assertEquals("Schlechtes Restaurant", editedMarker.getDescription());
		assertEquals("12:00 bis 20:00", editedMarker.getOpeningHours());
		assertEquals(kitchens, editedMarker.getKitchens());
		assertEquals(Price.CHEAP, editedMarker.getPrice());
        assertFalse(editedMarker.isVegetarian());
        assertFalse(editedMarker.isTakeAway());
        assertNull(editedMarker.getImage());
	}

	@Test
	public void testEditMarkerWithSqlInjections() {

		Marker marker = getDummyMarker();

		markerDao.createMarker(marker, 1);

		marker.setName("Migro-resti");
		marker.setAddress("Regenstrasse 2 WHERE ID=1; DROP TABLE Marker;");
		marker.setWebsite("migrolino.com#");
		marker.setPrice(Price.CHEAP);
		marker.setTakeAway(false);

		marker.setId(1);
		markerDao.editMarker(marker);

		Marker editedMarker = markerDao.getAllMarker().get(0);
		assertNotNull(editedMarker);
		assertEquals("Migro-resti", editedMarker.getName());
		assertEquals("Regenstrasse 2 WHERE ID=1; DROP TABLE Marker;", editedMarker.getAddress());
		assertEquals("migrolino.com#", editedMarker.getWebsite());
		assertEquals(Price.CHEAP, editedMarker.getPrice());
		assertFalse(editedMarker.isTakeAway());
	}

	@Test
	public void testEditingNonExistingMarker() {

		Marker marker = getDummyMarker();

		marker.setId(1);
		marker.setName("Biit Nats");
		marker.setAddress("Strassestrasse 3");
		marker.setPrice(Price.CHEAP);
		marker.setTakeAway(false);

		markerDao.editMarker(marker);

		assertEquals(0, markerDao.getAllMarker().size());
		assertNull(markerDao.getMarker(1));
	}

	@Test
	public void testDeleteMarkers() {

		long[] markerId = new long[4];
		markerId[0] = markerDao.createMarker(getDummyMarker(), 1);
		markerId[1] = markerDao.createMarker(getDummyMarker(), 1);
		markerId[2] = markerDao.createMarker(getDummyMarker(), 1);
		markerId[3] = markerDao.createMarker(getDummyMarker(), 1);

		markerDao.deleteMarker(markerId[0]);
		markerDao.deleteMarker(markerId[2]);

		List<Marker> markerList = markerDao.getAllMarker();
		for(Marker marker : markerList) {

			assertTrue(marker.getId() != markerId[0] || marker.getId() != markerId[2]);
		}
		assertEquals(2, markerList.size());
	}

	@Test
	public void testGetAllMarkers() {

		List<Marker> testMarkers = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			Marker marker = getDummyMarker();
			testMarkers.add(marker);
			markerDao.createMarker(marker, 1);
		}
		List<Marker> markerList1 = markerDao.getAllMarker();
		assertEquals(markerDao.getAllMarker().size(), testMarkers.size());
	}

	@Test
	public void testGetMarkersByFilter() {		

		Marker dummyMarker = getDummyMarker();
		dummyMarker.setVegetarian(false);
		markerDao.createMarker(dummyMarker, 1);

		dummyMarker = getDummyMarker();
		markerDao.createMarker(dummyMarker, 1);

		dummyMarker = getDummyMarker();
		markerDao.createMarker(dummyMarker, 1);

		dummyMarker = getDummyMarker();
		dummyMarker.setVegetarian(false);
		markerDao.createMarker(dummyMarker, 1);

		// loads list of markers which doesn't have vegetarian option
		List<Marker> filteredMarkers = markerDao.getMarkerByFilter(dummyMarker);

		for(Marker marker : filteredMarkers) {

			assertTrue(marker.getId() != 2 || marker.getId() != 3);
		}
	}

	@Test
	public void testFavouriteMarker() {

		markerDao.createMarker(getDummyMarker(), 1);
		markerDao.createMarker(getDummyMarker(), 1);
		markerDao.createMarker(getDummyMarker(), 1);

		markerDao.favouriteMarker(1, 1);
		markerDao.favouriteMarker(1, 3);

		UserDao userDao = new UserDaolmpl(H2ConnectionProviderImpl.getInstance());
		List<Marker> favourites = userDao.getFavouritesOfUser(1);

		for(Marker marker : favourites) {

			assertTrue(marker.getId() != 2);
		}
	}

	@Test
	public void testUnfavouriteMarker() {

		markerDao.createMarker(getDummyMarker(), 1);
		markerDao.createMarker(getDummyMarker(), 1);
		markerDao.createMarker(getDummyMarker(), 1);
		markerDao.createMarker(getDummyMarker(), 1);

		markerDao.favouriteMarker(1, 1);
		markerDao.favouriteMarker(1, 3);
		markerDao.favouriteMarker(1, 4);

		UserDao userDao = new UserDaolmpl(H2ConnectionProviderImpl.getInstance());
		List<Marker> favourites = userDao.getFavouritesOfUser(1);

		assertEquals(3, favourites.size());

		markerDao.unfavouriteMarker(1, 1);
		markerDao.unfavouriteMarker(1, 3);

		favourites = userDao.getFavouritesOfUser(1);
		assertEquals(1, favourites.size());
		assertEquals(4, favourites.get(0).getId());
	}

	@Test
	public void testGettingReviewsFromMarker() {

		long markerId = markerDao.createMarker(getDummyMarker(), 1);
		markerDao.writeReview(1, markerId, "Very nice place to eat breakfast");
		markerDao.writeReview(1, markerId, "Tasty Waffles!");

		List<Review> reviewList = markerDao.getReviewsOfMarker(markerId);
        assertEquals(2, reviewList.size());
		assertEquals("Very nice place to eat breakfast", reviewList.get(0).getReviewText());
		assertEquals("Tasty Waffles!", reviewList.get(1).getReviewText());
		assertEquals("mustermann", reviewList.get(0).getUsername());
	}
}