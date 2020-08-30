package ashes.quill.Player;

import ashes.quill.Config.Constants;
import ashes.quill.Data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import java.io.File;
import java.util.logging.Level;

public class PlayerSaveLoad {

    //Create instance of data manager
    protected DataManager dataManager = new DataManager();

    /**
     * Save data about ashes players to a local file
     * @param ashesPlayer player object from an ashes player
     */
    public void savePlayer(AshesPlayer ashesPlayer) {
        saveLoadLog("Attempting to save player " + ashesPlayer.getUUID());

        //Create JSON Object for the player
        JSONObject playerObject = new JSONObject();
        playerObject.put("uuid", ashesPlayer.getUUID());
        playerObject.put("level", ashesPlayer.getLevel());
        playerObject.put("experience", ashesPlayer.getExp());

        File path = new File(Constants.playerPath, ashesPlayer.getUUID().toString()+".json");

        dataManager.writeJSONSafe(path, playerObject);
    }

    /**
     * Load the player from their respective JSON file
     * @param player to load from file
     */
    public void loadPlayer(Player player){
        PlayerManager playerManager = PlayerManager.getInstance();

        //Create file
        File path = new File(Constants.playerPath, player.getUniqueId().toString()+".json");
        if(!path.exists()){
            saveLoadLog("File for player " + player.getDisplayName() + " does not exist.");

            //Register the player
            playerManager.registerPlayer(player, 1, 0);
            return;
        }

        //Get json object from file
        JSONObject playerObject = dataManager.readJSON(path);

        //Get the players stats from their save file
        int level = playerObject.getInt("level");
        int experience = playerObject.getInt("experience");

        //Register the player
        playerManager.registerPlayer(player, level, experience);

        //Log the players info
        saveLoadLog("Loaded player " + player.getDisplayName() + "{ level:" + level + ", experience:" + experience + " }");
    }

    /**
     * Logs for saving and loading player objects
     */
    private void saveLoadLog(String message){
        System.out.println("[Ashes] [PlayerLoader] " + message);
    }
}
