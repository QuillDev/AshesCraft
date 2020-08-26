package ashes.quill.NodeSystem;

import ashes.quill.Config.Constants;
import ashes.quill.Player.AshesPlayer;
import ashes.quill.Utils.Coordinates2d;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginLogger;

import java.util.ArrayList;
import java.util.List;

public class NodeManager implements Listener {

    private final List<Node> nodes = new ArrayList<>();
    private final NodeNameGenerator generator = new NodeNameGenerator();

    /**
     * Add a node to the node list.
     * @param node the node to add
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * Check if the node entered exists
     * @param node the node to check the existence of
     * @return whether the node exists
     */
    public boolean nodeExists(Node node){
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
    public Node getNodeFromChunk(Chunk chunk) {
        int x = Math.floorDiv(chunk.getX(), Constants.nodeSize);
        int z = Math.floorDiv(chunk.getZ(), Constants.nodeSize);

        return getNode(new Node(new Coordinates2d(x, z)));
    }

    /**
     * get the node from the list of it exists
     * @param node node to check the existence of
     * @return the node from the list
     */
    public Node getNode(Node node){

        //iterate through all nodes in the list
        for(Node curNode : nodes){
            //if the node is equal
            if(curNode.equals(node)){
                //return the node
                return curNode;
            }
        }
        //Create the node
        createNode(node);

        //return the node after getting it again
        return getNode(node);
    }
    /**
     * Add the node to the manager after adding properties for it
     * @param node to add to the manager
     */
    public void createNode(Node node){

        //check if the node already exists
        if(nodeExists(node)) { nodeLog("Tried to create an existing node.."); return; }

        //set the name of the node to something
        node.setName(generator.generate());

        //add the node to the nodelist
        addNode(node);

        //Print that we added a new node
        nodeLog("New node " + node.getName() + " at " + node.getCoordinateString());
    }

    public void setPlayerNode(AshesPlayer ashesPlayer, Node node){
        if(!nodeExists(node)){
            createNode(node);
        }

        //the player's new node
        Node playerNode = getNode(node);

        //set their node equal to the one from the list with coordinates equal to theirs
        ashesPlayer.setNode(playerNode);

        //Log that we changed the players node
        nodeLog("Set " + ashesPlayer.getDisplayName() + "'s node to " + node.getName()+ " {" + node.getCoordinateString() + "}");
    }

    /**
     * Log messages from the node manager
     * @param message to send with nodelog prefix
     */
    private void nodeLog(String message){
        System.out.println("[Ashes] [Node Manager] " + message);
    }
}
