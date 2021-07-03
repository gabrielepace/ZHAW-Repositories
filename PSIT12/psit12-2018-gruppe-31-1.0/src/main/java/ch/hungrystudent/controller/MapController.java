package ch.hungrystudent.controller;

import java.util.ArrayList;
import java.util.List;
import ch.hungrystudent.dataaccess.MarkerDao;
import ch.hungrystudent.dataaccess.MarkerDaoImpl;
import ch.hungrystudent.domain.Marker;

/**
 * Map Controller class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public class MapController {

	private MarkerDao markerDao;
	private List<Marker> markers;

	public MapController() {
		
		this.markerDao = new MarkerDaoImpl();
		markers = new ArrayList<>();
	}
	
	public MapController(MarkerDao markerDao) {

		this.markerDao = markerDao;
		markers = new ArrayList<>();
	}

	public void doAction(String action, Marker marker, String name) {
	
		if (null != action) {
			if ("filter".equals(action)) {
				this.filterMarkers(marker);
			}
			else if("search".equals(action)){
				this.searchMarker(name);
			}
		}
	}

	public void doAction(String action, Marker marker){
		doAction(action, marker, "");
	}
	
	public void filterMarkers(Marker marker) {

		markers = this.markerDao.getMarkerByFilter(marker);
	}

	private void searchMarker(String name){
		if(name != null && !name.isEmpty()) {
			markers = this.markerDao.getMarkersByName(name.trim());
		}
	}

	public void setMarkerDao(MarkerDao markerDao) {

		this.markerDao = markerDao;
	}

	public void loadMarkers() {

		if(markers == null || markers.isEmpty()) {

			markers = this.markerDao.getAllMarker();
		}
	}

	public List<Marker> getMarkers() {
		return markers;
	}

	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}
}
