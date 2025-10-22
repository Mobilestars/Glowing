package de.scholle.glowing;

import de.scholle.glowing.Glowing;
import de.scholle.glowing.utils.Msg;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GlowManager {

    private final Glowing plugin;
    private final long intervalTicks;
    private final int durationSeconds;
    private final boolean dieOnLogout;
    private boolean glowingEnabled;
    private final Map<UUID, BukkitRunnable> activeGlows = new HashMap<>();

    public GlowManager(Glowing plugin) {
        this.plugin = plugin;
        this.glowingEnabled = plugin.getConfig().getBoolean("glowing", true);
        this.intervalTicks = plugin.getConfig().getLong("glow-interval-minutes", 20) * 60 * 20;
        this.durationSeconds = plugin.getConfig().getInt("glow-duration-seconds", 60);
        this.dieOnLogout = plugin.getConfig().getBoolean("glow-die-on-logout", true);

        if (glowingEnabled) startGlowCycle();
    }

    private void startGlowCycle() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    startGlow(player);
                }
            }
        }.runTaskTimer(plugin, 0L, intervalTicks);
    }

    private void startGlow(Player player) {
        UUID id = player.getUniqueId();

        if (activeGlows.containsKey(id)) return;

        player.addPotionEffect(new PotionEffect(
                PotionEffectType.GLOWING,
                durationSeconds * 20,
                0,
                false,
                false,
                true
        ));

        Msg.send(plugin, player, "&eDu leuchtest nun für &f" + durationSeconds + " Sekunden!");

        BukkitRunnable task = new BukkitRunnable() {
            int timeLeft = durationSeconds;

            @Override
            public void run() {
                if (!player.isOnline()) {
                    removeGlow(player);
                    cancel();
                    return;
                }
                timeLeft--;
                if (timeLeft <= 0) {
                    removeGlow(player);
                    cancel();
                }
            }
        };

        task.runTaskTimer(plugin, 20L, 20L);
        activeGlows.put(id, task);
    }

    public void removeGlow(Player player) {
        UUID id = player.getUniqueId();
        player.removePotionEffect(PotionEffectType.GLOWING);
        Msg.send(plugin, player, "&7Dein &eGlow &7ist nun vorbei.");
        BukkitRunnable task = activeGlows.remove(id);
        if (task != null) task.cancel();
    }

    public void handleLogout(Player player) {
        UUID id = player.getUniqueId();
        if (activeGlows.containsKey(id)) {
            removeGlow(player);

            if (dieOnLogout) {
                dropInventory(player);
                player.getInventory().clear();
                player.setHealth(0.0);
                Msg.send(plugin, player, "&cDu bist während des Glows gestorben und hast alles verloren!");
            }
        }
    }

    private void dropInventory(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null) {
                player.getWorld().dropItemNaturally(player.getLocation(), item);
            }
        }
    }
}
