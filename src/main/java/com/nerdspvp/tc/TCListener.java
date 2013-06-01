package com.nerdspvp.tc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class TCListener implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){
        if(!e.getPlayer().hasPermission("towercontrol.bypass.pickupitem"))
            e.setCancelled(true);
    }

    @EventHandler
    public void onDie(PlayerDeathEvent e){
        e.setDroppedExp(9);
        e.getDrops().clear();
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(!e.getPlayer().hasPermission("towercontrol.bypass.blockplace"))
            e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(!e.getPlayer().hasPermission("towercontrol.bypass.blockbreak"))
            e.setCancelled(true);
    }
}
