package ashes.quill.Utils;

import ashes.quill.Config.Constants;
import ashes.quill.Player.PlayerManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private PlayerManager playerManager= Constants.playerManager;

    public void ScheduleEvents(){


        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(playerManager::savePlayers, 0, 5, TimeUnit.MINUTES);
    }
}
