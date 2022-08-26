package com.xiii.moocowio.core.data;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TempData {

    // Player Information
    public UUID uuid;
    public Player player;
    public String name;

    // TEMP
    public boolean tutorialPassed = false;
    public boolean isInGame = false;
    public double lastBreak = 1001;

    // Resources
    public int woodAmount = 0;
    public int stoneAmount = 0;
    public int foodAmount = 0;
    public int goldAmount = 0;
      // Backpack
    public int backpackLevel = 0;
    public int backpack_limit_wood = 100;
    public int backpack_limit_stone = 100;
    public int backpack_limit_food = 100;
    public int backpack_limit_gold = 500;
    public int backpack_goldRequired = 500;
    public int backpack_capacity = 300;
    public int backpack_upgradeCapacity = 600;

    // Exp | Level
    public int currentAge = 0;
    public int currentExp = 0;
    public int requiredExp = 100;
    public double expBar = 0;
    
    // Map
    public int mapPlacedBlocks = 0;

    public Map<Block, Player> returnBlock = new HashMap<Block, Player>();
    
    // Upgrades
    public String currentFoodType = "APPLE";



    public TempData(String name, UUID uuid) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

}
