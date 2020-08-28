package ashes.quill;

import ashes.quill.Leveling.ExperienceManager;
import ashes.quill.Lifeskills.LifeskillManager;
import ashes.quill.NodeSystem.NodeEvents;
import ashes.quill.NodeSystem.NodeManager;
import ashes.quill.Player.PlayerEvents;
import ashes.quill.Utils.Scheduler;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Quill extends JavaPlugin {

    //Use the event scheduler in Utils
    private Scheduler scheduler = Scheduler.getInstance();

    @Override
    public void onEnable() {

        //Get the server
        Server server = getServer();

        //Get the plugin manager
        PluginManager pluginManager = server.getPluginManager();

        //Register all of the plugins
        pluginManager.registerEvents(new PlayerEvents(), this);
        pluginManager.registerEvents(new NodeEvents(), this);
        pluginManager.registerEvents(new ExperienceManager(), this);
        pluginManager.registerEvents(new LifeskillManager(), this);

        //Schedule events for intermittent saving and loading
        scheduler.ScheduleEvents();

    }

    @Override
    public void onDisable() {

        //Stop save events from executing and shutdown the scheduler
        scheduler.shutdown();
    }
}
