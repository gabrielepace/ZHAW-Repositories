package ch.hungrystudent.controller;

import ch.hungrystudent.dataaccess.H2ConnectionProviderImpl;
import ch.hungrystudent.dataaccess.MarkerDao;
import ch.hungrystudent.dataaccess.MarkerDaoImpl;

import java.util.Collections;
import java.util.List;

import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Review;
import ch.hungrystudent.domain.Price;
import ch.hungrystudent.domain.Kitchen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;

/**
 * Marker Controller Test class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public class MarkerControllerTest {

	private MarkerController markerController;

	@Before
	public void setup() {

		H2ConnectionProviderImpl h2DataProvider = H2ConnectionProviderImpl.getInstance();
		h2DataProvider.cleanUpDatabase();
		h2DataProvider.createSingleUser();
		MarkerDao markerDao = new MarkerDaoImpl(h2DataProvider);
		markerController = new MarkerController(markerDao);
	}

	@Test
	public void testDoActionFunctionMapping() {

		Marker marker = getDummyMarker(1);

		// adding marker
		markerController.doAction("add", marker, 1, 1);
		assertNotNull(markerController.getMarkerById(1));

		// edit marker
		marker.setName("Is Edited");
		markerController.doAction("edit", marker, 1, 1);
		assertEquals("Is Edited", markerController.getMarkerById(1).getName());

		// removing marker
		markerController.doAction("remove", 1, 1);
		assertNull(markerController.getMarkerById(1));
	}

	@Test
	public void testCreateMarker() {

		markerController.createMarker(getDummyMarker(1), 1);
		markerController.createMarker(getDummyMarker(2), 1);
		markerController.createMarker(getDummyMarker(3), 1);

		assertNotNull(markerController.getMarkerById(1));
		assertNotNull(markerController.getMarkerById(2));
		assertNotNull(markerController.getMarkerById(3));
	}

	@Test
	public void testCreateMarkerWithNoUser() {

		// user id -1 is seen as "not logged in"
		markerController.createMarker(getDummyMarker(1), -1);

		assertNull(markerController.getMarkerById(1));
	}

    @Test
    public void testEditExistingMarker() {

		Marker marker = getDummyMarker(1);
		markerController.createMarker(marker, 1);

		marker.setName("TestTest");
        markerController.editMarker(marker, 1);

        marker = markerController.getMarkerById(1);
		assertNotNull(marker);
		assertEquals("TestTest", marker.getName());
    }

    @Test
    public void testEditMarkerWithNoMarkerId() {

		Marker marker = getDummyMarker(1);
		markerController.createMarker(marker, 1);

		marker.setName("TestTest");
        markerController.editMarker(marker, -1);

        marker = markerController.getMarkerById(1);
		assertNotNull(marker);
		assertNotEquals("TestTest", marker.getName());
    }

	@Test
	public void testDeleteMarker() {

		markerController.createMarker(getDummyMarker(1), 1);
		assertNotNull(markerController.getMarkerById(1));

        markerController.deleteMarker(1);
		assertNull(markerController.getMarkerById(1));
	}

	@Test
	public void testWritingReviews() {

		markerController.createMarker(getDummyMarker(1), 1);
		assertNotNull(markerController.getMarkerById(1));

		markerController.writeReview(1, 1, "Test1");
		markerController.writeReview(1, 1, "Test2");
		markerController.writeReview(1, 1, "Test3");
		List<Review> reviewList = markerController.getReviewsOfMarker(1);

        assertEquals(3, reviewList.size());
	}

	@Test
	public void testThatUserCantWriteReviewWithEmptyText() {

		markerController.createMarker(getDummyMarker(1), 1);
		assertNotNull(markerController.getMarkerById(1));

		markerController.writeReview(1, 1, "");
		List<Review> reviewList = markerController.getReviewsOfMarker(1);

        assertEquals(0, reviewList.size());
	}

	@Test
	public void testThatUserCantWriteReviewWhenNotLoggedIn() {

		markerController.createMarker(getDummyMarker(1), 1);
		assertNotNull(markerController.getMarkerById(1));

		markerController.writeReview(-1, 1, "Test1");
		List<Review> reviewList = markerController.getReviewsOfMarker(1);

        assertEquals(0, reviewList.size());
	}

	/**
	 * Applies dummy values to markerController
	 * to reduce method size
	 */
	private Marker getDummyMarker(long id) {

		Marker marker = new Marker();
		marker.setId(id);
		marker.setName("RestiResti");
		marker.setAddress("Strasse 12");
		marker.setWebsite("www.restiresti.ch");
		marker.setDescription("ist super");
		marker.setOpeningHours("11:00-13:00 Mo-Sa");
		marker.setKitchens(Collections.singletonList(Kitchen.FASTFOOD));
		marker.setPrice(Price.CHEAP);
		marker.setVegetarian(true);
		marker.setTakeAway(true);
		marker.setLocation("0,0");

		return marker;
	}
}
