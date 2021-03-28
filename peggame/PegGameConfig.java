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
    private final Move moveMade;
    private ArrayList<Move> movesMade;

    public PegGameConfig(PegGame game){
        this(game, null);
    }

    public PegGameConfig(PegGame game, Move moveMade) {
        this(game, moveMade, new ArrayList<Move>());
    }

    public PegGameConfig(PegGame game, Move moveMade, ArrayList<Move> movesMade) {
        this.game = game;
        this.moveMade = moveMade;
        this.movesMade = movesMade;
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
        Move newMove;
        Location from;
        Location to;
        for (Move move : moves) {
            ArrayList<Move> newMovesMade = new ArrayList<>();
            for(Move move2 : this.movesMade) {
                newMovesMade.add(move2);
            }
            from = move.getFrom();
            to = move.getTo();
            newMove = new Move(from, to);
            newMovesMade.add(newMove);
            successor = new PegGameConfig(game.deepCopy(), move, newMovesMade);
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
    public Move getMoveMade(){ return this.moveMade; }
    public ArrayList<Move> getMovesMade(){ return this.movesMade; }

    @Override
    public String toString() {
        return "Move " + this.moveMade + "\n" + this.game;
    }

    @Override
    public boolean equals(Object o) {
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
