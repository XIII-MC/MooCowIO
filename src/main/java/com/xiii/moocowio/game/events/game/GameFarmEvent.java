package com.xiii.moocowio.game.events.game;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.game.manager.BackpackManager;
import com.xiii.moocowio.game.manager.LevelManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

public class GameFarmEvent implements Listener {

    @EventHandler
    public void onFarm(BlockDamageEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            TempData player = Data.data.getTempData(e.getPlayer());
            ItemStack item = new ItemStack(Material.WOOD_AXE);
            ItemMeta item_meta = item.getItemMeta();
            item_meta.setDisplayName("§a§lMAIN");
            item_meta.setLore(Arrays.asList("\n", "§7Main can be used to kill/break/farm"));
            item_meta.setUnbreakable(true);
            item.setItemMeta(item_meta);
            e.getPlayer().getInventory().setItem(0, item);
            if (e.getPlayer().getInventory().getItemInHand().getType() == Material.WOOD_AXE) {
                if (e.getBlock().getType() == Material.LEAVES || e.getBlock().getType() == Material.LOG || e.getBlock().getType() == Material.STONE) {
                    e.getPlayer().getInventory().setItem(0, new ItemStack(Material.AIR));
                    Bukkit.getScheduler().runTaskLater(MooCowIO.instance, () -> {
                        e.getPlayer().getInventory().setItem(0, item);
                    }, 2);
                    if (System.currentTimeMillis() - player.lastBreak >= 1000) {
                        LevelManager.updateLevel(e.getPlayer());
                        player.lastBreak = System.currentTimeMillis();

                        if (e.getBlock().getType() == Material.LOG) BackpackManager.updateBackpack(e.getPlayer(), "WOOD", "ADD", 1);

                        if (e.getBlock().getType() == Material.STONE) BackpackManager.updateBackpack(e.getPlayer(), "STONE", "ADD", 1);

                        if (e.getBlock().getType() == Material.LEAVES) BackpackManager.updateBackpack(e.getPlayer(), "FOOD", "ADD", 1);

                    }
                } else if (e.getBlock().getType() == Material.LEAVES || e.getBlock().getType() == Material.LOG || e.getBlock().getType() == Material.STONE) e.getPlayer().sendMessage("§7[§6§lMCI§7] §cYou must use your Main to collect resources!");
            }
        });
    }
}
