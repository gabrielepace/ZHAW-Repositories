package ch.hungrystudent.dataaccess;

import java.util.List;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Review;

/**
 * Marker DAO Interface
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public interface MarkerDao {

	Marker getMarker(long id);

	List<Marker> getMarkersByName(String name);

	List<Marker> getAllMarker();

	List<Marker> getMarkerByFilter(Marker marker);

	long createMarker(Marker marker, long userId);

	void favouriteMarker(long userId, long markerId);

	void unfavouriteMarker(long userId, long markerId);

	void editMarker(Marker marker);

	void deleteMarker(long id);

	void writeReview(long userId, long markerId, String reviewText);

	List<Review> getReviewsOfMarker(long markerId);
}
