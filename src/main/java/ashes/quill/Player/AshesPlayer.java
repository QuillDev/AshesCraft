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
    private int goalExp = 500;

    /**
     * Constructor for an "ashes player"
     * @param player object from retrieved method
     */
    public AshesPlayer(Player player, int level, int exp){
        this.player = player;
        this.level = level;
        this.exp = exp;
        this.node = NodeManager.getNodeFromChunk(this.player.getChunk());
    }

    /**
     * Create player with default values
     * @param player to set values to
     */
    public AshesPlayer(Player player){
        this.player = player;
        this.level = 1;
        this.exp = 0;
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
     * @param expFromAction experience to be gained from the action
     */
    public void addExp(int expFromAction){
        exp += expFromAction;
        player.sendMessage(ChatColor.BLUE + "Level: " + level + " " + exp + "/" + goalExp);
        calcLevel();
    }

    /**
     * Calculate if the player should level up
     */
    public void calcLevel(){
        if(exp > goalExp){
            //Increment level
            level++;

            //Log that the player leveled up
            player.sendMessage(ChatColor.GREEN + "You've leveled up to level" + level + "!");

            //reset the experience
            exp = 0;
        }
        //calculate goal exp.
        goalExp = (int) Math.pow(level, 2.2) + 500;
    }

    /**
     * Get Current experience
     * @return the experience
     */
    public int getExp() {
        return exp;
    }

    /**
     * Set player's level
     * @param level to set to
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Set players experience
     * @param exp experience to set to
     */
    public void setExp(int exp) {
        this.exp = exp;
    }
}
