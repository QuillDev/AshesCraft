package ashes.quill.Utils;

import ashes.quill.Config.Constants;
import ashes.quill.NodeSystem.NodeManager;
import ashes.quill.Player.PlayerManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private PlayerManager playerManager= PlayerManager.getInstance();
    private NodeManager nodeManager = NodeManager.getInstance();

    public void ScheduleEvents(){


        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(playerManager::savePlayers, 1, 5, TimeUnit.MINUTES);
        executorService.scheduleAtFixedRate(nodeManager::saveNodes, 1, 5, TimeUnit.MINUTES);
    }
}
