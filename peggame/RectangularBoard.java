package peggame;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RectangularBoard implements PegGame{

    private final int rows;
    private final int col;
    private final Set<Location> locations;

    public RectangularBoard(int rows, int col) {
        this(rows, col, new HashSet<Location>());
    }

    public RectangularBoard(int rows, int col, Set<Location> locations) {
        this.rows = rows;
        this.col = col;
        this.locations = locations;
    }

    private Set<Location> makeLocations() {

        return null;
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
