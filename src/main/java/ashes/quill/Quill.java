package ashes.quill;

import ashes.quill.NodeSystem.NodeEvents;
import ashes.quill.NodeSystem.NodeManager;
import ashes.quill.Player.PlayerEvents;
import ashes.quill.Utils.Scheduler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Quill extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginManager().registerEvents(new NodeEvents(), this);

        //Use the event scheduler in Utils
        Scheduler scheduler = new Scheduler();
        scheduler.ScheduleEvents();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
