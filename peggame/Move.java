package peggame;

public class Move {
    private final Location from;
    private final Location to;

    public Move(Location from, Location to){
        this.from = from;
        this.to = to;
    }

    public Location getFrom(){return from;}
    public Location getTo(){return to;}

    @Override
    public boolean equals(Object o){
        if(o instanceof Move){
            Move other = (Move) o;
            return this.from == other.from && this.to == other.to;
        }
        return false;
    }

    @Override
    public String toString(){
        return("from "+from+" to "+to);
    }

    @Override
    public int hashCode(){
        return toString().hashCode();
    }
}
