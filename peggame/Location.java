package peggame;

public class Location {

    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
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