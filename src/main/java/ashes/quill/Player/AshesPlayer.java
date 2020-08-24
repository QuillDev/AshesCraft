package ashes.quill.Player;

import ashes.quill.NodeSystem.Node;
import ashes.quill.NodeSystem.NodeManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AshesPlayer {
    private Node node;
    private Player player;
    private int level = 1;
    private int exp = 0;

    /**
     * Constructor for an "ashes player"
     * @param player object from retrieved method
     */
    public AshesPlayer(Player player){
        this.player = player;
        this.node = NodeManager.getNodeFromChunk(this.player.getChunk());
    }

    /**
     * Get the player
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the node the player is in
     * @return the node the player is in
     */
    public Node getNode() {
        return node;
    }

    /**
     * Set the node the player is in
     * @param node the node to set the player's active node to
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /***
     * Get the players level
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Add Experience to the players exp
     * @param exp
     */
    public void addExp(int exp){
        this.exp += exp;
        calcLevel();
    }

    /**
     * Calculate if the player should level up
     */
    public void calcLevel(){
        if(exp > level * 20){
            level++;
            player.sendMessage(ChatColor.GREEN + "You've leveled up to level" + level + "!");
        }
    }
}
