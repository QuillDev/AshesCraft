package ashes.quill.NodeSystem;

import ashes.quill.Config.Constants;
import ashes.quill.Utils.Coordinates2d;
import org.bukkit.Chunk;

public class NodeUtils {
    /**
     * Create the Coordinates2d from the player
     * @param chunk the player is currently in
     * @return the coordinates of the node
     */
    public static Coordinates2d getNodeFromChunk(Chunk chunk) {
        int x = Math.floorDiv(chunk.getX(), Constants.nodeSize);
        int z = Math.floorDiv(chunk.getZ(), Constants.nodeSize);

        return new Coordinates2d(x, z);
    }
}
