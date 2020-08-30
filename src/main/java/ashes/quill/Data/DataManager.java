package ashes.quill.Data;

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
    @Deprecated
    //Use WriteJSONSafe
    public void writeJSON(File path, JSONObject json) {
        //make directories if they don't exist
        path.getParentFile().mkdirs();

        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(json.toString());
            fileWriter.close();
        } catch (IOException e) {
            //Print the stack
            e.printStackTrace();
        }
    }

    public void writeJSONSafe(File path, JSONObject json){
        //make directories if they don't exist
        path.getParentFile().mkdirs();
        try {
            //Write to a lock file before writing data to the main file
            File lock = new File(path.getPath() + ".lock"); //create the lock file
            FileWriter lockWriter = new FileWriter(lock); //create the writer for the lock file
            lockWriter.write(json.toString()); //write to the lock file
            lockWriter.close(); //close the write stream for the lock file

            //check that the lock file exists and that the contents of the file exist
            if(lock.exists() && readJSON(lock).toString().length() != 0){
                //start writing to the main file
                FileWriter fileWriter = new FileWriter(path); //create a writer for the real file
                fileWriter.write(readJSON(lock).toString()); //write the contents of lock to the main file
                fileWriter.close(); // close the file writer
            }

        } catch (IOException e) {
            //Print the stack
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
            if(!path.exists()){dataLog("File does not exist \"" + path.getPath() + "\"");}
            Scanner fileScanner = new Scanner(path);
            StringBuilder jsonString = new StringBuilder();
            while(fileScanner.hasNextLine()) jsonString.append(fileScanner.nextLine());
            fileScanner.close();

            if(jsonString.length() == 0){

                //If the path doesn't exist, let us know :)
                if(!path.exists()){dataLog("File does not exist \"" + path.getPath() + "\"");}

                //Create a scanner for the lock file
                Scanner lockScanner = new Scanner(new File(path.getPath() + ".lock"));

                //set json string equal to a new string builder
                jsonString = new StringBuilder();

                //Get all data from the file
                while(lockScanner.hasNextLine()) jsonString.append(lockScanner.nextLine());

                //Close the scanner
                fileScanner.close();

                //Write the JSON data we got from the lock file into the new file
                writeJSONSafe(path, new JSONObject(jsonString.toString()));
            }

            return new JSONObject(jsonString.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Return null if an error occurred
        return null;
    }

    private void dataLog(String message){
        System.out.println("[Ashes] [Data Manager] " + message);
    }
}
