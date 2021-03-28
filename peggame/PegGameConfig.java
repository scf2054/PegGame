package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backtracker.Backtracker;
import backtracker.Configuration;
import peggame.PegGame.GameState;

public class PegGameConfig implements Configuration{
    private final PegGame game;

    public PegGameConfig(PegGame game){
        this.game = game;
    }

    /**
     * Returns a list of all the valid configurations of a board
     * 
     * @return Collection<Configuration> a list of all the possible configurations
     */
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

    /**
     * @return boolean if the game is not in a stalemate
     */
    @Override
    public boolean isValid() {
        return game.getGameState() != GameState.STALEMATE;
    }   

    /**
     * @return boolean if the game state has been won
     */
    @Override
    public boolean isGoal() {
        return game.getGameState() == GameState.WON;
    }
    
    public PegGame getGame(){ return game; }

    @Override
    public String toString(){
        String string = game.toString() + "\n\n[";
        int i = 0;
        for(Move move : game.getMovesMade()){
            if(i == game.getMovesMade().size() - 1) {
                string += move + "]";
            } else {
                string += move + ", ";
            }
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

    /**
     * Through user input, this returns the correct solution to the game
     * the user wants to solve.
     * Returns nothing if there is no solution.
     * 
     * @param game that the user wants to find a solution to
     * 
     * @return the successful configuration, null if failed
     */
    public static Configuration getSolution(PegGame game) {
        Backtracker backtracker = new Backtracker(false);
        PegGameConfig config = new PegGameConfig(game);
        Configuration solution = backtracker.solve(config);
        if(solution == null) {
            return null;
        }
        return solution;
    }
}
