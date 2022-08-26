package com.xiii.moocowio.core.utils;

import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class PingUtils {

    public static int getPing(Player p) throws ReflectiveOperationException {
        Object entityPlayer = p.getClass().getDeclaredMethod("getHandle").invoke(p);
        Field f = entityPlayer.getClass().getDeclaredField("ping");
        return f.getInt(entityPlayer);
    }

}
