package ch.hungrystudent.dataaccess;

import ch.hungrystudent.domain.Kitchen;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.Price;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DaoHelperTest {

    @Test
    public void testExtractingMarker() throws SQLException {

        ResultSet rs = mock(ResultSet.class);
        when(rs.getLong(1)).thenReturn(3L);
        when(rs.getString(2)).thenReturn("TestResti");
        when(rs.getString(3)).thenReturn("Strasse 9");
        when(rs.getString(4)).thenReturn("www.test.com");
        when(rs.getString(5)).thenReturn("is something");
        when(rs.getString(6)).thenReturn("9:00 - 10:00");
        when(rs.getString(7)).thenReturn("ASIAN,EUROPEAN,");
        when(rs.getString(8)).thenReturn("EXPENSIVE");
        when(rs.getBoolean(9)).thenReturn(true);
        when(rs.getBoolean(10)).thenReturn(false);
        when(rs.getString(11)).thenReturn("47.37447, 8.53033");
        when(rs.getString(12)).thenReturn(null);

        List<Kitchen> kitchenList = new ArrayList<>();
        kitchenList.add(Kitchen.ASIAN);
        kitchenList.add(Kitchen.EUROPEAN);

        Marker marker = DaoHelper.extractMarkerFromResultSet(rs);
        assertNotNull(marker);
        assertEquals(3L, marker.getId());
        assertEquals(marker.getName(), "TestResti");
        assertEquals(marker.getAddress(), "Strasse 9");
        assertEquals(marker.getWebsite(), "www.test.com");
        assertEquals(marker.getDescription(), "is something");
        assertEquals(marker.getOpeningHours(), "9:00 - 10:00");
        assertEquals(marker.getKitchens(), kitchenList);
        assertEquals(marker.getPrice(), Price.EXPENSIVE);
        assertTrue(marker.isVegetarian());
        assertFalse(marker.isTakeAway());
        assertEquals(marker.getLocation(), "47.37447, 8.53033");
        assertNull(marker.getImage());
    }
}
