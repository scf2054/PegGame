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
        int total = 0;
        for(int i = 1; i<size; i++){
            total += i;
        }
        Location[] locations = new Location[total];
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
        if(movesMade.size() == 0){
            // The game is not started if movesMade.size() = 0
            return GameState.NOT_STARTED;
        } else {
            // The game is in progress if getPossible moves is not empty
            Set<Move> possible = (Set<Move>) getPossibleMoves();
            if(possible.size() != 0){
                return GameState.IN_PROGRESS;
            } else {
                int numPegs = 0;
                for(Location location : locations){
                    if(location.hasPeg()){
                        numPegs++;
                    }
                }
                // The game is won is there is only 1 peg on board
                // The game is stalemate if getPossible moves is empty and movesMade is not empty
                if(numPegs > 1){
                    return GameState.STALEMATE;
                } else {
                    return GameState.WON;
                }
            }
        }
    }

    @Override
    public void MakeMove(Move move) throws PegGameException {
        Set<Move> possibleMoves = (Set<Move>)getPossibleMoves();
        if (!possibleMoves.contains(move)) {
            throw new PegGameException("Move is not possible");
        }
        else {
            Location from = move.getFrom();
            Location to = move.getTo(); 
            movesMade.add(move);
            // original location remove peg
            Location location = getLocation(from.getRow(), from.getCol());
            location.setPeg(false);

            // new location add peg
            location = getLocation(to.getRow(), to.getCol());
            location.setPeg(true);
            // remove peg in the middle
                // x is from.cols
                int midCol = from.getCol();
                // y is from.rows
                int midRow = from.getRow();

                // if x + 2 is to.col 
                if (midCol + 2 == to.getCol()) {
                    // midCol = x + 1
                    midCol += 1;
                }
                // else if x - 2 is to.col
                else if (midCol - 2 == to.getCol()) {
                    // midCol = x - 1
                    midCol -= 1;
                }
                // if y + 2 is to.row
                if (midRow + 2 == to.getRow()) {
                        // midRow = y + 1
                        midRow += 1;
                }
                // else if y - 2 is to.row
                else if (midRow - 2 == to.getRow()) {
                    // midRow = y - 1
                    midRow -= 1;
                }
                
                // get location at midRow, midCol
                location = getLocation(midRow, midCol);
                // set hasPeg to false
                location.setPeg(false);
        }       
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
