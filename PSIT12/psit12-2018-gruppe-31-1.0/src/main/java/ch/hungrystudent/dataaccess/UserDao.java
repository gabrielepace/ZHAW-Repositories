package ch.hungrystudent.dataaccess;

import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.User;
import java.util.List;

/**
 * User DAO Interface
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public interface UserDao {

	User getUser(String userEmail, String password);
	
	long createUser(User user);

	boolean checkAvailability(String username, String email);
	
	List<Marker> getCreatedOfUser(long userId);

	List<Marker> getFavouritesOfUser(long userId);

	void editUser (User user);

	void removeUser(User user);
	
}
