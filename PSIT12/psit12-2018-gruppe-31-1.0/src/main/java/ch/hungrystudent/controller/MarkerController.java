package ch.hungrystudent.controller;

import ch.hungrystudent.dataaccess.MarkerDao;
import ch.hungrystudent.dataaccess.MarkerDaoImpl;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Review;

import java.util.List;

/**
 * Marker Controller class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student)
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), 
 *         Aleksandra Timofeeva (timofale), Semanur Cerkez (cerkesem)
 */
public class MarkerController {

	private MarkerDao markerDao;

	public MarkerController() {

		this.markerDao = new MarkerDaoImpl();
	}

	public MarkerController(MarkerDao markerDao) {

		this.markerDao = markerDao;
	}

	public void doAction(String action, Marker marker, long markerId, long userId) {

		if (null != action) {

			switch (action) {
				case "add":
					createMarker(marker, userId);
					break;
				case "edit":
					editMarker(marker, markerId);
					break;
				case "remove":
					deleteMarker(markerId);
					break;
				case "favourite":
					favouriteMarker(userId, markerId);
					break;
				case "unfavourite":
					unfavouriteMarker(userId, markerId);
					break;
				default:
					// do nothing
			}
		}
	}

	public void doAction(String action, long markerId, long userId) {

		doAction(action, null, markerId, userId);
	}

	public void setMarkerDao(MarkerDao markerDao) {

		this.markerDao = markerDao;
	}

	public Marker getMarkerById(long id) {
		return markerDao.getMarker(id);
	}

	public void createMarker(Marker marker, long userId) {
		if(userId != -1) {
			markerDao.createMarker(marker, userId);
		}
	}

	public void editMarker(Marker marker, long markerId) {
		if(markerId != -1) {
			markerDao.editMarker(marker);
		}
	}

	public void deleteMarker(long markerId) {
		if(markerId != -1) {
			markerDao.deleteMarker(markerId);
		}
	}

	private void favouriteMarker(long userId, long markerId) {
		if(userId != -1) {
			markerDao.favouriteMarker(userId, markerId);
		}
	}

	private void unfavouriteMarker(long userId, long markerId) {
		if(userId != -1) {
			markerDao.unfavouriteMarker(userId, markerId);
		}
	}

	public void writeReview(long userId, long markerId, String reviewText) {
		if(!reviewText.isEmpty() && userId != -1
				&& markerId != -1) {
			markerDao.writeReview(userId, markerId, reviewText);
		}
	}

    public List<Review> getReviewsOfMarker(long markerId) {

		return markerDao.getReviewsOfMarker(markerId);
    }
}
