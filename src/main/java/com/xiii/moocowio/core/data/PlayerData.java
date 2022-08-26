package com.xiii.moocowio.core.data;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData implements java.io.Serializable {

    public UUID uuid;
    public Player player;
    public String name;

    public boolean isBanned;

    public int woodAmount;
    public int stoneAmount;
    public int foodAmount;
    public int goldAmount;

    public int backpackLevel;
    public int backpack_limit_wood;
    public int backpack_limit_stone;
    public int backpack_limit_food;
    public int backpack_limit_gold;
    public int backpack_goldRequired;
    public int backpack_capacity;
    public int backpack_upgradeCapacity;

    public int currentAge;
    public int currentExp;
    public int requiredExp;
    public double expBar;

    public String currentFoodType;
    public boolean saveProgress;



    public PlayerData(String name, UUID uuid) {
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
