package com.xiii.moocowio.game.events.game;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.PlayerData;
import com.xiii.moocowio.core.data.ServerData;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.core.utils.FileUtil;
import com.xiii.moocowio.game.manager.BackpackManager;
import com.xiii.moocowio.game.manager.LevelManager;
import com.xiii.moocowio.game.manager.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GameDeathEvent implements Listener {

    @EventHandler
    public void onGameDeath(PlayerDeathEvent e) {
        if(e.getEntity() != null & e.getEntity().getKiller() == null) e.setDeathMessage("§7[§6§lMCI§7] §c" + e.getEntity().getName() + " §7died!");
        if(e.getEntity() != null && e.getEntity().getKiller() != null) e.setDeathMessage("§7[§6§lMCI§7] §c" + e.getEntity().getKiller().getName() + " §7has killed §c" + e.getEntity().getName());
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {
            ServerData server = Data.data.getServerData(Bukkit.getServer());
            if(e.getEntity() != null) {
                PlayerData data = Data.data.getUserData(e.getEntity());
                TempData temp = Data.data.getTempData(e.getEntity());

                e.setNewExp(0);
                e.setNewLevel(0);
                temp.isInGame = false;
                if(e.getEntity().getKiller() == null) e.getEntity().sendTitle("§c§lYOU DIED!", "");
                Bukkit.getScheduler().runTask(MooCowIO.instance, () -> e.getEntity().teleport(server.lobbySpawn));
                if(data.saveProgress) {
                    data.woodAmount = temp.woodAmount;
                    data.stoneAmount = temp.stoneAmount;
                    data.foodAmount = temp.foodAmount;
                    data.goldAmount = temp.goldAmount;
                    data.currentAge = temp.currentAge;
                    data.currentExp = temp.currentExp;
                    data.requiredExp = temp.requiredExp;
                    data.expBar = temp.expBar;
                    data.currentFoodType = temp.currentFoodType;
                    data.backpackLevel = temp.backpackLevel;
                    data.backpack_capacity = temp.backpack_capacity;
                    data.backpack_goldRequired = temp.backpack_goldRequired;
                    data.backpack_limit_food = temp.backpack_limit_food;
                    data.backpack_limit_stone = temp.backpack_limit_stone;
                    data.backpack_limit_wood = temp.backpack_limit_wood;
                    data.backpack_limit_gold = temp.backpack_limit_gold;
                    data.backpack_upgradeCapacity = temp.backpack_upgradeCapacity;
                    data.saveProgress = false;
                    FileUtil.savePlayerData(Data.data.getUserData(e.getEntity()));
                    SoundManager.playSound(e.getEntity().getPlayer(), 3);
                    e.getEntity().getInventory().clear(); Data.data.clearPlayerTemp(e.getEntity()); Data.data.registerUser(e.getEntity());
                    e.getEntity().sendMessage("§8§M+---------------------------------------+");
                    e.getEntity().sendMessage("                  §6§LSAVED");
                    e.getEntity().sendMessage("   §fYour previous progress was §a§nsaved");
                    e.getEntity().sendMessage("   §fHere is the list of the saved progress:");
                    e.getEntity().sendMessage("");
                    e.getEntity().sendMessage("   §a+ " + data.woodAmount + " Wood");
                    e.getEntity().sendMessage("   §a+ " + data.stoneAmount + " Stone");
                    e.getEntity().sendMessage("   §a+ " + data.foodAmount + " Food");
                    e.getEntity().sendMessage("   §a+ " + data.currentExp + " Exp / " + data.currentAge + " Level");
                    e.getEntity().sendMessage("   §a+ " + data.backpackLevel + " Backpack level");
                    e.getEntity().sendMessage("   §c- Position not saved.");
                    e.getEntity().sendMessage("   §c- Gold amount not saved.");
                    e.getEntity().sendMessage("   §c- Placed blocks not saved.");
                    e.getEntity().sendMessage("");
                    e.getEntity().sendMessage("   §4§LWARNING: §cYour Progress Saver has been used!");
                    e.getEntity().sendMessage("   §cYour next death will not be saved without purchasing");
                    e.getEntity().sendMessage("   §ca new progress saver at the shop.");
                    e.getEntity().sendMessage("§8§M+---------------------------------------+");
                } else {
                    e.getEntity().getInventory().clear();
                    Data.data.clearPlayerTemp(e.getEntity());
                    Data.data.registerUser(e.getEntity());
                    e.getEntity().setLevel(0);
                    e.getEntity().setExp(0f);
                    SoundManager.playSound(e.getEntity().getPlayer(), 2);
                    e.getEntity().sendMessage("§8§M+---------------------------------------+");
                    e.getEntity().sendMessage("                  §c§lYOU DIED");
                    e.getEntity().sendMessage("   §fYour previous progress was §c§nnot saved");
                    e.getEntity().sendMessage("   §fYou can buy an upgrade to save it next time!");
                    e.getEntity().sendMessage("");
                    e.getEntity().sendMessage("   §6  §8- §6Progress Saver (x1) - 10,000g");
                    e.getEntity().sendMessage("");
                    e.getEntity().sendMessage("   §fYou can buy it at the §b/shop");
                    e.getEntity().sendMessage("§8§M+---------------------------------------+");
                }
            }
            if(e.getEntity() != null && e.getEntity().getKiller() != null) {
                if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {
                    e.getEntity().sendTitle("§c§lYOU DIED!", "§fYou were killed by §c" + e.getEntity().getKiller().getName());
                    e.getEntity().getKiller().sendMessage("§7[§6§lMCI§7] §6+100g §8| §e" + e.getEntity().getName() + "'s kill");
                    LevelManager.updateLevel(e.getEntity().getKiller());
                    BackpackManager.updateBackpack(e.getEntity().getKiller(), "GOLD", "ADD", 100);
                } else {
                    e.getEntity().sendTitle("§c§lYOU DIED!", "§fYou were killed by §c" + e.getEntity().getKiller().getType().getName());
                    e.setDeathMessage("§7[§6§lMCI§7] §c" + e.getEntity().getKiller().getType().getName() + " §7has killed §c" + e.getEntity().getName());
                }
            }
        });
    }
}
