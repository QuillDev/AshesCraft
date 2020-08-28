package ashes.quill.Lifeskills;

import ashes.quill.Lifeskills.Fishing.Fishing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class LifeskillManager implements Listener {

    @EventHandler
    public void Fishing(PlayerFishEvent event){
        new Fishing().run(event);
    }
}
