package com.nerdspvp.tc;

import org.bukkit.entity.Player;

public class GamePlayer {

    private Player playerHandle;

    public GamePlayer(Player player){
        this.playerHandle = player;
    }

    public Player getHandle(){
        return this.playerHandle;
    }

}
