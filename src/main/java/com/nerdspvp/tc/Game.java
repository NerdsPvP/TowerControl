package com.nerdspvp.tc;

public abstract class Game {

    public abstract WorldWrapper getWorld();

    public abstract void addPlayerToTeam(GamePlayer gp, String teamIdentifier);

    public abstract void removePlayerFromTeam(GamePlayer gp);


}
