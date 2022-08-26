package com.xiii.moocowio.game.events.preventions;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerPreventions implements Listener {

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if(e.getWhoClicked().getGameMode() != GameMode.CREATIVE) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != null) {
                if (e.getCurrentItem().getType() == Material.WOOD_AXE || e.getCurrentItem().getType() == Material.APPLE || e.getCurrentItem().getType() == Material.COOKIE || e.getCurrentItem().getType() == Material.GOLDEN_CARROT || e.getCurrentItem().getType() == Material.CHEST)
                    e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            if(e.getItemDrop().getItemStack().getType() == Material.CHEST || e.getItemDrop().getItemStack().getType() == Material.WOOD_AXE || e.getItemDrop().getItemStack().getType() == Material.APPLE || e.getItemDrop().getItemStack().getType() == Material.COOKIE || e.getItemDrop().getItemStack().getType() == Material.GOLDEN_CARROT) e.setCancelled(true);
        }
    }
}
