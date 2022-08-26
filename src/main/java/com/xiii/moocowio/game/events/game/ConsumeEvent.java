package com.xiii.moocowio.game.events.game;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.game.manager.BackpackManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ConsumeEvent implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        e.setCancelled(true);
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            TempData data = Data.data.getTempData(e.getPlayer());
            if (data.foodAmount >= 10) {
                if (e.getPlayer().getHealth() < 20) {
                    if (e.getPlayer().getInventory().getItemInHand().getType() == Material.APPLE) {
                        BackpackManager.updateBackpack(e.getPlayer(), "FOOD", "REMOVE", 10);
                        e.getPlayer().setHealth(e.getPlayer().getHealth() + 2);
                    }
                    if (e.getPlayer().getInventory().getItemInHand().getType() == Material.COOKIE) {
                        BackpackManager.updateBackpack(e.getPlayer(), "FOOD", "REMOVE", 10);
                        e.getPlayer().setHealth(e.getPlayer().getHealth() + 4);
                    }
                    if (e.getPlayer().getInventory().getItemInHand().getType() == Material.GOLDEN_CARROT) {
                        BackpackManager.updateBackpack(e.getPlayer(), "FOOD", "REMOVE", 10);
                        e.getPlayer().setHealth(e.getPlayer().getHealth() + 6);
                    }
                } else e.getPlayer().sendMessage("§7[§6§lMCI§7] §cYou already have all your health!");
            } else e.getPlayer().sendMessage("§7[§6§lMCI§7] §cYou don't have enough food!");
        });
    }
}
