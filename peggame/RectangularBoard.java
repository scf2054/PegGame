package peggame;

import java.util.Collection;

public class RectangularBoard implements PegGame {

    private final int rows;
    private final int cols;
    private final Location[] locations;

    public RectangularBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.locations = makeLocations();
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
