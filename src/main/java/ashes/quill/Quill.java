package ashes.quill;

import ashes.quill.NodeSystem.nodePositionCheck;
import org.bukkit.plugin.java.JavaPlugin;

public final class Quill extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new nodePositionCheck(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
