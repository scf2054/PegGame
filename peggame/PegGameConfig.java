package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import backtracker.Configuration;
import peggame.PegGame.GameState;

public class PegGameConfig implements Configuration{
    private final PegGame game;

    public PegGameConfig(PegGame game){
        this.game = game;
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();
        Set<Move> moves = (Set<Move>) game.getPossibleMoves();



        return successors;
    }

    @Override
    public boolean isValid() {
        return game.getGameState() != GameState.STALEMATE;
    }   

    @Override
    public boolean isGoal() {
        return game.getGameState() == GameState.WON;
    }
    
}
