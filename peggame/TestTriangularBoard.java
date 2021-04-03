package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestTriangularBoard {

    @Test
    public void testToString() {
        // setup
        PegGame board = new TriangularBoard(3);
        String expected = "  - \n - - \n- - - \n";

        // invoke
        String actual = board.toString();

        // analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringPeg() {
        // setup
        PegGame board = new TriangularBoard(3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[4].setPeg(true);
        locations[2].setPeg(true);
        String expected = "  o \n - o \n- o - \n";

        // invoke
        String actual = board.toString();

        // analyze
        assertEquals(expected, actual);
    }
    
}
