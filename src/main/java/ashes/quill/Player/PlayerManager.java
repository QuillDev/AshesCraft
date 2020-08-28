package ashes.quill.Player;

import ashes.quill.Config.Constants;
import ashes.quill.NodeSystem.NodeManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    //make the PlayerManager so we can get it's instance in other files
    private static PlayerManager playerManager = new PlayerManager();

    //Instantiate private vars the player manager will use
    private static final List<AshesPlayer> playerList = new ArrayList<>();

    //Instantiate an instance of the node manager
    private final NodeManager nodeManager = NodeManager.getInstance();

    //Create an instance of the player save loader
    private final PlayerSaveLoad saveLoad = new PlayerSaveLoad();

    private final List<UUID> gmList = new ArrayList<UUID>();

    private PlayerManager(){
        gmList.add(UUID.fromString("959e2a47-f4ee-4492-9a4f-387a2d9340c3"));
        gmList.add(UUID.fromString("5007392d-a76f-4080-98b0-0fa1a416c1ae"));
    }

    /**
     * Get an ashes player from the array based on a player query
     * @param player to check hte array for
     * @return the matching ashes player
     */
    public AshesPlayer getPlayerFromList(Player player){

        //Iterate through all ashes players on
        for(AshesPlayer ashesPlayer : playerList) {

            //if the player is equal the the queried player
            if(ashesPlayer.getPlayer().equals(player)){

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

        //Create an ashes player using the given info
        AshesPlayer ashesPlayer = new AshesPlayer(player, level, exp);

        //add the player to the list using their info and exp
        playerList.add(ashesPlayer);

        //Log that we registered the player
        managerLog("Added " + ashesPlayer.getDisplayName() + " Level: " + ashesPlayer.getLevel());
    }

    /**
     * Check if player exists
     * @param player to check
     * @return whether the player exists
     */
    public boolean playerExists(Player player) {
        for(AshesPlayer ashesPlayer : playerList) {
            if(ashesPlayer.getUUID() == player.getUniqueId()){ System.out.println("Player Exists"); return true; }
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
     * Get the player managers instance
     * @return the player manager
     */
    public static PlayerManager getInstance(){
        return playerManager;
    }
    /**
     * Load a player by passing in the player object
     * @param player to retrieve data on
     */
    public void loadPlayer(Player player){
        saveLoad.loadPlayer(player);
    }

    public boolean isGM(AshesPlayer player){

        //Whether the player is a GM
        boolean isGM = false;

        //iterate through GM UUIDs
        for(UUID uuid : gmList){
            //If the UUID is equal to a players UUID
            if(player.getUUID().equals(uuid)){

                //set isgm to true
                isGM = true;
                break;
            }
        }

        //return whether they're a GM or not
        return isGM;
    }

    /**
     * Log to the con
     * @param message
     */
    private void managerLog(String message) {
        System.out.println("[Ashes] [Player Manger]" + message);
    }
}
