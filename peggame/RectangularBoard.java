package peggame;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RectangularBoard implements PegGame {

    private final int rows;
    private final int cols;
    private final Set<Location> locations;

    public RectangularBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.locations = makeLocations();
    }

    /**
     * Creates the board which is a set of every single location on the board.
     * 
     * Only accessible within the rectangular board class.
     * 
     * @return set of locations on the board
     */
    private Set<Location> makeLocations() {
        Set<Location> locations = new HashSet<>();
        for(int row = 0; row < this.rows; row++) {
            for(int col = 0; col < this.cols; col++) {
                Location location = new Location(row, col);
                locations.add(location);
            }
        }
        return locations;
    }

    @Override
    public Collection<Move> getPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameState getGameState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void MakeMove(Move move) throws PegGameException {
        // TODO Auto-generated method stub
        
    }


    
}
