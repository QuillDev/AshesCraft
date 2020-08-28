package ashes.quill.Utils;

import ashes.quill.Config.Constants;
import ashes.quill.NodeSystem.NodeManager;
import ashes.quill.Player.PlayerManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    //Get instance of the player and node manager
    private PlayerManager playerManager= PlayerManager.getInstance();
    private NodeManager nodeManager = NodeManager.getInstance();

    //Create the scheduler
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    //Create a new scheduler
    private static Scheduler scheduler = new Scheduler();

    //Create constructor for scheduler
    private Scheduler(){}

    //Get an instance of the scheduler
    public static Scheduler getInstance() { return scheduler; }

    /**
     * Schedule save events
     */
    public void ScheduleEvents(){
        //Create executors for saving data from the  node manager and player manager
        executorService.scheduleAtFixedRate(playerManager::savePlayers, 1, 5, TimeUnit.MINUTES);
        executorService.scheduleAtFixedRate(nodeManager::saveNodes, 1, 5, TimeUnit.MINUTES);
    }

    /**
     * Before disabling the plugin run one last save then shutdown the scheduler
     */
    public void shutdown(){

        //Save information about nodes and players
        executorService.schedule(playerManager::savePlayers, 0, TimeUnit.SECONDS);
        executorService.schedule(nodeManager::saveNodes, 0, TimeUnit.SECONDS);

        //Shutdown the scheduler
        executorService.shutdownNow();
    }



}
