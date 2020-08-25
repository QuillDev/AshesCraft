package ashes.quill.Player;

import ashes.quill.Config.Constants;
import ashes.quill.NodeSystem.NodeManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    private static final List<AshesPlayer> playerList = new ArrayList<>();

    public PlayerManager(){}

    private final PlayerSaveLoad saveLoad = new PlayerSaveLoad();
    private final NodeManager nodeManager = Constants.nodeManager;

    /**
     * Get an ashes player from the array based on a player query
     * @param player to check hte array for
     * @return the matching ashes player
     */
    public AshesPlayer getPlayerFromList(Player player){

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
    public void registerPlayer(Player player, int level, int exp) {

        //Check if player is already registered
        for(AshesPlayer ashesPlayer : playerList) {
            if(ashesPlayer.getPlayer().equals(player)){
                return;
            }
        }
        playerList.add(new AshesPlayer(player, level, exp));
        System.out.println("Added player " + player.getDisplayName());
    }

    /**
     * Add player to the player list
     * @param player to register
     */
    public void registerPlayer(Player player) {

        //Check if player is already registered
        for(AshesPlayer ashesPlayer : playerList) {
            if(ashesPlayer.getPlayer().equals(player)){
                return;
            }
        }
        playerList.add(new AshesPlayer(player));

        //Set the player node
        System.out.println("Added player " + player.getDisplayName());
    }

    /**
     * Remove a player from the playerList
     * @param player to remove
     */
    public void removePlayer(Player player){
        playerList.remove(getPlayerFromList(player));
    }

    /**
     * Save each player in the player list
     */
    public void savePlayers(){
        for(AshesPlayer ashesPlayer : playerList){
            saveLoad.savePlayer(ashesPlayer);
        }
    }

    /**
     * Save a singular player
     */
    public void savePlayer(AshesPlayer player){
        saveLoad.savePlayer(player);
    }

    /**
     * Load a player by passing in the player object
     * @param player to retrieve data on
     */
    public void loadPlayer(Player player){
        saveLoad.loadPlayer(player);
    }
}
