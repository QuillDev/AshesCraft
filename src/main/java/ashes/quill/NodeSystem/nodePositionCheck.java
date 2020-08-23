package ashes.quill.NodeSystem;

import ashes.quill.Player.AshesPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class nodePositionCheck implements Listener {

    List<AshesPlayer> playerList = new ArrayList<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent movement){
        Player player = movement.getPlayer();

        if(playerRegistered(player)){

        }
    }

    private boolean playerRegistered(Player player){

        //Iterate through all players in the player list
        for(AshesPlayer ashesPlayer : playerList){

            //check if the player has been registered
            if(ashesPlayer.getPlayer() == player){
                System.out.println("[Player Manager] Player " + player.getDisplayName() + " has already been registered.");
                return true;
            }
        }

        //Add the new player if they weren't in the player list.
        playerList.add(new AshesPlayer(player));
        return false;
    }
}
