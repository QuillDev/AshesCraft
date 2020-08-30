package ashes.quill.Lifeskills.Fishing;

import org.bukkit.ChatColor;

public class Fish {
    private final String name;

    private final double avgWeight;
    private final double avgLength;

    private final int rarity;

    public Fish(String name, double avgWeight, double avgLength, int rarity){
        this.name = name;
        this.avgWeight = avgWeight;
        this.avgLength = avgLength;

        //Make sure rarity makes sense
        if(rarity > 4) { rarity = 4; }
        if(rarity < 0) { rarity = 0; }

        this.rarity = rarity;
    }

    /**
     * Return the rarity as a formatted string
     * @return a string of there rarity
     */
    public String rarityString(){
        String rarityText;
        switch(rarity){
            case 0: rarityText = ChatColor.GRAY + "Common"; break;
            case 1: rarityText = ChatColor.GREEN + "Uncommon"; break;
            case 2: rarityText = ChatColor.AQUA + "Rare"; break;
            case 3: rarityText = ChatColor.LIGHT_PURPLE + "Epic"; break;
            case 4: rarityText = ChatColor.GOLD + "Legendary"; break;
            default: return null;
        }

        return ChatColor.WHITE + "[" + rarityText + ChatColor.WHITE + "]";
    }
    public Fish generate(){
        //Get min and max weight
        double minWeight = avgWeight * .8;
        double maxWeight = avgWeight * 1.2;
        double weightRange = maxWeight - minWeight + 1;

        //Get min and max length
        double minLength = avgLength * .8;
        double maxLength = avgLength * 1.2;
        double lengthRange = maxLength - minLength + 1;

        //Generate the fish's length and weight
        double weight =  ( Math.random() * weightRange ) + minWeight;
        double length = ( Math.random() * lengthRange ) + minLength;

        return new Fish(name, weight, length, rarity);
    }

    public double getAvgWeight() {
        return Math.round(avgWeight*100.0)/100.0;
    }

    public double getAvgLength() {
        return Math.round(avgLength*100.0)/100.0;
    }

    public String getName() {
        return name;
    }



}
