package com.xiii.moocowio.game.displays;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.PlayerData;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.game.manager.BackpackManager;
import com.xiii.moocowio.game.manager.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopDisplay implements Listener {

    public static void shopGUI(Player p) {
        Bukkit.getScheduler().runTask(MooCowIO.instance, () -> {

            SoundManager.playSound(p, 8);

            Inventory gui = Bukkit.createInventory(p.getPlayer(), 54, "                §6§l§nSHOP");
            ItemStack glass = (ItemUtils.customItem(Material.STAINED_GLASS_PANE, " ", " "));

            ItemStack progress_saver = (ItemUtils.customItem(Material.TOTEM, "§6Progress Saver", " ", "§8- §fAmount: 1", "§8- §fPrice: §610,000g", "§8- §fUsage: 1 time §7(Death)", "§8- §fUsage: §f∞ §7(Leave)", " ", "§8- §fDetails: This can prevent progress loss", "§fon §cdeath§f and when leaving. §aResources§f, §aupgrades§f, §6gold", "§fwill be saved, however your §3position§f and", "§3the blocks you placed§f will §c§nnot be saved."));

            for(int i = 0; i < 54; i++) gui.setItem(i,glass);

            gui.setItem(10, progress_saver);

            p.getPlayer().openInventory(gui);

        });

    }

    @EventHandler
    public void onShopClick(InventoryClickEvent e) {
        if(e.getView().getTitle().contains("SHOP")) {
            e.setCancelled(true);
            Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
                TempData player = Data.data.getTempData((Player) e.getWhoClicked());
                PlayerData data = Data.data.getUserData((Player) e.getWhoClicked());

                if (e.getRawSlot() == 10) {
                    if (player.goldAmount >= 10000) {
                        if (!data.saveProgress) {
                            SoundManager.playSound((Player) e.getWhoClicked(), 7);
                            BackpackManager.updateBackpack((Player) e.getWhoClicked(), "GOLD", "REMOVE", 10000);
                            data.saveProgress = true;
                            e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §aPurchase done! You bought §6x1 Progress Saver §afor §e10,000g");
                        } else
                            e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cPurchase failed! You already have an active Progress Saver!"); SoundManager.playSound((Player) e.getWhoClicked(), 9);
                    } else
                        e.getWhoClicked().sendMessage("§7[§6§lMCI§7] §cPurchase failed! You don't have enough gold!"); SoundManager.playSound((Player) e.getWhoClicked(), 9);
                }
            });
        }
    }

}
