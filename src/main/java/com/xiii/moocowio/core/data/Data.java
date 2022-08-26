package com.xiii.moocowio.core.data;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public enum Data {
    data;

    public final ArrayList<PlayerData> users = new ArrayList<>();
    public final ArrayList<TempData> temp = new ArrayList<>();
    public final ArrayList<ServerData> servers = new ArrayList<>();

    public boolean isServerAlreadyRegistered(Server p) {
        return getServerData(p) != null;
    }
    public boolean isAlreadyRegistered(Player p) {
        return getUserData(p) != null;
    }
    public boolean isTempAlreadyRegistered(Player p) {
        return getTempData(p) != null;
    }

    public void registerUser(Player p) {
        if (!isAlreadyRegistered(p)) {
            PlayerData pd = new PlayerData(p.getName(), p.getUniqueId());
            this.users.add(pd);
        }

        if (!isTempAlreadyRegistered(p)) {
            TempData pd = new TempData(p.getName(), p.getUniqueId());
            this.temp.add(pd);
        }
    }

    public void registerServer(Server p) {
        if (!isServerAlreadyRegistered(p)) {
            ServerData pd = new ServerData();
            this.servers.add(pd);
        }
    }


    public PlayerData getUserData(Player p) {
        for (PlayerData user : users) {
            if (user.uuid.toString().contains(p.getUniqueId().toString())) {
                return user;
            }
        }
        return null;
    }

    public TempData getTempData(Player p) {
        for (TempData user : temp) {
            if (user.uuid.toString().contains(p.getUniqueId().toString())) {
                return user;
            }
        }
        return null;
    }

    public ServerData getServerData(Server p) {
        for (ServerData user : servers) {
            return user;
        }
        return null;
    }


    public void clearCache() {
        this.temp.clear();
    }

    public void clearPlayerTemp(Player p) {
        if(isTempAlreadyRegistered(p)) this.temp.remove(p);
    }

    public void deletePlayerData(Player p) {
        if(isAlreadyRegistered(p)) this.users.remove(p);
    }
}
