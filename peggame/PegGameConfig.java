package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import backtracker.Configuration;
import peggame.PegGame.GameState;

public class PegGameConfig implements Configuration{
    private final PegGame game;

    public PegGameConfig(PegGame game){
        this.game = game;
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        return null;
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
