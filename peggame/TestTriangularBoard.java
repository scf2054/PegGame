package peggame;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import peggame.PegGame.GameState;

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

    @Test
    public void testMakeMove() {
        // setup
        PegGame board = new TriangularBoard(3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[2].setPeg(true);
        locations[4].setPeg(true);
        Location from = locations[0];
        Location to = locations[5];
        Move move = new Move(from, to);
        String expected = "  - \n - - \n- o o \n";

        // invoke
        try {
            board.MakeMove(move);
            String actual = board.toString();

            // analyze
            assertEquals(expected, actual);
        } catch (PegGameException e) {
            assertFalse(true);
        }
    }

    @Test
    public void testMakeMoveInvalid() {
        // setup
        PegGame board = new TriangularBoard(3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[2].setPeg(true);
        locations[4].setPeg(true);
        Location from = locations[0];
        Location to = locations[4];
        Move move = new Move(from, to);
        String expected = "  - \n - - \n- o o \n";

        // invoke
        try {
            board.MakeMove(move);
            String actual = board.toString();

            // analyze
            assertEquals(expected, actual);
        } catch (PegGameException e) {
            assertFalse(false);
        }
    }

    @Test
    public void testGameStateNotStarted() {
        // setup
        PegGame board = new TriangularBoard(3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[1].setPeg(true);
        locations[2].setPeg(true);
        GameState expected = GameState.NOT_STARTED;

        // invoke
        GameState actual = board.getGameState();

        // analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testGameStateInProgress() {
        // setup
        PegGame board = new TriangularBoard(3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[2].setPeg(true);
        locations[4].setPeg(true);
        Location fromLocation = locations[0];
        Location toLocation = locations[5];
        Move move = new Move(fromLocation,toLocation);
        GameState expected = GameState.IN_PROGRESS;

        try {
            board.MakeMove(move);

            // invoke
            GameState actual = board.getGameState();

            // analyze
            assertEquals(expected, actual);
        } catch (PegGameException p){
            assertFalse(true);
        }
    }

    @Test
    public void testGameStateWon(){
        PegGame board = new TriangularBoard(3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[2].setPeg(true);
        Location fromLocation = locations[0];
        Location toLocation = locations[5];
        Move move = new Move(fromLocation,toLocation);
        GameState expected = GameState.WON;

        try{
            board.MakeMove(move);
            GameState actual = board.getGameState();
            assertEquals(expected, actual);
        } catch (PegGameException p){
            assertFalse(true);
        }
    }

    @Test
    public void testGameStateStalemate(){
        PegGame board = new TriangularBoard(3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[2].setPeg(true);
        locations[3].setPeg(true);
        Location fromLocation = locations[0];
        Location toLocation = locations[5];
        Move move = new Move(fromLocation,toLocation);
        GameState expected = GameState.STALEMATE;

        try{
            board.MakeMove(move);
            GameState actual = board.getGameState();
            assertEquals(expected, actual);
        } catch (PegGameException p){
            assertFalse(true);
        }
    }

    @Test
    public void testDeepCopy() {
        // setup
        TriangularBoard expected = new TriangularBoard(3);

        // inovke
        TriangularBoard actual = expected.deepCopy();

        // analyze
        assertEquals(expected, actual);

    }

    @Test
    public void testDeepCopyMove() {
        // setup
        TriangularBoard board = new TriangularBoard(3);
        TriangularBoard copy = board.deepCopy();
        Location[] locationsOrig = board.getLocations();
        Location[] locationsCopy = copy.getLocations();
        locationsOrig[0].setPeg(true);
        locationsOrig[2].setPeg(true);
        locationsCopy[0].setPeg(true);
        locationsCopy[2].setPeg(true);

        // invoke
        Location from = locationsCopy[0];
        Location to = locationsCopy[5];

        try {
            copy.MakeMove(new Move(from, to));
        }
        catch (PegGameException e) {
            e.printStackTrace();
        }

        // analyze
        assertFalse(board.equals(copy));
    }
}
