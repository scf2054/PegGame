package peggame;

public class Location {

    private int row;
    private int col;
    private boolean hasPeg;

    public Location(int row, int col) {
        this(row, col, false);
    }

    public Location(int row, int col, boolean hasPeg) {
        this.row = row;
        this.col = col;
        this.hasPeg = hasPeg;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean hasPeg() {
        return this.hasPeg;
    }

    /**
     * @param o other object the user wants to compare
     * 
     * @return boolean true if row and col are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Location) {
            Location otherL = (Location)o;
            return this.row == otherL.getRow() && this.col == otherL.getCol();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + this.row + ", " + this.col + ")";
    }

}