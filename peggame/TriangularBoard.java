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
        Collection<Move> possible = new HashSet<>();
        for(Location location : this.locations){
            if(location.hasPeg()){
                int up2 = location.getRow()+2;
                int up1 = location.getRow()+1;
                int down2 = location.getRow()-2;
                int down1 = location.getRow()-1;
                int right2 = location.getCol()+2;
                int right1 = location.getCol()+1;
                int left2 = location.getCol()-2;
                int left1 = location.getCol()-1;

                int[] movesV = {location.getRow(), location.getRow(), location.getRow(), location.getRow(),up2,up1,down2,down1,down2,down1,up2,up1};
                int[] movesH = {right2, right1, left2, left1,right2,right1,right2,right1,left2,left1,left2,left1};

                for(int i = 0; i < movesV.length; i+=2) {
                    Location moveTo = getLocation(movesV[i], movesH[i]);
                    Location target = getLocation(movesV[i+1], movesH[i+1]);
                    if(moveTo != null && target.hasPeg() && !moveTo.hasPeg()){
                        Move moveV = new Move(location, moveTo);
                        possible.add(moveV);
                    }
                    
                }
            }
        }
        return possible;
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

    public Location getLocation(int row, int col) {
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
