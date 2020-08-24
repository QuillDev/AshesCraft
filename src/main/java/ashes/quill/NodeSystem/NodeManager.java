package ashes.quill.NodeSystem;

import ashes.quill.Config.Constants;
import ashes.quill.Utils.Coordinates2d;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class NodeManager implements Listener {

    private static List<Node> nodes = new ArrayList<Node>();

    /**
     * Add a node to the node list.
     * @param node the node to add
     */
    public static void addNode(Node node) {
        nodes.add(node);
        System.out.println("[Quill] [Node Manager] Added node at " + node.getCoordinateString());
    }

    /**
     * Check if the node entered exists
     * @param node the node to check the existence of
     * @return whether the node exists
     */
    public static boolean nodeExists(Node node){
        for(Node curNode : nodes){
            if(curNode.equals(node)){
                return true;
            }
        }
        return false;
    }

    /**
     * Create the Coordinates2d from the player
     * @param chunk the player is currently in
     * @return the coordinates of the node
     */
    public static Node getNodeFromChunk(Chunk chunk) {
        int x = Math.floorDiv(chunk.getX(), Constants.nodeSize);
        int z = Math.floorDiv(chunk.getZ(), Constants.nodeSize);

        return new Node(new Coordinates2d(x, z));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Node node = getNodeFromChunk(player.getChunk());

        //If the node they spawned in didn't exist, add it
        if(!nodeExists(node)){
            addNode(node);
        }
    }
}
