package ashes.quill.NodeSystem;

import ashes.quill.Config.Constants;
import ashes.quill.Data.DataManager;
import ashes.quill.Player.AshesPlayer;
import ashes.quill.Player.PlayerManager;
import ashes.quill.Utils.Coordinates2d;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import java.io.File;

public class NodeSaveLoad {

    //Create instance of data manager
    protected DataManager dataManager = new DataManager();

    /**
     * Save data about ashes players to a local file
     * @param node the node to save
     */
    public void saveNode(Node node) {
        saveLoadLog("Attempting to save node " + node.getName() + " at " + node.getCoordinateString());

        //Create JSON Object for the player
        JSONObject nodeObject = new JSONObject();
        nodeObject.put("x", node.getX());
        nodeObject.put("z", node.getZ());
        nodeObject.put("name", node.getName());
        nodeObject.put("level", node.getLevel());
        nodeObject.put("experience", node.getExperience());

        File path = new File(Constants.nodePath, node.getX() + ", " + node.getZ() + ".json");

        dataManager.writeJSON(path, nodeObject);
    }

    /**
     * Load the player from their respective JSON file
     * @param node to load from file
     */
    public void loadNode(Node node){
        NodeManager nodeManager = NodeManager.getInstance();

        //Create file
        File path = new File(Constants.nodePath, node.getX() + ", " + node.getZ() + ".json");

        //check if the file exists
        if(!path.exists()){

            //If the file doesn't exist, say that it doesn't
            saveLoadLog("File for node " + node.getCoordinateString() + " does not exist.");

            //Register the player
            nodeManager.createNode(node);

            return;
        }

        //Get json object from file
        JSONObject nodeObject = dataManager.readJSON(path);

        //Get the node information from its save file
        String name = nodeObject.getString("name");
        int x = nodeObject.getInt("x");
        int z = nodeObject.getInt("z");
        int level = nodeObject.getInt("level");
        int experience = nodeObject.getInt("experience");

        //Register the node
        nodeManager.addNode(new Node(new Coordinates2d(x, z), name, level, experience));

        //Log the players info
        saveLoadLog("Loaded node " + name + "{ level:" + level + ", experience:" + experience + " } at " + node.getCoordinateString());
    }

    private void saveLoadLog(String message){
        System.out.println("[Ashes] [NodeLoader] " + message);
    }
}
