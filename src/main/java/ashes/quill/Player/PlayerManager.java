package ashes.quill.Player;

import ashes.quill.NodeSystem.Node;
import ashes.quill.NodeSystem.NodeManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

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

        //Check if the player is in the AshesPlayer list
        if(playerRegistered(player)){

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
    }

    /**
     * Check whether the current player is registered in the player list
     * @param player the player to check
     * @return whether the player has been registered
     */
    private boolean playerRegistered(Player player){

        //Iterate through all players in the player list
        for(AshesPlayer ashesPlayer : playerList){

            //check if the player has been registered
            if(ashesPlayer.getPlayer() == player){
                return true;
            }
        }

        //Add the new player if they weren't in the player list.
        playerList.add(new AshesPlayer(player));
        return false;
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
}
