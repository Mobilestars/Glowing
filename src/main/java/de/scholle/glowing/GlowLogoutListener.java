package de.scholle.glowing;

import de.scholle.glowing.Glowing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GlowLogoutListener implements Listener {

    private final Glowing plugin;

    public GlowLogoutListener(Glowing plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.glow().handleLogout(event.getPlayer());
    }
}