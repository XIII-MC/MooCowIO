package com.xiii.moocowio.game.events.game;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.PlayerData;
import com.xiii.moocowio.core.data.TempData;
import com.xiii.moocowio.core.utils.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameQuitEvent implements Listener {

    @EventHandler
    public void onGameQuit(PlayerQuitEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(MooCowIO.instance, () -> {

            PlayerData data = Data.data.getUserData(e.getPlayer());
            TempData player = Data.data.getTempData(e.getPlayer());

            if(!data.saveProgress) {
                player.isInGame = false;
                e.getPlayer().getInventory().clear();
                Data.data.clearPlayerTemp(e.getPlayer());
            }
            if(data.saveProgress) {
                data.woodAmount = player.woodAmount;
                data.stoneAmount = player.stoneAmount;
                data.foodAmount = player.foodAmount;
                data.goldAmount = player.goldAmount;
                data.currentAge = player.currentAge;
                data.currentExp = player.currentExp;
                data.requiredExp = player.requiredExp;
                data.expBar = player.expBar;
                data.currentFoodType = player.currentFoodType;
                data.backpackLevel = player.backpackLevel;
                data.backpack_capacity = player.backpack_capacity;
                data.backpack_goldRequired = player.backpack_goldRequired;
                data.backpack_limit_food = player.backpack_limit_food;
                data.backpack_limit_stone = player.backpack_limit_stone;
                data.backpack_limit_wood = player.backpack_limit_wood;
                data.backpack_limit_gold = player.backpack_limit_gold;
                data.backpack_upgradeCapacity = player.backpack_upgradeCapacity;
                FileUtil.savePlayerData(Data.data.getUserData(e.getPlayer()));
            }

        });
    }

}
