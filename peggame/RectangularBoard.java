package peggame;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RectangularBoard implements PegGame {

    private final int rows;
    private final int cols;
    private final Location[] locations;
    private final Set<Move> movesMade;

    public RectangularBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.locations = makeLocations();
        this.movesMade = new HashSet<>();
    }

    /**
     * Creates the board which is an Array of every single location on the board.
     * 
     * Only accessible within the rectangular board class.
     * 
     * @return set of locations on the board
     */
    private Location[] makeLocations() {
        Location[] locations = new Location[this.rows * this.cols];
        int index = 0;
        for(int row = 0; row < this.rows; row++) {
            for(int col = 0; col < this.cols; col++) {
                Location location = new Location(row, col);
                locations[index] = location;
                index++;
            }
        }
        return locations;
    }

    @Override
    public Collection<Move> getPossibleMoves() {
        Set<Move> possible = new HashSet<>();
        for(Location location : this.locations){
            if(location.hasPeg()){
                // verticle
                // If current location plus 2 isn't off the board
                // If the location 1 away from where we are has a peg
                // If the location 2 away does not have one
                    Location moveTo = getLocation(location.getRow()+2, location.getCol());
                    Location target = getLocation(location.getRow()+1, location.getCol());
                    if(moveTo != null && target.hasPeg() && !moveTo.hasPeg()){
                        Move moveV = new Move(location, moveTo);
                        possible.add(moveV);
                    }
                    
                    moveTo = getLocation(location.getRow()-2, location.getCol());
                    target = getLocation(location.getRow()-1, location.getCol());
                    if(moveTo != null && target.hasPeg() && !moveTo.hasPeg()){
                        Move moveV = new Move(location, moveTo);
                        possible.add(moveV);
                    }

                // horizontal
                    moveTo = getLocation(location.getRow(), location.getCol()+2);
                    target = getLocation(location.getRow(), location.getCol()+1);
                    if(moveTo != null && target.hasPeg() && !moveTo.hasPeg()){
                        Move moveV = new Move(location, moveTo);
                        possible.add(moveV);
                    }
                    
                    moveTo = getLocation(location.getRow(), location.getCol()-2);
                    target = getLocation(location.getRow(), location.getCol()-1);
                    if(moveTo != null && target.hasPeg() && !moveTo.hasPeg()){
                        Move moveV = new Move(location, moveTo);
                        possible.add(moveV);
                    }

                // diagonal
                    moveTo = getLocation(location.getRow()+2, location.getCol()+2);
                    target = getLocation(location.getRow()+1, location.getCol()+1);
                    if(moveTo != null && target.hasPeg() && !moveTo.hasPeg()){
                        Move moveV = new Move(location, moveTo);
                        possible.add(moveV);
                    }
                    
                    moveTo = getLocation(location.getRow()-2, location.getCol()-2);
                    target = getLocation(location.getRow()-1, location.getCol()-1);
                    if(moveTo != null && target.hasPeg() && !moveTo.hasPeg()){
                        Move moveV = new Move(location, moveTo);
                        possible.add(moveV);
                    }
                    
                    moveTo = getLocation(location.getRow()+2, location.getCol()-2);
                    target = getLocation(location.getRow()+1, location.getCol()-1);
                    if(moveTo != null && target.hasPeg() && !moveTo.hasPeg()){
                        Move moveV = new Move(location, moveTo);
                        possible.add(moveV);
                    }
                    
                    moveTo = getLocation(location.getRow()-2, location.getCol()+2);
                    target = getLocation(location.getRow()-1, location.getCol()+1);
                    if(moveTo != null && target.hasPeg() && !moveTo.hasPeg()){
                        Move moveV = new Move(location, moveTo);
                        possible.add(moveV);
                    }
            }
        }
        
        return null;
    }

    private Location getLocation(int row, int col){
        for(Location location : this.locations){
            if(location.getRow() == row && location.getCol() == col){
                return location;
            }
        }
        return null;
    }

    @Override
    public GameState getGameState() {
        // The game is won is there is only 1 peg on board
        // The game is not started if movesMade.size() = 0
        // The game is in progress if getPossible moves is not empty
        // The game is stalemate if getPossible moves is empty and movesMade is not empty
        
        return null;
    }

    @Override
    public void MakeMove(Move move) throws PegGameException {
        Location from = move.getFrom();
        Location to = move.getTo();


        
    }

    /**
     * pegs are "o" and spaces are "-"
     * 
     * @return string of the board
     */
    @Override
    public String toString() {
        String board = "";
        Location location;
        for(int i = 0; i < this.locations.length; i++) {
            location = this.locations[i];
            if(i % this.cols == 0 && i != 0) {
                board += "\n";
            }

            if(location.hasPeg()) {
                board += "o";
            } else {
                board += "-";
            }
        }
        return board;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public Location[] getLocations() {
        return this.locations;
    }
}
