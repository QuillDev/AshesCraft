/*
  @author Quill
 * @since 8.23.2020
 * @description Class that manages coords in a 2d plane (minecraft x, z)
 */
package ashes.quill.Utils;

import java.util.Objects;

public class Coordinates2d {

    private int x;
    private int z;

    /**
     * Creates new coordinates passing the game coordinates into them
     * @param x coordinate
     * @param z coordinate
     */
    public Coordinates2d(int x, int z){
        this.x = x;
        this.z = z;
    }

    /**
     * Get the X coordinate
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Z coordinate
     * @return the Z coordinate
     */
    public int getZ() {
        return z;
    }

    /**
     * Set the X coordinate to the x parameter
     * @param x the x to set it to
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the Z coordinate to the z parameter
     * @param z the z to set it to
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Check if two Coordinates2d have the same coordinates
     * @param o the object to compare to
     * @return whether they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates2d)) return false;
        Coordinates2d that = (Coordinates2d) o;
        return x == that.x &&
                z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }

    @Override
    public String toString() {
        return "x=" + x + ", z=" + z;
    }
}
