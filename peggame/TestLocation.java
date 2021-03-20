package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestLocation {
    @Test
    public void testEqualsValid(){
        int row = 0;
        int col = 0;
        Location location1 = new Location(row, col,false);
        Location location2 = new Location(row, col,false);
        boolean expected = true;

        boolean actual = location1.equals(location2);

        assertEquals(expected, actual);
    }

    @Test
    public void testEqualsInvalid(){
        int row = 0;
        int col = 0;
        Location location1 = new Location(row, col, false);
        Location location2 = new Location(row, col+1, false);
        boolean expected = false;

        boolean actual = location1.equals(location2);

        assertEquals(expected, actual);
    }
}
