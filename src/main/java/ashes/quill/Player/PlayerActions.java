package ashes.quill.Player;

import ashes.quill.Config.Constants;
import ashes.quill.NodeSystem.Node;
import ashes.quill.NodeSystem.NodeManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerActions implements Listener {

    PlayerManager playerManager = Constants.playerManager;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent movement){

        //Save to and from locations
        Location from = movement.getFrom();
        Location to = movement.getTo();

        //if we didn't actually move at all, return before processing any data
        if(from.getX() == to.getX() && from.getZ() == to.getZ()) { return; }

        Player player = movement.getPlayer();

        //Get the player from the list and store it as ashesPlayer
        AshesPlayer ashesPlayer = playerManager.getPlayerFromList(player);

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

    //TODO Trigger save on player exit
    //Triggers on player leaving the server
    @EventHandler
    private void onLeave(PlayerQuitEvent event){
        //Get player
        Player player = event.getPlayer();

        //Get the ashes player
        AshesPlayer ashesPlayer = playerManager.getPlayerFromList(player);

        //save the ashes player
        playerManager.savePlayer(ashesPlayer);

        //remove the player from the player list
        playerManager.removePlayer(player);
    }

    //Triggers on player joining
    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        PlayerManager playerManager = Constants.playerManager;
        //Register the player when they join
        Player player = event.getPlayer();

        playerManager.loadPlayer(player);

        AshesPlayer ashesPlayer = playerManager.getPlayerFromList(player);
        assert ashesPlayer != null;
        player.sendMessage(ChatColor.GREEN + "Welcome Lvl. " + ashesPlayer.getLevel() + " player " + player.getDisplayName() + "");
    }

    @EventHandler
    private void onBreakBlock(BlockBreakEvent event){
        int expgain = 5;

        //Get the player
        Player player = event.getPlayer();

        //Get the ashes player
        AshesPlayer ashesPlayer = playerManager.getPlayerFromList(player);

        //add the experience from breaking a block
        assert ashesPlayer != null;
        ashesPlayer.addExp(expgain);

        //log the experience gained.
        player.sendMessage(ChatColor.BLUE + "Gained " + expgain);
    }
}
