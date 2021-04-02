package peggame;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TriangularBoard implements PegGame{
    
    private final int size;
    private Location[] locations;
    private Set<Move> movesMade;

    public TriangularBoard(int size) {
        this.size = size;
        this.locations = makeLocations();
        this.movesMade = new HashSet<>();
    }

    private Location[] makeLocations() {
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

    public Location getLocation() {
        return null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Location[] getLocations() {
        return this.locations;
    }

    @Override
    public Set<Move> getMovesMade() {
        return this.movesMade;
    }    

    public void setLocations(Location[] newLocations) {
        this.locations = newLocations;
    }

    public void setMovesMade(Set<Move> newMoves) {
        this.movesMade = newMoves;
    }
}
