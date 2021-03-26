package peggame;

import java.util.Collection;

import backtracker.Configuration;

public class PegGameConfig implements Configuration{

    @Override
    public Collection<Configuration> getSuccessors() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isGoal() {
        return false;
    }
    
}
