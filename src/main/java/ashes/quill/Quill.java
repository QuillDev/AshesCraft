package ashes.quill;

import ashes.quill.Player.PlayerActions;
import ashes.quill.Utils.Scheduler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Quill extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerActions(), this);

        //Use the event scheduler in Utils
        Scheduler scheduler = new Scheduler();
        scheduler.ScheduleEvents();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
