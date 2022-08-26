package com.xiii.moocowio.core.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ServerData implements java.io.Serializable {

    public Location lobbySpawn = new org.bukkit.Location(Bukkit.getWorld("world"), 0.0, 29.0, 0.0, 0.0f, 0.0f);
    public Location mapSpawn = new org.bukkit.Location(Bukkit.getWorld("world"), 0.0, 3.0, 0.0, 0.0f, 0.0f);
}
