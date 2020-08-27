package ashes.quill.NodeSystem;

import ashes.quill.Utils.Coordinates2d;

import java.util.Objects;

//TODO Add random name generation for the nodes

public class Node {

    private int experience;
    private int level;
    private final Coordinates2d coordinates;
    private String name;

    /**
     * Used for creation of new nodes
     * defaults lvl to 1 and exp to zero
     * @param coordinates the coordinates the node needs to be at
     */
    public Node(Coordinates2d coordinates){
        this.coordinates = coordinates;
        this.level = 0;
        this.experience = 0;
        this.name = name;
    }

    /**
     * Used when recreatingn file loaded nodes
     * @param coordinates coordinates for the node to be in
     * @param name name of the node
     * @param level level of the node
     * @param experience experience the node currently has
     */
    public Node(Coordinates2d coordinates, String name, int level , int experience){
        this.coordinates = coordinates;
        this.level = level;
        this.experience = experience;
        this.name = name;
    }

    /**
     * Get the coordinates as a string for printing purposes
     * @return a string of the coordinates
     */
    public String getCoordinateString(){
        return "X: " + coordinates.getX() + " Z: " + coordinates.getZ();
    }

    /**
     * Set the name of the node
     * @param name for the node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the node
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the nodes x
     * @return the nodes x
     */
    public int getX(){
        return coordinates.getX();
    }

    /**
     * Get the nodes Z
     * @return the nodes z
     */
    public int getZ(){
        return coordinates.getZ();
    }

    /**
     * Get the nodes exp
     * @return the nodes exp
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Get the nodes level
     * @return the nodes level
     */
    public int getLevel() {
        return level;
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
