package peggame;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestPegGameConfig {
    
    @Test
    public void is_goalTrue() {
        // setup
        PegGame game = null;
        try {
            game = ReadFile.PegFile("/Users/sam/SoftDevII/GroupProject/project-1-03-01/data/10_10.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Location from = null;
        Location to = null;
        Location[] locations = game.getLocations();
        for (Location location : locations) {
            if (location.getRow() == 4 && location.getCol() == 4) {
                from = location;
            }

            else if (location.getRow() == 4 && location.getCol() == 6) {
                to = location;
            }
        }

        try {
            game.MakeMove(new Move(from, to));
        }
        catch (PegGameException e) {
            e.printStackTrace();
        }
        PegGameConfig config = new PegGameConfig(game);

        // analyze
        assertTrue(config.isGoal());
    }

    @Test
    public void is_goalFalse() {
        // setup
        PegGame game = null;
        try {
            game = ReadFile.PegFile("/Users/sam/SoftDevII/GroupProject/project-1-03-01/data/10_10.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        PegGameConfig config = new PegGameConfig(game);

        // analyze
        assertFalse(config.isGoal());
    }

    @Test
    public void testIs_validTrue(){
        // setup
        PegGame game = null;
        try {
            game = ReadFile.PegFile("/Users/sam/SoftDevII/GroupProject/project-1-03-01/data/5_5.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Location from = null;
        Location to = null;
        Location[] locations = game.getLocations();
        for (Location location : locations) {
            if (location.getRow() == 2 && location.getCol() == 0) {
                from = location;
            }

            else if (location.getRow() == 2 && location.getCol() == 2) {
                to = location;
            }
        }

        try{
            game.MakeMove(new Move(from, to));
        } catch (PegGameException e){
            e.printStackTrace();
        }
        PegGameConfig config = new PegGameConfig(game);
        assertTrue(config.isValid());
    }

    @Test

    public void testIs_validFalse(){
        // setup
        PegGame game = null;
        try {
            game = ReadFile.PegFile("/Users/sam/SoftDevII/GroupProject/project-1-03-01/data/stalePeg.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Location from = null;
        Location to = null;
        Location[] locations = game.getLocations();
        for (Location location : locations) {
            if (location.getRow() == 0 && location.getCol() == 1) {
                from = location;
            }

            else if (location.getRow() == 0 && location.getCol() == 3) {
                to = location;
            }
        }

        try{
            game.MakeMove(new Move(from, to));
        } catch (PegGameException e){
            e.printStackTrace();
        }
        PegGameConfig config = new PegGameConfig(game);
        assertFalse(config.isValid());
    }

    @Test
    public void testGetSuccessors(){
        PegGame game = null;
        PegGame expected = null;
        try{
            game = ReadFile.PegFile("/Users/sam/SoftDevII/GroupProject/project-1-03-01/data/One_move.txt");
            expected = ReadFile.PegFile("/Users/sam/SoftDevII/GroupProject/project-1-03-01/data/the_move.txt");
        } catch(IOException e){
            e.printStackTrace();
        }

        PegGameConfig config = new PegGameConfig(game);
        PegGameConfig exe = new PegGameConfig(expected);
        List<backtracker.Configuration> successors = (ArrayList<backtracker.Configuration>) config.getSuccessors();
        
        assertEquals(exe, successors.get(0));
    }

    public static void main(String[] args) {
        PegGame game = null;
        PegGame expected = null;
        try{
            game = ReadFile.PegFile("/Users/sam/SoftDevII/GroupProject/project-1-03-01/data/One_move.txt");
            expected = ReadFile.PegFile("/Users/sam/SoftDevII/GroupProject/project-1-03-01/data/the_move.txt");
        } catch(IOException e){
            e.printStackTrace();
        }

        PegGameConfig config = new PegGameConfig(game);
        PegGameConfig exe = new PegGameConfig(expected);
        List<backtracker.Configuration> successors = (ArrayList<backtracker.Configuration>) config.getSuccessors();
    }
}

