package de.scholle.glowing.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Msg {

    private static final String PREFIX = "§6[Glow] §r";

    public static void send(JavaPlugin plugin, Player player, String message) {
        player.sendMessage(PREFIX + message.replace("&", "§"));
    }
}
