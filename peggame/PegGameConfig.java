package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
        Set<Move> moves = (HashSet<Move>) game.getPossibleMoves();
        PegGameConfig successor = null;
        for (Move move : moves) {
            successor = new PegGameConfig(game.deepCopy());
            try {
                successor.getGame().MakeMove(move);
            }
            catch (PegGameException e) {
                e.printStackTrace();
            }
            successors.add(successor);
        }
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
    
    public PegGame getGame(){ return game; }

    @Override
    public String toString(){
        String string = game.toString() + " ";
        for(Move move : game.getMovesMade()){
            string += move + ", ";
        }
        return string;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof PegGameConfig){
            PegGameConfig other = (PegGameConfig) o;
            return this.getGame().equals(other.getGame());
        }
        return false;
    }
}
