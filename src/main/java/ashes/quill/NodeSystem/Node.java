package ashes.quill.NodeSystem;

import ashes.quill.Utils.Coordinates2d;

import java.util.Objects;

//TODO Add random name generation for the nodes

public class Node {

    private int level = 1;
    private Coordinates2d coordinates;

    public Node(Coordinates2d coordinates){
        this.coordinates = coordinates;
    }

    /**
     * Get the coordinates as a string for printing purposes
     * @return a string of the coordinates
     */
    public String getCoordinateString(){
        return "X: " + coordinates.getX() + " Z: " + coordinates.getZ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(coordinates, node.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }
}
