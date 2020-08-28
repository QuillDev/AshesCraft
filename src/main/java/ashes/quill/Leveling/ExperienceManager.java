package ashes.quill.Leveling;

import ashes.quill.Leveling.Events.BreakBlock;
import ashes.quill.NodeSystem.Node;
import ashes.quill.NodeSystem.NodeManager;
import ashes.quill.Player.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ExperienceManager implements Listener {

    //Create node manager to be implemented in other classes
    private static ExperienceManager experienceManager = new ExperienceManager();
    private NodeManager nodeManager = NodeManager.getInstance();
    private PlayerManager playerManager = PlayerManager.getInstance();


    @EventHandler
    private void onBreakBlock(BlockBreakEvent event){
        new BreakBlock().run(event);
    }

    /**
     * Adds experience to a node
     * @param node to add experience to
     * @param experience to add to the node
     */
    public void addExp(Node node, int experience){
        nodeManager.getNode(node).addExperience(experience);
    }
}
