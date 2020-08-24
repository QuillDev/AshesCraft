package ashes.quill.Player;

import ashes.quill.NodeSystem.Node;
import ashes.quill.NodeSystem.NodeManager;
import com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager implements Listener {

    List<AshesPlayer> playerList = new ArrayList<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent movement){
        //Save to and from locations
        Location from = movement.getFrom();
        Location to = movement.getTo();

        //if we didn't actually move at all, return before processing any data
        if(from.getX() == to.getX() && from.getZ() == to.getZ()) { return; }

        Player player = movement.getPlayer();
        registerPlayer(player);

        //Get the player from the list and store it as ashesPlayer
        AshesPlayer ashesPlayer = getPlayerFromList(player);

        //Save the player coordinates
        Node playerNode = NodeManager.getNodeFromChunk(player.getChunk());

        //If there is a difference in node coordinates
        assert ashesPlayer != null;
        if(!ashesPlayer.getNode().equals(playerNode)){

            //Tell the player they've entered a new node
            player.sendMessage("Entered node at coordinates " + playerNode.getCoordinateString());

            //set the players node coordinates to the current node they occupy
            ashesPlayer.setNode(playerNode);

            if(!NodeManager.nodeExists(playerNode)){
                NodeManager.addNode(playerNode);
            }
        }
    }

    /**
     * Get an ashes player from the array based on a player query
     * @param player to check hte array for
     * @return the matching ashes player
     */
    private AshesPlayer getPlayerFromList(Player player){

        //Iterate through all ashes players on
        for(AshesPlayer ashesPlayer : playerList) {

            //if the player is equal the the queried player
            if(ashesPlayer.getPlayer() == player){

                //return that ashes player
                return ashesPlayer;
            }
        }

        //Something went very wrong.
        return null;
    }

    /**
     * Add player to the player list
     * @param player to register
     */
    private void registerPlayer(Player player) {

        //Check if player is already registered
        for(AshesPlayer ashesPlayer : playerList) {
            if(ashesPlayer.getPlayer().equals(player)){
                return;
            }
        }
        playerList.add(new AshesPlayer(player));
        System.out.println("Added player " + player.getDisplayName());
    }

    //Triggers on player leaving the server
    @EventHandler
    private void onLeave(PlayerConnectionCloseEvent event){
        UUID playerUUID = event.getPlayerUniqueId();

        //Iterate through all players in player list
        for(AshesPlayer ashesPlayer : playerList){

            //If the player has a unique id equal to the one of the player who left
            if(ashesPlayer.getPlayer().getUniqueId() == playerUUID) {
                //remove that player from the player list
                playerList.remove(ashesPlayer);
                System.out.println("[Quill] [Player Manager] Removed " + event.getPlayerName() + " from active player list");
                break;
            }
        }
    }

    //Triggers on player joining
    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        //Register the player when they join
        Player player = event.getPlayer();

        //register the player
        registerPlayer(player);

        AshesPlayer ashesPlayer = getPlayerFromList(player);
        assert ashesPlayer != null;
        player.sendMessage(ChatColor.GREEN + "Welcome Lvl. " + ashesPlayer.getLevel() + " player " + player.getDisplayName() + "");
    }

    @EventHandler
    private void onBreakBlock(BlockBreakEvent event){
        int expgain = 5;

        //Get the player
        Player player = event.getPlayer();
        registerPlayer(player); //register the player

        //Get the ashes player
        AshesPlayer ashesPlayer = getPlayerFromList(player);

        //add the experience from breaking a block
        assert ashesPlayer != null;
        ashesPlayer.addExp(expgain);

        //log the experience gained.
        player.sendMessage(ChatColor.BLUE + "Gained " + expgain);
    }
}
