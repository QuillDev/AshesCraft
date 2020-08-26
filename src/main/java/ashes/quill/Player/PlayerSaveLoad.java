package ashes.quill.Player;

import ashes.quill.Config.Constants;
import ashes.quill.Data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.UUID;
import java.util.logging.Level;

//TODO Make loading and saving files of different structures easier.... please... I need it.
public class PlayerSaveLoad {

    //Create instance of player manager
    PlayerManager playerManager = Constants.playerManager;

    //Create instance of data manager
    DataManager dataManager = new DataManager();


    /**
     * Save data about ashes players to a local file
     * @param ashesPlayer player object from an ashes player
     */
    public void savePlayer(AshesPlayer ashesPlayer) {
        System.out.println("Attempting to save player " + ashesPlayer.getUUID());

        //Create JSON Object for the player
        JSONObject playerObject = new JSONObject();
        playerObject.put("uuid", ashesPlayer.getUUID());
        playerObject.put("level", ashesPlayer.getLevel());
        playerObject.put("experience", ashesPlayer.getExp());
        //Log this
        System.out.println(ashesPlayer.getUUID().toString() +  " " + ashesPlayer.getLevel() + " " + ashesPlayer.getExp());

        File path = new File("./plugins/quill", ashesPlayer.getUUID().toString()+".json");

        dataManager.writeJSON(path, playerObject);

        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
    }

    /**
     * Load the player from their respective JSON file
     * @param player to load from file
     */
    public void loadPlayer(Player player){

        JSONObject playerObject = dataManager.readJSON(new File("./plugins/quill", player.getUniqueId().toString()+".json"));

        //Get the players stats from their save file
        int level = playerObject.getInt("level");
        int experience = playerObject.getInt("experience");

        //Register the player
        playerManager.registerPlayer(player, level, experience);

        //Log the players info
        saveLoadLog("Loaded player" + player.getDisplayName() + "{ level:" + level + ", experience:" + experience + " }");
    }

    /**
     * Logs for saving and loading player objects
     */
    private void saveLoadLog(String message){
        System.out.println("[Ashes] [PlayerLoader] " + message);
    }
}
