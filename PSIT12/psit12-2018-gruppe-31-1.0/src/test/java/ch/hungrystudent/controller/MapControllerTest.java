package ch.hungrystudent.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import ch.hungrystudent.dataaccess.MarkerDao;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Price;

/**
 * Map Controller Test class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public class MapControllerTest {

	private static MapController mapController;
	private MarkerDao mockedMarkerDao;

	@Before
	public void setup() {
		mockedMarkerDao = mock(MarkerDao.class);
		mapController = new MapController(mockedMarkerDao);
	}

	@Test
	public void testDoActionFunctionalityMapping() {
		List<Marker> markerList = new ArrayList<>();

		Marker marker = new Marker.MarkerBuilder()
				.setId(1)
				.setName("Restaurant")
				.setAddress("Strasse")
				.setWebsite("www.restaurant.com")
				.setDescription("Das Restaurant war sehr gemuetlich.")
				.setOpeningHours("8:00 bis 20:00")
				.setKitchens(new ArrayList<>())
				.setPrice(Price.MEDIUM)
				.setVegetarian(true)
				.setTakeAway(true)
				.setImage(null)
				.setLocation("47.37447, 8.53033")
				.build();
		markerList.add(marker);
		when(mockedMarkerDao.getMarkerByFilter(marker)).thenReturn(markerList);

		// filtering markers
		mapController.doAction("filter", marker);
		List<Marker> result = mapController.getMarkers();
		assertTrue(result.contains(marker));

		// searching markers
		mapController.doAction("search", marker);
		result = mapController.getMarkers();
		assertTrue(result.contains(marker));
	}

	@Test
	public void testLoadMarkersWithNoFilter() {
		List<Marker> markerList = new ArrayList<>();

		Marker marker = new Marker.MarkerBuilder()
				.setId(1)
				.setName("Restaurant")
				.setAddress("Strasse")
				.setWebsite("www.restaurant.com")
				.setDescription("Das Restaurant war sehr gemuetlich.")
				.setOpeningHours("8:00 bis 20:00")
				.setKitchens(new ArrayList<>())
				.setPrice(Price.MEDIUM)
				.setVegetarian(true)
				.setTakeAway(true)
				.setImage(null)
				.setLocation("47.37447, 8.53033")
				.build();

		markerList.add(marker);
		when(mockedMarkerDao.getAllMarker()).thenReturn(markerList);

		mapController.loadMarkers();
		List<Marker> result = mapController.getMarkers();

		assertTrue(result.contains(marker));
	}

	@Test
	public void testLoadMarkersWhileNoMarkersAvailable() {
		when(mockedMarkerDao.getAllMarker()).thenReturn(new ArrayList<>());
		mapController.loadMarkers();

		List<Marker> result = mapController.getMarkers();

		assertTrue(result.isEmpty());
	}

	@Test
	public void testLoadMarkersWithFilter() {
		List<Marker> markerList = new ArrayList<>();

		Marker marker = new Marker.MarkerBuilder()
				.setId(-1)
				.setName("Restaurant")
				.setAddress("Strasse")
				.setWebsite("www.restaurant.com")
				.setDescription("Das Restaurant war sehr gemuetlich.")
				.setOpeningHours("8:00 bis 20:00")
				.setKitchens(new ArrayList<>())
				.setPrice(Price.MEDIUM)
				.setVegetarian(true)
				.setTakeAway(true)
				.setImage(null)
				.setLocation("47.37447, 8.53033")
				.build();
		markerList.add(marker);
		when(mockedMarkerDao.getMarkerByFilter(marker)).thenReturn(markerList);

		mapController.filterMarkers(marker);

		assertEquals(mapController.getMarkers().get(0), marker);
	}
}
