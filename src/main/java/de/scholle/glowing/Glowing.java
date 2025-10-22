package de.scholle.glowing;

import org.bukkit.plugin.java.JavaPlugin;
import de.scholle.glowing.GlowManager;
import de.scholle.glowing.GlowLogoutListener;

public class Glowing extends JavaPlugin {

    private GlowManager glowManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        glowManager = new GlowManager(this);
        getServer().getPluginManager().registerEvents(new GlowLogoutListener(this), this);
        getLogger().info("Glowing plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Glowing plugin disabled!");
    }

    public GlowManager glow() {
        return glowManager;
    }
}
