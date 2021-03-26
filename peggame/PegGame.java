package peggame;

import java.util.Collection;

public interface PegGame {

    public enum GameState {
        NOT_STARTED, 
        IN_PROGRESS,
        STALEMATE,
        WON;
    }

    Collection<Move> getPossibleMoves();
    GameState getGameState();
    void MakeMove(Move move) throws PegGameException;
    Location[] getLocations();

    default PegGame deepCopy() {
        throw new UnsupportedOperationException();
    }

}
