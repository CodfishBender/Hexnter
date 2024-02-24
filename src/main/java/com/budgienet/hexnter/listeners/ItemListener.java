package com.budgienet.hexnter.listeners;

import com.budgienet.hexnter.utils.LogUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemListener implements Listener {
    @EventHandler
    public void invClickEvent(InventoryClickEvent event) {

        ItemStack item = event.getCurrentItem();
        Player player = (event.getWhoClicked() instanceof Player) ? (Player) event.getWhoClicked() : null;

        verifyItem(item, player);
    }
    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent event) {

        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        verifyItem(item, player);
    }
    @EventHandler
    public void playerCommandEvent(PlayerCommandPreprocessEvent event) {

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        Player player = event.getPlayer();

        verifyItem(item, player);
    }

    public void verifyItem(ItemStack item, Player player) {

        // Return if item is not applicable
        if (item == null) return;
        if (!item.hasItemMeta()) return;

        ItemMeta itemMeta = item.getItemMeta();

        // Return if no display name
        if (!itemMeta.hasDisplayName()) return;

        // Return if belongs to IA or EI
        NamespacedKey keyEI = NamespacedKey.fromString("executableitems:ei-id");
        NamespacedKey keyIA = NamespacedKey.fromString("itemattributes:itemid");

        if (keyEI != null && itemMeta.getPersistentDataContainer().has(keyEI)) return;
        if (keyIA != null && itemMeta.getPersistentDataContainer().has(keyIA)) return;

        // We will try and catch all null exceptions
        try {
            List<Component> nameColor = itemMeta.displayName().children();

            for (Component c : nameColor) {
                if (c == null) continue; // Try next color if it doesn't exist
                String color = String.valueOf(c.color()); // Get color
                if (!color.contains("#")) continue; // Try next color if not hex

                // Hex detected - return clean display name

                String name = buildCodedName(itemMeta.displayName()); // Get item name
                String pString = (player != null) ? " from player: " + player.getName() : ""; // Get player name
                LogUtil.log("HEX REMOVED: " + name + pString); // Log the crime

                itemMeta.displayName(null); // Remove custom name from meta data
                item.setItemMeta(itemMeta); // Apply the updated meta data to item
                return; // Break the loop, we found at least one instance of hex
            }
        } catch(NullPointerException ignored) {} // Ignore any null exceptions (e.g. no color found)
    }

    /**
     * Returns a string including color codes that may be present in the component.
     * @param name The component to break down.
     * @return The name with its color codes.
     */
    String buildCodedName(Component name) {
        if (name == null) return "";

        StringBuilder codedName = new StringBuilder();
        List<Component> children = name.children();

        for (Component c : children) {
            if (!(c instanceof TextComponent)) continue;

            codedName.append("<");
            codedName.append(c.color());
            codedName.append(">");
            codedName.append(((TextComponent) c).content());
        }

        return codedName.toString();
    }

}
