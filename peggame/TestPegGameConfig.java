package peggame;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestPegGameConfig {
    
    @Test
    public void is_goalTrue() {
        // setup
        PegGame game = null;
        try {
            game = ReadFile.PegFile("C:/Users/borna/VS Code/SoftDevII/project/project-1-03-01/data/10_10.txt");
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
            game = ReadFile.PegFile("C:/Users/earth/Documents/SoftDev2/Project01/project-1-03-01/data/10_10.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        PegGameConfig config = new PegGameConfig(game);

        // analyze
        assertFalse(config.isGoal());
    }
}
