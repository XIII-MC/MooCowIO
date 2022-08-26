package com.xiii.moocowio.game.events.preventions;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MapPreventions implements Listener {

    @EventHandler
    public void onMapPlace(BlockPlaceEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            if(e.getBlock().getType() == Material.CHEST) e.setCancelled(true);
            if(e.getBlock().getLocation().getY() >= 27 || e.getBlock().getLocation().getZ() >= 224 || e.getBlock().getLocation().getX() >= 224 || e.getBlock().getLocation().getZ() <= -224 || e.getBlock().getLocation().getX() <= -224) e.setCancelled(true);
        }
     }

    @EventHandler
    public void onMapMobSpawn(EntitySpawnEvent e) {
        if(e.getEntity().getLocation().getY() >= 27 || e.getEntity().getLocation().getZ() >= 224 || e.getEntity().getLocation().getX() >= 224 || e.getEntity().getLocation().getZ() <= -224 || e.getEntity().getLocation().getX() <= -224) e.setCancelled(true);
    }

    @EventHandler
    public void onMapDamage(EntityDamageByEntityEvent e) {
        if(e.getEntity().getLocation().getY() >= 27) e.setCancelled(true);
     }

    @EventHandler
    public void onMapExplode(EntityExplodeEvent e) {
        e.blockList().clear();
     }

    @EventHandler
    public void onMapBreak(BlockBreakEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            if(e.getBlock().getType() == Material.LOG || e.getBlock().getType() == Material.STONE || e.getBlock().getType() == Material.LEAVES) e.setCancelled(true);
        }
    }

}
