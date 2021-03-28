package peggame;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RectangularBoard implements PegGame {

    private final int rows;
    private final int cols;
    private Location[] locations;
    private Set<Move> movesMade;

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

    /**
     * Iterates over each location in this locations, if the location has a peg then check every possible move
     * if the move doesnt have a peg and isnt off the board, add it to the possible set
     * 
     * tested manually
     * 
     * @return set of all the possible moves from a specific location
     */
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

                int[] movesV = {up2, up1, down2, down1, location.getRow(), location.getRow(), location.getRow(), location.getRow(), up2, up1, down2, down1, up2, up1, down2, down1};
                int[] movesH = {location.getCol(), location.getCol(), location.getCol(), location.getCol(), right2, right1, left2, left1, right2, right1, left2, left1, left2, left1, right2, right1};
                // verticle
                // If current location plus 2 isn't off the board
                // If the location 1 away from where we are has a peg
                // If the location 2 away does not have one
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

    /**
     * Helper method for getPossibleMoves that allows us to get the 
     * location we want from the locations list.
     * 
     * return null if its off the board
     * 
     * @param row of location we want to get
     * @param col of location we want to get
     * @return location from the locations list
     */
    private Location getLocation(int row, int col){
        for(Location location : this.locations){
            if(location.getRow() == row && location.getCol() == col){
                return location;
            }
        }
        return null;
    }

    /**
     * returns the state of the game depending on the amount of
     * pegs and moves made by the user
     * 
     * @return GameState enum
     */
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

    /**
     * Makes a move depending on user input
     * 
     * Moves the current peg to the space the user wants it to,
     * removes the peg right in front of the current peg
     * 
     * @param Move that the user wants to make
     * @throws PegGameException if the move is invalid
     */
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RectangularBoard) {
            RectangularBoard other = (RectangularBoard)obj;
            return this.toString().equals(other.toString());
        }
        return false;
    }

    /**
     * Copy every location into the new PegGame
     * 
     * @return a successor for the game
     */
    @Override
    public RectangularBoard deepCopy() {
        RectangularBoard copy = new RectangularBoard(this.rows, this.cols);
        Location[] newLocations = new Location[this.locations.length];
        for(int i = 0; i < newLocations.length; i++) {
            newLocations[i] = this.locations[i];
        }

        copy.setLocations(newLocations);
        copy.setMovesMade(this.movesMade);

        return copy;
    }
    public Set<Move> getMovesMade() { return movesMade; }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public Location[] getLocations() {
        return this.locations;
    }

    public void setLocations(Location[] newLocations) {
        this.locations = newLocations;
    }

    public void setMovesMade(Set<Move> newMoves) {
        this.movesMade = newMoves;
    }

    public static void main(String[] args) {
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

        // // analyze
        // assertFalse(board.equals(copy));
    }
}
