package com.nerdspvp.tc;

import com.nerdspvp.tc.signentities.PotionTowerEntity;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        TowerControl.prepareForQuit(e.getPlayer());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        switch(e.getAction()){
            case PHYSICAL:
                switch(e.getClickedBlock().getType()){
                    case STONE_PLATE:
                        Sign signBelow = (Sign)e.getClickedBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN);
                        if(PotionTowerEntity.validate(signBelow)){
                            new PotionTowerEntity().onTrigger(signBelow, e.getClickedBlock());
                        }
                        break;
                }
                break;
        }
    }
}