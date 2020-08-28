package ashes.quill.Lifeskills.Fishing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FishingManager {

    //Sole instance of fishing manager
    private static FishingManager fishingManager = new FishingManager();

    //List of registered fish
    private List<Fish> freshwaterList = new ArrayList<Fish>();
    private List<Fish> saltwaterList = new ArrayList<Fish>();

    //Private fishing manager constructor
    private FishingManager(){

        //Add freshwater fish
        freshwaterList.add(new Fish("Bass", 12, 18, 0));
        freshwaterList.add(new Fish("Catfish", 18, 34, 2));
        freshwaterList.add(new Fish("Herring", 4.1, 11.5, 0));
        freshwaterList.add(new Fish("Rainbow Trout", 8, 16, 1));
        freshwaterList.add(new Fish("Speckled Trout", 3, 20, 0));
        freshwaterList.add(new Fish("Crappie", .5, 9, 0));
    }

    /**
     * Generate
     * @return
     */
    public Fish generateFreshwaterFish(){
        //Create random num generator
        Random random = new Random();

        return freshwaterList.get(random.nextInt(freshwaterList.size())).generate();
    }



    public Fish generateFish(){
        return null;
    }

    //get an instance of the fishing manager
    public static FishingManager getInstance(){
        return fishingManager;
    }
}
