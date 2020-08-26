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

        //If the player is already registered, please don't
        if(playerExists(player)) { return; }

        //add the player to the list using their info and exp
        playerList.add(new AshesPlayer(player, level, exp));
        managerLog("Added player " + player.getDisplayName());
    }

    public boolean playerExists(Player player) {
        for(AshesPlayer ashesPlayer : playerList) {
            if(ashesPlayer.equals(player)){return true; }
        }

        return false;
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

    /**
     * Log to the console
     * @param message
     */
    private void managerLog(String message) {
        System.out.println("[Ashes] [Player Manger]");
    }
}
