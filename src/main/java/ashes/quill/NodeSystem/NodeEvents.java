package ashes.quill.NodeSystem;

import ashes.quill.Config.Constants;
import ashes.quill.Player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NodeEvents implements Listener {

    //Create an instance of the nodemanager
    NodeManager nodeManager = Constants.nodeManager;
    PlayerManager playerManager = Constants.playerManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        Node nodeToSet = nodeManager.getNodeFromChunk(player.getChunk());

        System.out.println("should be setting " + player.getName() + "'s node to " + nodeToSet.getCoordinateString() + " named " + nodeToSet.getName());

        //set the node of the player
        nodeManager.setPlayerNode(playerManager.getPlayerFromList(player), nodeManager.getNodeFromChunk(player.getChunk()));
    }
}
