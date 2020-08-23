package ashes.quill;

import ashes.quill.Player.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Quill extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerManager(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
