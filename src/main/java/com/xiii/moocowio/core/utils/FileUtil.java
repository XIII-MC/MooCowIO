package com.xiii.moocowio.core.utils;

import com.xiii.moocowio.MooCowIO;
import com.xiii.moocowio.core.data.Data;
import com.xiii.moocowio.core.data.PlayerData;
import com.xiii.moocowio.core.data.ServerData;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

public class FileUtil {

    public static void saveServerData(ServerData data) {
        try {
            if (!MooCowIO.instance.getDataFolder().exists())
                MooCowIO.instance.getDataFolder().mkdir();
            File fileFolder = new File(MooCowIO.instance.getDataFolder() + "//server//");
            if (!fileFolder.exists()) fileFolder.mkdir();
            File file = new File(MooCowIO.instance.getDataFolder() + "//server//", "CONSOLE" + ".MCI");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(MooCowIO.instance.getDataFolder() + "//server//" + "CONSOLE" + ".MCI");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void readServerData() {
        File configFolder = new File(MooCowIO.instance.getDataFolder() + "//server//");

        if (configFolder.listFiles() == null || Objects.requireNonNull(configFolder.listFiles()).length < 1) {
            System.out.println("§eWARN: No ServerData found");
            return;
        } else {
            for (final File file : Objects.requireNonNull(configFolder.listFiles())) {
                try {
                    FileInputStream fileIn = new FileInputStream(file.getPath());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    Data.data.servers.add((ServerData) in.readObject());
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    i.printStackTrace();
                    return;
                } catch (ClassNotFoundException c) {
                    System.out.println("§eERROR: ServerData class not found");
                    c.printStackTrace();
                    return;
                }
            }
        }
    }

    public static void deletePlayerData(UUID uuid, Player p) {
        if(MooCowIO.instance.getDataFolder().exists()) {
            File fileFolder = new File(MooCowIO.instance.getDataFolder() + "//players//");
            if(fileFolder.exists()) {
                File file = new File(MooCowIO.instance.getDataFolder() + "//players//", uuid + ".MCI");
                if(file.exists()) {
                    file.delete();
                    Data.data.deletePlayerData(p);
                }
            }
        }
    }

    public static void savePlayerData(PlayerData data) {
        try {
            if (!MooCowIO.instance.getDataFolder().exists())
                MooCowIO.instance.getDataFolder().mkdir();
            File fileFolder = new File(MooCowIO.instance.getDataFolder() + "//players//");
            if (!fileFolder.exists()) fileFolder.mkdir();
            File file = new File(MooCowIO.instance.getDataFolder() + "//players//", data.getUuid() + ".MCI");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(MooCowIO.instance.getDataFolder() + "//players//" + data.getUuid() + ".MCI");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void readPlayerData() {
        File configFolder = new File(MooCowIO.instance.getDataFolder() + "//players//");

        if (configFolder.listFiles() == null || Objects.requireNonNull(configFolder.listFiles()).length < 1) {
            System.out.println("§eWARN: No PlayerData found");
        } else {
            for (final File file : Objects.requireNonNull(configFolder.listFiles())) {
                try {
                    FileInputStream fileIn = new FileInputStream(file.getPath());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    Data.data.users.add((PlayerData) in.readObject());
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    i.printStackTrace();
                    return;
                } catch (ClassNotFoundException c) {
                    System.out.println("§cERROR: PlayerData class not found");
                    c.printStackTrace();
                    return;
                }
            }
        }
    }
}
