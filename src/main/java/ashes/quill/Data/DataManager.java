package ashes.quill.Data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataManager {

    //Constructor for datamanager object
    public DataManager(){}

    //TODO Add compression to files
    public void writeJSON(File path, JSONObject json) {
        //make directories if they don't exist
        path.getParentFile().mkdirs();

        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(json.toString());
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Read the JSON object from the system
     * @param path the path of the JSON object
     * @return a JSON object from the file
     */
    public JSONObject readJSON(File path) {

        try {
            Scanner fileScanner = new Scanner(path);
            StringBuilder jsonString = new StringBuilder();
            while(fileScanner.hasNextLine()) jsonString.append(fileScanner.nextLine());
            return new JSONObject(jsonString.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Return null if an error occurred
        return null;
    }
}
