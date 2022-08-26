package com.xiii.moocowio.game.manager;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.game.displays.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BackpackManager {

    public static void updateBackpack(Player p, String mat, String action, int amount) {
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            TempData data = Data.data.getTempData(p);
            assert data != null;
            if (action.equalsIgnoreCase("ADD")) {
                if (mat.equalsIgnoreCase("WOOD")) {
                    if (data.backpack_limit_wood - (data.woodAmount + amount) >= 0) data.woodAmount += amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Wood)"); //SoundManager.playSound(p, 6);
                }
                if (mat.equalsIgnoreCase("STONE")) {
                    if (data.backpack_limit_stone - (data.stoneAmount + amount) >= 0) data.stoneAmount += amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Stone)"); //SoundManager.playSound(p, 6);
                }
                if (mat.equalsIgnoreCase("FOOD")) {
                    if (data.backpack_limit_food - (data.foodAmount + amount) >= 0) data.foodAmount += amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Food)"); //SoundManager.playSound(p, 6);
                }
                if (mat.equalsIgnoreCase("GOLD")) {
                    if (data.backpack_limit_gold - (data.goldAmount + amount) >= 0) data.goldAmount += amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Gold)"); //SoundManager.playSound(p, 6);
                }
                if (mat.equalsIgnoreCase("*")) {
                    if (data.backpack_limit_wood - (data.woodAmount + amount) >= 0) data.woodAmount += amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Wood)"); //SoundManager.playSound(p, 6);
                    if (data.backpack_limit_stone - (data.stoneAmount + amount) >= 0) data.stoneAmount += amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Stone)"); //SoundManager.playSound(p, 6);
                    if (data.backpack_limit_food - (data.foodAmount + amount) >= 0) data.foodAmount += amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Food)"); //SoundManager.playSound(p, 6);
                    if (data.backpack_limit_gold - (data.goldAmount + amount) >= 0) data.goldAmount += amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYour backpack is full! §7(Gold)"); //SoundManager.playSound(p, 6);
                }
            }
            if (action.equalsIgnoreCase("REMOVE")) {
                if (mat.equalsIgnoreCase("WOOD")) {
                    if (data.woodAmount - amount >= 0) data.woodAmount -= amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Wood)"); //SoundManager.playSound(p, 6);
                }
                if (mat.equalsIgnoreCase("STONE")) {
                    if (data.stoneAmount - amount >= 0) data.stoneAmount -= amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Stone)"); //SoundManager.playSound(p, 6);
                }
                if (mat.equalsIgnoreCase("FOOD")) {
                    if (data.foodAmount - amount >= 0) data.foodAmount -= amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Food)"); //SoundManager.playSound(p, 6);
                }
                if (mat.equalsIgnoreCase("GOLD")) {
                    if (data.goldAmount - amount >= 0) data.goldAmount -= amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Gold)"); //SoundManager.playSound(p, 6);
                }
                if (mat.equalsIgnoreCase("*")) {
                    if (data.woodAmount - amount >= 0) data.woodAmount -= amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Wood)"); //SoundManager.playSound(p, 6);
                    if (data.stoneAmount - amount >= 0) data.stoneAmount -= amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Stone)"); //SoundManager.playSound(p, 6);
                    if (data.foodAmount - amount >= 0) data.foodAmount -= amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Food)"); //SoundManager.playSound(p, 6);
                    if (data.goldAmount - amount >= 0) data.goldAmount -= amount;
                    else
                        p.sendMessage("§7[§6§lMCI§7] §cYou do not have enough resources! §7(Gold)"); //SoundManager.playSound(p, 6);
                }
            }
            if (action.equalsIgnoreCase("CLEAR")) {
                if (mat.equalsIgnoreCase("WOOD")) data.woodAmount = 0;
                if (mat.equalsIgnoreCase("STONE")) data.stoneAmount = 0;
                if (mat.equalsIgnoreCase("FOOD")) data.foodAmount = 0;
                if (mat.equalsIgnoreCase("GOLD")) data.goldAmount = 0;
                if (mat.equalsIgnoreCase("*")) {
                    data.woodAmount = 0;
                    data.stoneAmount = 0;
                    data.foodAmount = 0;
                    data.goldAmount = 0;
                }
            }
            if (action.equalsIgnoreCase("SET")) {
                if (mat.equalsIgnoreCase("WOOD")) data.woodAmount = amount;
                if (mat.equalsIgnoreCase("STONE")) data.stoneAmount = amount;
                if (mat.equalsIgnoreCase("FOOD")) data.foodAmount = amount;
                if (mat.equalsIgnoreCase("GOLD")) data.goldAmount = amount;
                if (mat.equalsIgnoreCase("*")) {
                    data.woodAmount = amount;
                    data.stoneAmount = amount;
                    data.foodAmount = amount;
                    data.goldAmount = amount;
                }
            }
            p.getInventory().setItem(8, ItemUtils.customItem(Material.CHEST, "§6§lBACKPACK §7(Level " + data.backpackLevel + ")", "", "§e§nLEFT CLICK§f to open §8| §6§nRIGHT CLICK§f to upgrade", "", "§fLevel §7»§f " + data.backpackLevel, "§fCapacity §7»§f " + data.backpack_capacity, "", "   §8- §eWood §7»§e " + data.woodAmount + "§7/" + data.backpack_limit_wood, "   §8- §7Stone §7»§8 " + data.stoneAmount + "§7/" + data.backpack_limit_stone, "   §8- §cFood §7»§c " + data.foodAmount + "§7/" + data.backpack_limit_food, "   §8- §6Gold §7»§6 " + data.goldAmount + "§7/" + data.backpack_limit_gold, "", "§fUpgrade cost §7» §6" + data.backpack_goldRequired + "g", "§fUpgrade's Capacity §7» §6" + data.backpack_upgradeCapacity));
        });
    }

}
