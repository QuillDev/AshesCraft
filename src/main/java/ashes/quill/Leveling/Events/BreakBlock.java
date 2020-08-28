package ashes.quill.Leveling.Events;

import ashes.quill.Leveling.ExperienceManager;
import ashes.quill.NodeSystem.NodeManager;
import ashes.quill.Player.AshesPlayer;
import ashes.quill.Player.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.yaml.snakeyaml.events.Event;

public class BreakBlock {

    //Get the player manager
    private PlayerManager playerManager = PlayerManager.getInstance();
    private NodeManager nodeManager = NodeManager.getInstance();
    private ExperienceManager experienceManager = new ExperienceManager();
    /**
     * Runs the run function when a break block event occurs.
     * @param event
     */
    public void run(BlockBreakEvent event){


        //Amount of experience to gain for the event
        int expgain = 50;

        //Get the player
        Player player = event.getPlayer();

        //Get the ashes player
        AshesPlayer ashesPlayer = playerManager.getPlayerFromList(player);
        if(ashesPlayer == null) { return; }

        //add the experience from breaking a block
        ashesPlayer.addExp(expgain);

        //add experience to the node
        experienceManager.addExp(ashesPlayer.getNode(), expgain);

        //log the experience gained.
        player.sendMessage(ChatColor.BLUE + "Gained " + expgain);
    }
}
