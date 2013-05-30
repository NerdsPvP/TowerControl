package com.nerdspvp.tc;

import org.bukkit.entity.Player;

public class GamePlayer {

    private Player playerHandle;
    private TCInstance tcInstance;

    public GamePlayer(Player player, TCInstance instance){
        this.playerHandle = player;
        this.tcInstance = instance;
    }

    public Player getHandle(){
        return this.playerHandle;
    }

    public TCInstance getTcInstance() {
        return tcInstance;
    }
}
