package ch.hungrystudent.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Kitchen;
import ch.hungrystudent.domain.Review;

import static ch.hungrystudent.dataaccess.DaoHelper.extractListOfMarkersFromResultSet;

/**
 * Marker DAO Implementation class
 * 
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 *         Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 *         (timofale), Semanur Cerkez (cerkesem)
 */
@SuppressWarnings("ALL")
public class MarkerDaoImpl implements MarkerDao {

	private static final Logger LOGGER = Logger.getLogger(MarkerDaoImpl.class.getName());
	private ConnectionProvider dataProvider;

	public MarkerDaoImpl() {

		dataProvider = ConnectionProviderImpl.getInstance();
	}

	public MarkerDaoImpl(ConnectionProvider dataProvider) {

		this.dataProvider = dataProvider;
	}

    @Override
    public Marker getMarker(long id) {
        Connection connection = dataProvider.getConnection();
        Marker marker = null;
        try {
            String query = "SELECT * FROM Marker WHERE ID=?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
				marker = DaoHelper.extractMarkerFromResultSet(rs);
			}

            statement.close();
        } catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while retrieving marker " +
					"with ID: " + id, e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
            }
        }
        return marker;
    }

    @Override
	public List<Marker> getMarkersByName(String name){

		Connection connection = dataProvider.getConnection();
		List<Marker> markerList = new ArrayList<>();

		try {
			String query = "SELECT * FROM Marker WHERE NAME LIKE ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "%" + name + "%");
			ResultSet rs = statement.executeQuery();

			markerList = extractListOfMarkersFromResultSet(rs);
			statement.close();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "An error occurred while retrieving markers " +
					"with the name: " + name, e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
		return markerList;
	}

    @Override
	public List<Marker> getAllMarker() {
		Connection connection = dataProvider.getConnection();
		List<Marker> markerList = new ArrayList<>();
		try {
			String query = "SELECT * FROM Marker;";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();

			markerList = extractListOfMarkersFromResultSet(rs);
			statement.close();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while retrieving every marker", e);
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
		return markerList;
	}

	@Override
	public List<Marker> getMarkerByFilter(Marker filterMarker) {

		int parameterIndex = 0;
		Connection connection = dataProvider.getConnection();
		List<Marker> markerList = new ArrayList<>();
		String query = "SELECT * FROM Marker WHERE PRICE=?";

		try {
			if (!filterMarker.getKitchens().isEmpty()) {
				
				query += " AND (";
				Iterator<Kitchen> kitchenIterator = filterMarker.getKitchens().iterator();
				while (kitchenIterator.hasNext()) {
					kitchenIterator.next();
					query += "Kitchen LIKE ?";
					if(kitchenIterator.hasNext()) {
						query += "OR ";
					}
				}
				query += ")";
			}

			String price = "";
			if (filterMarker.getPrice() != null) {
				price = filterMarker.getPrice().name();
			}

			if (filterMarker.isVegetarian()) {
				query += " AND Vegetarian=?";
			}

			if (filterMarker.isTakeAway()) {
				query += " AND TakeAway=?";
			}

			query += ";";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(++parameterIndex, price);
			
			for(Kitchen kitchen : filterMarker.getKitchens()) {
				statement.setString(++parameterIndex, "%" +kitchen + "%");
			}

			if (filterMarker.isVegetarian()) {
				statement.setBoolean(++parameterIndex, filterMarker.isVegetarian());
			}

			if (filterMarker.isTakeAway()) {
				statement.setBoolean(++parameterIndex, filterMarker.isTakeAway());
			}

			ResultSet rs = statement.executeQuery();

			markerList = extractListOfMarkersFromResultSet(rs);
			statement.close();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while retrieving markers matching " +
					"the current filtercriteria", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
		return markerList;
	}

	@Override
	public long createMarker(Marker marker, long userId) {
        Connection connection = dataProvider.getConnection();
		long markerId = -1;
		try {
			connection.setAutoCommit(false);

			// inserts new marker
			String query = "INSERT INTO Marker(Name, Address, Website, Description, OpeningHours, Kitchen, Price, Vegetarian, TakeAway, Location) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			StringBuilder sb = new StringBuilder();
            for (Kitchen kitchen : marker.getKitchens()) {
                sb.append(kitchen.name());
                sb.append(",");
            }

            String price = "";
            if (marker.getPrice() != null) {
                price = marker.getPrice().name();
            }

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, marker.getName());
            statement.setString(2, marker.getAddress());
            statement.setString(3, marker.getWebsite());
            statement.setString(4, marker.getDescription());
            statement.setString(5, marker.getOpeningHours());
            statement.setString(6, sb.toString());
            statement.setString(7, price);
            statement.setBoolean(8, marker.isVegetarian());
            statement.setBoolean(9, marker.isTakeAway());
            statement.setString(10, marker.getLocation());

            statement.executeUpdate();
            
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if(generatedKeys.next()) {
				markerId = generatedKeys.getLong(1);
			}

			// insert entry in created table
			query = "INSERT INTO Created(UserId, ID) VALUES (?, ?);";
			statement = connection.prepareStatement(query);
			statement.setLong(1, userId);
			statement.setLong(2, markerId);
			statement.executeUpdate();

			connection.commit();

			statement.close();
        } catch (SQLException e) {
			LOGGER.log(Level.INFO, "Couldn't create marker with userId: " + userId, e);
			// rolls back the changes if something happens
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOGGER.log(Level.SEVERE, "Couldn't rollback changes when creating marker", e1);
			}
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
            }
        }
        return markerId;
    }

	@Override
	public void favouriteMarker(long userId, long markerId) {
		Connection connection = dataProvider.getConnection();
		String query = "INSERT INTO Favourites (UserID, ID) VALUES (?, ?);";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setLong(1, userId);
			statement.setLong(2, markerId);

			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Couldn't favourite marker: " + markerId + " with userId: " + userId, e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
	}

    @Override
    public void unfavouriteMarker(long userId, long markerId) {
		Connection connection = dataProvider.getConnection();
		String query = "DELETE FROM Favourites WHERE UserID = ? AND ID = ?;";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setLong(1, userId);
			statement.setLong(2, markerId);

			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Couldn't unfavourite marker: " + markerId + " with userId: " + userId, e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
    }

    @Override
	public void editMarker(Marker marker) {

        Connection connection = dataProvider.getConnection();

        String query = "UPDATE Marker SET Name=?, Address=?, Website=?, Description=?, " +
                "OpeningHours=?, Kitchen=?, Price=?, Vegetarian=?, TakeAway=?, " +
                "Location=?, Image=? WHERE ID=?;";
        try {
            StringBuilder sb = new StringBuilder();
            for(Kitchen kitchen : marker.getKitchens()) {
                sb.append(kitchen.name());
                sb.append(",");
            }

            String price = "";
            if(marker.getPrice() != null) {
                price = marker.getPrice().name();
            }

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, marker.getName());
            statement.setString(2, marker.getAddress());
            statement.setString(3, marker.getWebsite());
            statement.setString(4, marker.getDescription());
            statement.setString(5, marker.getOpeningHours());
            statement.setString(6, sb.toString());
            statement.setString(7, price);
            statement.setBoolean(8, marker.isVegetarian());
            statement.setBoolean(9, marker.isTakeAway());
            statement.setString(10, marker.getLocation());
            statement.setString(11, marker.getImage());
            statement.setLong(12, marker.getId());

            int result = statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while editing marker: " + marker.getId(), e);
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
	public void deleteMarker(long id) {
        Connection connection = dataProvider.getConnection();
        try {
			connection.setAutoCommit(false);

			// remove marker entry in review
			String query = "DELETE FROM Review WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.executeUpdate();

			// remove marker entry in favourites
			query = "DELETE FROM Favourites WHERE ID = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.executeUpdate();

			// remove marker entry in created
			query = "DELETE FROM Created WHERE ID = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.executeUpdate();

			// remove marker in table Marker
			query = "DELETE FROM Marker WHERE ID=?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
            statement.executeUpdate();

            connection.commit();

            statement.close();
        } catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while removing marker: " + id, e);
            // rolls back the changes if something happens
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOGGER.log(Level.SEVERE, "Couldn't rollback when removing marker: " + id, e1);
			}
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
	public void writeReview(long userId, long markerId, String reviewText) {

		Connection connection = dataProvider.getConnection();
		String query = "INSERT INTO Review (UserID, ID, ReviewText) VALUES (?, ?, ?);";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setLong(1, userId);
			statement.setLong(2, markerId);
			statement.setString(3, reviewText);

			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Couldn't save review on marker: " + markerId + " with userId: " + userId, e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
	}

	@Override
	public List<Review> getReviewsOfMarker(long markerId){

		Connection connection = dataProvider.getConnection();
		List<Review> reviewList = new ArrayList<>();
		try {
			// Because of performance reasons
			// the username is also extracted from the db and saved in Review
			String query = "SELECT Review.UserID, Username, ID, ReviewText FROM Review " +
					"JOIN User ON Review.UserID = User.UserID WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setLong(1, markerId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Review review = DaoHelper.extractReviewFromResultSet(rs);
				if(review != null) {
					reviewList.add(review);
				}
			}
			statement.close();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred while getting reviews " +
					"for marker: " + markerId, e);
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, "Database connection couldn't close properly", e);
			}
		}
		return reviewList;
	}
}
