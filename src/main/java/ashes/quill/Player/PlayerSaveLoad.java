package ashes.quill.Player;

import ashes.quill.Config.Constants;
import ashes.quill.Data.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;
import java.util.logging.Level;

//TODO Split character data into different files based on their UUIDs, load data on player joining.
public class PlayerSaveLoad {

    /**
     * Save data about ashes players to a local file
     * @param ashesPlayer player object from an ashes player
     */
    public void savePlayer(AshesPlayer ashesPlayer) {
        UUID uuid = ashesPlayer.getPlayer().getUniqueId();
        Integer level = ashesPlayer.getLevel();
        Integer experience = ashesPlayer.getExp();

        File path = new File(Constants.playerDataPath);
        if(!path.exists()) { path.mkdirs(); }

        new Data(uuid, level, experience).saveData(Constants.playerDataPath + "/" + uuid+".dat");

        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
    }

    public void loadPlayer(Player player){
        PlayerManager playerManager = Constants.playerManager;
        String datPath = Constants.playerDataPath + player.getUniqueId() + ".dat";

        //if the file exists
        if(new File(datPath).exists()){
            Data data = new Data(Data.loadData(datPath));

            //Log information about the player
            System.out.println("Player: " + player.getDisplayName() + " Level:" + data.level + " Experience: " + data.experience);

            //register the player
            playerManager.registerPlayer(player, data.level, data.experience);
        }
        else {
            //if the players save data does not exist, make their player
            playerManager.registerPlayer(player);

            //save their players data after we create them
            savePlayer(playerManager.getPlayerFromList(player));
        }
    }
}
