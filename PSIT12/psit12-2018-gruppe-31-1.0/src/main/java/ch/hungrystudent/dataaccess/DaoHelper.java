package ch.hungrystudent.dataaccess;

import ch.hungrystudent.domain.Kitchen;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Price;
import ch.hungrystudent.domain.Review;
import ch.hungrystudent.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoHelper {
    private static final Logger LOGGER = Logger.getLogger(DaoHelper.class.getName());

    public static Marker extractMarkerFromResultSet(ResultSet rs) {
        try {
            List<Kitchen> kitchenList = new ArrayList<>();

            String[] kitchens = rs.getString(7).split(",");

            for(String kitchen : kitchens) {
                if(!kitchen.trim().isEmpty()) {

                    kitchenList.add(Kitchen.valueOf(kitchen));
                }
            }

            Price price = Price.CHEAP;
            if(rs.getString(8) != null && !"".equals(rs.getString(8))) {
                price = Price.valueOf(rs.getString(8));
            }

            return new Marker.MarkerBuilder()
                    .setId(rs.getLong(1))
                    .setName(rs.getString(2))
                    .setAddress(rs.getString(3))
                    .setWebsite(rs.getString(4))
                    .setDescription(rs.getString(5))
                    .setOpeningHours(rs.getString(6))
                    .setKitchens(kitchenList)
                    .setPrice(price)
                    .setVegetarian(rs.getBoolean(9))
                    .setTakeAway(rs.getBoolean(10))
                    .setImage(rs.getString(12))
                    .setLocation(rs.getString(11))
                    .build();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Couldn't parse marker from result set", e);
            return null;
        }
    }

    public static List<Marker> extractListOfMarkersFromResultSet(ResultSet rs) {
        List<Marker> resultList = new ArrayList<>();
        try {
            while (rs.next()) {
                Marker marker = extractMarkerFromResultSet(rs);
                if(marker != null) {
                    resultList.add(marker);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while parsing markers", e);
        }
        return resultList;
    }

    public static Review extractReviewFromResultSet(ResultSet rs) {
        try {
            return new Review(rs.getLong(1),
                    rs.getString(2),
                    rs.getLong(3),
                    rs.getString(4));
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Couldn't parse review from result set", e);
            return null;
        }
    }

    public static User extractUserFromResultSet(ResultSet rs) {
    	try {
    		return new User(rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));
    	}catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Couldn't parse user from result set", e);
            return null;
        } 
    }
}
