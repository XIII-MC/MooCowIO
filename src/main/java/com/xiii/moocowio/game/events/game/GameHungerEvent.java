package com.xiii.moocowio.game.events.game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class GameHungerEvent implements Listener {

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent e) {
        e.setFoodLevel(19);
    }

}
