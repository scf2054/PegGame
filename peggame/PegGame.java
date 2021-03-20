package peggame;

import java.util.Collection;

public interface PegGame {

    public enum GameState {
        NOT_STARTED, 
        IN_PROGRESS,
        STALEMATE,
        WON;
    }

    public class PegGameException extends Exception {
        public PegGameException(String message) throws PegGameException {
            throw new PegGameException("Invalid move attempted.");
        }
    }

    Collection<Move> getPossibleMoves();
    GameState getGameState();
    void MakeMove(Move move) throws PegGameException;
    Location[] getLocations();
    
}
