package com.xiii.moocowio;

import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.listener.PacketListener;
import com.xiii.moocowio.core.utils.FileUtil;
import com.xiii.moocowio.game.commands.CommandGUI;
import com.xiii.moocowio.game.commands.CommandGive;
import com.xiii.moocowio.game.commands.CommandJoin;
import com.xiii.moocowio.game.displays.BackpackDisplay;
import com.xiii.moocowio.game.displays.ShopDisplay;
import com.xiii.moocowio.game.events.game.*;
import com.xiii.moocowio.game.events.lobby.LobbyJoinEvent;
import com.xiii.moocowio.game.events.lobby.LobbyQuitEvent;
import com.xiii.moocowio.game.events.preventions.MapPreventions;
import com.xiii.moocowio.game.events.preventions.PlayerPreventions;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.settings.PacketEventsSettings;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MooCowIO extends JavaPlugin {

    public static MooCowIO instance;
    public PacketListener listener;

    @Override
    public void onLoad() {
        PacketEvents.create(this);
        PacketEventsSettings settings = PacketEvents.get().getSettings();
        settings
                .fallbackServerVersion(ServerVersion.v_1_7_10)
                .compatInjector(false)
                .checkForUpdates(false);
        PacketEvents.get().load();
        PacketEvents.get().loadAsyncNewThread();
    }

    @Override
    public void onEnable() {
        instance = this;
        listener = new PacketListener();
        Data.data.registerServer(Bukkit.getServer());
        Data.data.clearCache();
        Bukkit.getPluginManager().registerEvents(new GameDeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new GameFarmEvent(), this);
        Bukkit.getPluginCommand("join").setExecutor(new CommandJoin());
        Bukkit.getPluginCommand("shop").setExecutor(new CommandGUI());
        Bukkit.getPluginManager().registerEvents(new ShopDisplay(), this);
        Bukkit.getPluginCommand("boost").setExecutor(new CommandGive());
        Bukkit.getPluginManager().registerEvents(new GameQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LobbyJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LobbyQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MapPreventions(), this);
        Bukkit.getPluginManager().registerEvents(new ConsumeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new GameHungerEvent(), this);
        Bukkit.getPluginManager().registerEvents(new GameKillEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPreventions(), this);
        Bukkit.getPluginManager().registerEvents(new GameBackpackUpgrade(), this);
        Bukkit.getPluginManager().registerEvents(new GameRespawnEvent(), this);
        Bukkit.getPluginManager().registerEvents(new GameBuildEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BackpackDisplay(), this);
        PacketEvents.get().init();
        PacketEvents.get().registerListener(listener);
        PacketEvents.get().getInjector().eject();
        PacketEvents.get().getInjector().inject();
        FileUtil.readPlayerData();
        FileUtil.readServerData();
        Bukkit.getConsoleSender().sendMessage("[MCI] Plugin §a§nENABLEDd§f.");

        //Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> FileUtils.saveServerData(Data.data.getServerData(Bukkit.getServer())), 20, 20);
    }

    @Override
    public void onDisable() {
        PacketEvents.get().terminate();
        Bukkit.getScheduler().cancelTasks(this);
        Bukkit.getConsoleSender().sendMessage("[MCI] Plugin §c§nDISABLED§f.");
    }
}
