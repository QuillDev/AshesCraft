package ashes.quill.Lifeskills.Fishing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FishingManager {

    //Sole instance of fishing manager
    private static final FishingManager fishingManager = new FishingManager();

    //List of registered fish
    private final List<Fish> freshwaterList = new ArrayList<>();
    private final List<Fish> saltwaterList = new ArrayList<>();

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
     * Generate freshwater fish
     * @return a Fish object using data from the freshwater fish list
     */
    public Fish generateFreshwaterFish(){
        //Create random num generator
        Random random = new Random();

        return freshwaterList.get(random.nextInt(freshwaterList.size())).generate();
    }


    //get an instance of the fishing manager
    public static FishingManager getInstance(){
        return fishingManager;
    }
}
