package com.xiii.moocowio.game.events.game;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.game.displays.BackpackDisplay;
import com.xiii.moocowio.game.manager.BackpackManager;
import com.xiii.moocowio.game.manager.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GameBackpackUpgrade implements Listener {

    @EventHandler
    public void onBackpackUpgrade(InventoryClickEvent e) {
        if(e.getCurrentItem() == null) return;
        if(e.getCurrentItem().getType() == null) return;
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            TempData data = Data.data.getTempData((Player) e.getWhoClicked());
            if(e.isRightClick()) {
                if (e.getCurrentItem().getType() == Material.CHEST) {
                    e.setCancelled(true);
                    if (data.goldAmount >= data.backpack_goldRequired) {

                        data.backpackLevel += 1;
                        data.backpack_capacity += 300;
                        data.goldAmount -= data.backpack_goldRequired;
                        data.backpack_upgradeCapacity = data.backpack_capacity + 300;
                        data.backpack_goldRequired += 500;

                        data.backpack_limit_wood += 100;
                        data.backpack_limit_stone += 100;
                        data.backpack_limit_food += 100;
                        data.backpack_limit_gold += 500;

                        SoundManager.playSound((Player) e.getWhoClicked(), 7);
                        ((Player) e.getWhoClicked()).sendTitle("§2§lꜛ §6§l§nBACKPACK UPGRADE§r §2§lꜛ", "§e" + (data.backpackLevel - 1) + " §f➩ §e" + data.backpackLevel);
                        BackpackManager.updateBackpack((Player) e.getWhoClicked(), "GOLD", "REMOVE", data.backpack_goldRequired);
                    } else e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cYou don't have enough gold!");
                    SoundManager.playSound((Player) e.getWhoClicked(), 9);
                }
            } else {
                if (data.isInGame && e.getCurrentItem().getType() == Material.CHEST) {
                    e.setCancelled(true);
                    Bukkit.getScheduler().runTask(MooCowIO.instance, () -> {
                        e.getWhoClicked().closeInventory();
                        BackpackDisplay.backpackGUI((Player) e.getWhoClicked(), 0);
                    });
                }
            }
        });
    }

}
