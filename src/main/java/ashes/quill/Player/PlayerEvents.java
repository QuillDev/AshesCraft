package ashes.quill.Player;

import ashes.quill.Config.Constants;
import ashes.quill.NodeSystem.Node;
import ashes.quill.NodeSystem.NodeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

//TODO Make it so getNodeFromChunk isn't so fugly / doesn't rely on creatinng a node object.. its bad
//TODO NODES SHOULD NOT BE TOUCHED IN THE PLAYER MANGAGER!!! AHHH THE HUMANITY
public class PlayerEvents implements Listener {

    static PlayerManager playerManager = PlayerManager.getInstance();
    static NodeManager nodeManager = NodeManager.getInstance();

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
        if(ashesPlayer == null) { return; }

        //Save the player coordinates
        Node playerNode = nodeManager.getNodeFromChunk(player.getChunk());

        //If there is a difference in the nodes coordinates
        if(!ashesPlayer.getNode().equals(playerNode)){
            nodeManager.setPlayerNode(ashesPlayer, playerNode);
            player.sendMessage("You entered " + ashesPlayer.getNode().getName());
        }

    }


    //Triggers on player joining
    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        //Get the player from the event
        Player player = event.getPlayer();

        //Load the player
        //THIS IS WHERE THE ERROR HAPPENS
        playerManager.loadPlayer(player);

        //Run node managers on join events
        nodeManager.onJoin(event);
    }

    //Triggers on player leaving the server
    @EventHandler
    private void onLeave(PlayerQuitEvent event){
        //Get the player from the event
        Player player = event.getPlayer();

        //get the player from the player list
        AshesPlayer ashesPlayer = playerManager.getPlayerFromList(player);
        if(ashesPlayer == null) { return; }

        //Save the player who left
        playerManager.savePlayer(ashesPlayer);

        //remove the player from the active player list
        playerManager.removePlayer(event.getPlayer());

        //If all players are offline save the state of the nodes
        if(Bukkit.getOnlinePlayers().size() == 0){
            //save all nodes data
            nodeManager.saveNodes();
        }

    }


    @EventHandler
    private void onBreakBlock(BlockBreakEvent event){
        int expgain = 5;

        //Get the player
        Player player = event.getPlayer();

        //Get the ashes player
        AshesPlayer ashesPlayer = playerManager.getPlayerFromList(player);
        if(ashesPlayer == null) { return; }

        //add the experience from breaking a block
        ashesPlayer.addExp(expgain);

        //log the experience gained.
        player.sendMessage(ChatColor.BLUE + "Gained " + expgain);
    }
}
