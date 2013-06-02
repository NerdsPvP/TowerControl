package com.nerdspvp.tc;

import com.nerdspvp.tc.signentities.PotionTowerEntity;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

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
                        Sign signBelow = (Sign)e.getClickedBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getState();
                        if(PotionTowerEntity.validate(signBelow)){
                            new PotionTowerEntity().onTrigger(signBelow, e.getClickedBlock(), e.getPlayer());
                        }
                        break;
                }
                break;
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        TowerControl.instances.get(0).addPlayer(e.getPlayer(), "gold");
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        TCInstance instance = TowerControl.getPlayerInstance(e.getPlayer().getName());
        Game sg = (Game) instance.getCurrentGame();
        e.setRespawnLocation(sg.getWorld().getWorld().getSpawnLocation());
        sg.spawnPlayer(e.getPlayer());
    }
}
