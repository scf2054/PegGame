package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestMove {
    
    @Test
    public void testFetFrom(){
        Location from = new Location(0,0,false);
        Location to = new Location(1,1,false);

        Move move = new Move(from, to);
        Location actual = move.getFrom();

        assertEquals(from, actual);
    }

    @Test
    public void testFetTo(){
        Location from = new Location(0,0,false);
        Location to = new Location(1,1,false);

        Move move = new Move(from, to);
        Location actual = move.getTo();

        assertEquals(to, actual);
    }

    @Test
    public void testEqualsValid(){
        Location from = new Location(0,0,false);
        Location to = new Location(1,1,false);
        Move move1 = new Move(from, to);
        Move move2 = new Move(from, to);
        boolean expected = true;

        boolean actual = move1.equals(move2);

        assertEquals(expected, actual);
    }

    @Test
    public void testEqualsInvalid(){
        Location from = new Location(0,0,false);
        Location to = new Location(1,1,false);
        Location too = new Location(1,2,false);
        Move move1 = new Move(from, to);
        Move move2 = new Move(from, too);
        boolean expected = false;

        boolean actual = move1.equals(move2);

        assertEquals(expected, actual);
    }
}
