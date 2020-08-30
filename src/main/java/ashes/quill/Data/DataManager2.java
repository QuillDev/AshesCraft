package ashes.quill.Data;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataManager2 {

    /**
     * Save data to a json file using the pathname+filename specified
     * @param pathString the path to the file
     * @param fileName the name of the file
     * @param obj the data to save
     */
    public void save(String pathString, String fileName, JSONObject obj){

        //Check the save path
        Path savePath = Paths.get(pathString);

        //If the directories don't exist
        try {
            //If the path does not exist, make it
            if(!Files.exists(savePath)){
                //try to make the directories
                Files.createDirectories(savePath);
            }

            //Create path strings
            String filePathString = pathString + fileName;
            String lockPathString = filePathString + ".lock";

            //Create paths
            Path filePath = Paths.get(filePathString);
            Path lockPath = Paths.get(lockPathString);

            //Create the files if they don't exist
            if(!Files.exists(filePath)) { Files.createFile(filePath); }
            if(!Files.exists(lockPath)) { Files.createFile(lockPath); }

            //Try to save the data
            List<String> dataToWrite = new ArrayList<>(Collections.singletonList(obj.toString()));

            //write data to the lock file
            Files.write(lockPath, dataToWrite);

            //check integrity of data that was written to the lock file


        } catch (IOException e) {
                e.printStackTrace();
        }

    }

    public void load(String filePathString){
        try {
            //create a path to find the file
            Path filePath = Paths.get(filePathString);
            //check if the file exists
            if (!Files.exists(filePath)) {
                log("File " + filePath.getFileName() + " does not exist.");
                return;
            }

            //Create path var for lock file
            Path lockPath = Paths.get(filePathString + ".lock");

            //Check if the lock file exists, if it does there was an error saving the data to the main file
            if (Files.exists(lockPath)) {
                //Log that an error was detected
                log("Detected error in file " + filePath.getFileName() + " attempting repair.");

                //Read all info from the lock file
                List<String> lockContent = Files.readAllLines(lockPath);

                //if the lock file is empty
                if(!lockContent.isEmpty()){
                    //Write the content from the lock file to the main file
                    Files.write(filePath, lockContent);
                } else { log("Data damaged beyond repair. try loading backup data to repair."); return;}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String message){
        System.out.println("[Ashes] [DataManager] " + message);
    }
}