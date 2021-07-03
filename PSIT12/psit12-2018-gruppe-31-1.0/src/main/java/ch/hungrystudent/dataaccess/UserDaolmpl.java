package ch.hungrystudent.dataaccess;

import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ch.hungrystudent.dataaccess.DaoHelper.extractListOfMarkersFromResultSet;

/**
 * User DAO Implementation class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
public class UserDaolmpl implements UserDao {

	private static final Logger LOGGER = Logger.getLogger(UserDaolmpl.class.getName());
	private final ConnectionProvider dataProvider;

	public UserDaolmpl() {

		dataProvider = ConnectionProviderImpl.getInstance();
	}

	public UserDaolmpl(ConnectionProvider dataProvider) {

		this.dataProvider = dataProvider;
	}

	@Override
	public long createUser(User user) {

		long generatedId = -1;
		Connection conn = dataProvider.getConnection();
		String insertQuery = "INSERT INTO User (Username, Email, Password) VALUES (?, ?, ?);";
		
		try {
			PreparedStatement statement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			String hashedPassword = get_SHA_512_SecurePassword(user.getPassword());

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getEmail());
			statement.setString(3, hashedPassword);
			statement.execute();

			generatedId = getGeneratedId(generatedId, statement);
			statement.close();

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while creating user: " + user.getEmail() , e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
		return generatedId;
	}
	

	@Override
	public void editUser(User user) {

		Connection connection = dataProvider.getConnection();
		String query = "UPDATE User SET Username=?, Email =?, Password=? WHERE UserID=?;";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			String hashedPassword = get_SHA_512_SecurePassword(user.getPassword());

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getEmail());
			statement.setString(3, hashedPassword);
			statement.setLong(4, user.getId());
			statement.executeUpdate();

			statement.close();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while editing user: " + user.getId(), e);
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
	}

	@Override
	public void removeUser(User user) {
		long id = user.getId();
		Connection connection = dataProvider.getConnection();
		try {
			connection.setAutoCommit(false);

			// remove marker entry in review
			String query = "DELETE FROM Review WHERE UserID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.executeUpdate();

			// remove marker entry in created
			query = "DELETE FROM Created WHERE UserID = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.executeUpdate();

			// remove marker in table Marker
			query = "DELETE FROM Favourites WHERE UserID=?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.executeUpdate();

			// remove marker entry in favourites
			query = "DELETE FROM User WHERE UserID = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.executeUpdate();

			connection.commit();

			statement.close();
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred while removing user: " + user.getEmail() , e);
			// rolls back the changes if something happens
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOGGER.log(Level.SEVERE, "There was a problem during rollback while removing user: " + user.getEmail(), e1);
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
	}
	
	@Override
	public boolean checkAvailability(String username, String email) {
		Connection conn = dataProvider.getConnection();
		boolean isAvailable = false;
		try {
			String query = "SELECT * FROM User WHERE Username=? OR Email=?;";
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1, username);
			statement.setString(2, email);

			ResultSet rs = statement.executeQuery();

			isAvailable = !rs.next();

			statement.close();

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Couldn't check availability of username: " + username + " with email: " + email, e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
		return isAvailable;
	}
	
	private long getGeneratedId(long generatedID, PreparedStatement statement) {

		try (ResultSet resultSet = statement.getGeneratedKeys()) {

			while (resultSet.next()) {

				generatedID = resultSet.getLong(1);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error on getting generated keys", e);
		}
		return generatedID;
	}

	@Override
	public List<Marker> getCreatedOfUser(long userId) {

		Connection connection = dataProvider.getConnection();
		List<Marker> createdList = new ArrayList<>();
		try {
			String query = "SELECT * FROM Marker " +
					"JOIN Created ON Marker.ID = Created.ID " +
					"WHERE Created.UserID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setLong(1, userId);

			ResultSet rs = statement.executeQuery();

			createdList = extractListOfMarkersFromResultSet(rs);
			statement.close();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while getting created markers " +
			"of userID: " + userId, e);
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
		return createdList;
	}

	@Override
	public List<Marker> getFavouritesOfUser(long userId) {

		Connection connection = dataProvider.getConnection();
		List<Marker> favouriteList = new ArrayList<>();
		try {
			String query = "SELECT * FROM Marker " +
					"JOIN Favourites ON Marker.ID = Favourites.ID " +
					"WHERE Favourites.UserID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setLong(1, userId);

			ResultSet rs = statement.executeQuery();

			favouriteList = extractListOfMarkersFromResultSet(rs);
			statement.close();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while getting favourite markers " +
					"of userID: " + userId, e);
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
		return favouriteList;
	}

	@Override
	public User getUser(String userEmail, String password) {
		
		Connection conn = dataProvider.getConnection();
		User resultUser = new User();
		try {
			String hashedPassword = get_SHA_512_SecurePassword(password);

			String query = "SELECT * FROM User WHERE Email=? AND Password=?;";
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1, userEmail);
			statement.setString(2, hashedPassword);

			ResultSet rs = statement.executeQuery();		
			
			if(rs.next()) {
				User user = DaoHelper.extractUserFromResultSet(rs);
				if(user != null) {
					resultUser = user;
				}
			}	

			statement.close();

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while retrieving user: " + userEmail, e);
			resultUser.setId(-2); //To inform caller that exception happened
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
		return resultUser;
	}
	
	private String get_SHA_512_SecurePassword(String passwordToHash) {

		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			// adds salt to password
			passwordToHash += "StUd3nT";

			// convert string to hash
			byte[] bytes = md.digest(passwordToHash.getBytes());

			// converts result bytes to string
			StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.SEVERE, "Couldn't hash given password");
		}
		return generatedPassword;
	}
}
