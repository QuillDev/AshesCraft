package ashes.quill.Config;

import ashes.quill.Data.DataManager;
import ashes.quill.NodeSystem.NodeManager;
import ashes.quill.Player.PlayerManager;

public class Constants {
    //Default settings for world construction
    public static int nodeSize = 16;

    //Instantiate manager classes for global use
    public static PlayerManager playerManager = new PlayerManager();
    public static NodeManager nodeManager = new NodeManager();
}
