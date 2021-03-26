package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import peggame.PegGame.GameState;

@Testable
public class TestRectangularBoard {
    
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

    @Test
    public void testMakeMove() {
        // setup
        PegGame board = new RectangularBoard(3, 3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[1].setPeg(true);
        Location fromLocation = locations[0];
        Location toLocation = locations[2];
        Move move = new Move(fromLocation, toLocation);
        String expected = "--o\n---\n---";

        // invoke
        try {
            board.MakeMove(move);
            String actual = board.toString();

            // analyze
            assertEquals(expected, actual);
        }
        catch (PegGameException e) {
            assertFalse(true);
        }
    }

    @Test
    public void testMakeMoveInvalid() {
        // setup
        PegGame board = new RectangularBoard(3, 3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[1].setPeg(true);
        locations[2].setPeg(true);
        Location fromLocation = locations[0];
        Location toLocation = locations[2];
        Move move = new Move(fromLocation, toLocation);
        String expected = "--o\n---\n---";

        // invoke
        try {
            board.MakeMove(move);
            String actual = board.toString();

            // analyze
            assertEquals(expected, actual);
        }
        catch (PegGameException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGameStateNotStarted(){
        PegGame board = new RectangularBoard(3, 3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[1].setPeg(true);
        locations[2].setPeg(true);
        GameState expected = GameState.NOT_STARTED;

        GameState actual = board.getGameState();

        assertEquals(expected, actual);
    }

    @Test
    public void testGameStateInProgress(){
        PegGame board = new RectangularBoard(3, 3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[1].setPeg(true);
        locations[5].setPeg(true);
        Location fromLocation = locations[0];
        Location toLocation = locations[2];
        Move move = new Move(fromLocation,toLocation);
        GameState expected = GameState.IN_PROGRESS;

        try{
            board.MakeMove(move);
            GameState actual = board.getGameState();
            assertEquals(expected, actual);
        } catch (PegGameException p){
            assertFalse(true);
        }
    }

    @Test
    public void testGameStateWon(){
        PegGame board = new RectangularBoard(3, 3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[1].setPeg(true);
        Location fromLocation = locations[0];
        Location toLocation = locations[2];
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
        PegGame board = new RectangularBoard(3, 3);
        Location[] locations = board.getLocations();
        locations[0].setPeg(true);
        locations[1].setPeg(true);
        locations[8].setPeg(true);
        Location fromLocation = locations[0];
        Location toLocation = locations[2];
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
        RectangularBoard expected = new RectangularBoard(3, 3);
        
        // invoke
        RectangularBoard actual = expected.deepCopy();

        // analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testDeepCopyMove() {
        // setup
        RectangularBoard board = new RectangularBoard(3, 3);
        RectangularBoard copy = board.deepCopy();
        Location[] locationsOrig = board.getLocations();
        Location[] locationsCopy = copy.getLocations();
        locationsOrig[0].setPeg(true);
        locationsOrig[1].setPeg(true);
        locationsCopy[0].setPeg(true);
        locationsCopy[1].setPeg(true);

        // invoke
        Location from = locationsCopy[0];
        Location to = locationsCopy[2];
        try {
            copy.MakeMove(new Move(from, to));
        } catch (PegGameException e) {
            e.printStackTrace();
        }

        // analyze
        assertFalse(board.equals(copy));
    }
}
