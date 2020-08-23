package ashes.quill.Player;

import ashes.quill.NodeSystem.NodeUtils;
import ashes.quill.Utils.Coordinates2d;
import org.bukkit.entity.Player;

public class AshesPlayer {
    private Coordinates2d nodeCoordinates;
    private Player player;

    /**
     * Constructor for an "ashes player"
     * @param player object from retrieved method
     */
    public AshesPlayer(Player player){
        this.player = player;
        this.nodeCoordinates = NodeUtils.getNodeFromChunk(this.player.getChunk());
    }

    public Player getPlayer() {
        return player;
    }

    public Coordinates2d getNodeCoordinates() {
        return nodeCoordinates;
    }

    public void setNodeCoordinates(Coordinates2d nodeCoordinates) {
        this.nodeCoordinates = nodeCoordinates;
    }
}
