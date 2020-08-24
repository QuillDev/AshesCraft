package ashes.quill.Data;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Data implements Serializable {
    private static transient final long serialVersionUID = -8571511353258712225L;

    public UUID uuid;
    public int level;
    public int experience;

    public Data(UUID uuid, int level, int experience){
        this.uuid = uuid;
        this.level = level;
        this.experience = experience;
    }

    public Data(Data loadedData){
        this.uuid = loadedData.uuid;
        this.level = loadedData.level;
        this.experience = loadedData.experience;
    }
    /**
     * Save Data
     */
    public boolean saveData(String filePath) {
        try {
            System.out.println("Attempting to save data.");
            //File output chain
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Load data from the file given
     * @param filePath the file to check for data
     * @return
     */
    public static Data loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream( new FileInputStream(filePath)));
            Data data = (Data) in.readObject();
            in.close();
            return data;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
