package ashes.quill.Lifeskills.Fishing;

import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fishing {

    //get an instance of the fishing manager
    private final FishingManager fishingManager = FishingManager.getInstance();

    /**
     * Runs when a fishing event is executed
     * @param event PlayerFishEvent from a player fishing
     */
    public void run(PlayerFishEvent event){
        //if the player didn't catch a fish, return
        if(event.getState() != PlayerFishEvent.State.CAUGHT_FISH){return;}

        //Change info of the fish
        Item fish = (Item) event.getCaught();
        if(fish == null){ return;}
        ItemStack fishStack = fish.getItemStack();

        //Generate a fish
        Fish coolFish = fishingManager.generateFreshwaterFish();

        ItemMeta fishMeta = fishStack.getItemMeta();
        //set the name of the fish
        fishMeta.setDisplayName(coolFish.rarityString() + " " + ChatColor.BOLD + ChatColor.ITALIC + coolFish.getName());

        //Get date for now
        Date now = new Date();
        LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //Mess with the fish
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Weight: " + coolFish.getAvgWeight() + "lbs");
        lore.add(ChatColor.WHITE + "Length: " + coolFish.getAvgLength() + "in");
        lore.add(ChatColor.WHITE + "Caught on " + localDate.getMonth() + " " + localDate.getDayOfMonth() + " " + localDate.getYear());

        //set lore in metadata
        fishMeta.setLore(lore);

        //Set the lore of the item
        fishStack.setItemMeta(fishMeta);

    }
}
