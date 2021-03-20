package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestRectangualrBoard {
    
    @Test
    public void testToStringEmpty() {
        // setup
        PegGame board = new RectangularBoard(3, 3);
        String expected = "---\n---\n---";

        // invoke
        String actual = board.toString();

        // analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringHasPeg() {
        // setup
        PegGame board = new RectangularBoard(3, 3);
        Location[] locations = board.getLocations();
        locations[7].setPeg(true);
        String expected = "---\n---\n-o-";

        // invoke
        String actual = board.toString();

        // analyze
        assertEquals(expected, actual);
    }
}
