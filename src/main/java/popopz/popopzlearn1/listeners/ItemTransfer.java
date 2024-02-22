package popopz.popopzlearn1.listeners;

import io.papermc.paper.event.player.PlayerPickItemEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import popopz.popopzlearn1.utils.LogUtil;

import java.util.List;

public class ItemTransfer implements Listener {
    @EventHandler
    public void itemMoveEvent(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (!event.getCurrentItem().hasItemMeta()) return;
        ItemMeta itemMeta = verifyItem(event.getCurrentItem().getItemMeta());
        if (itemMeta != null){
            event.getCurrentItem().setItemMeta(itemMeta);
        }
    }
    @EventHandler
    public void playerInteract(PlayerInteractEvent event){
        ItemStack mainHand = event.getItem();
        if (mainHand == null) return;
        if (!mainHand.hasItemMeta()) return;
        ItemMeta itemMeta = verifyItem(mainHand.getItemMeta());
        if (itemMeta != null){
            mainHand.setItemMeta(itemMeta);
        }
    }

    @EventHandler
    public void playerCommandEvent(PlayerCommandPreprocessEvent event){
        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
        if (!mainHand.hasItemMeta()) return;
        ItemMeta itemMeta = verifyItem(mainHand.getItemMeta());
        if (itemMeta != null){
            mainHand.setItemMeta(itemMeta);
        }
    }

    public ItemMeta verifyItem(ItemMeta itemMeta){
        //ItemMeta item = event.getCurrentItem().getItemMeta();

        NamespacedKey keyEI = NamespacedKey.fromString("executableitems:ei-id");
        NamespacedKey keyIA = NamespacedKey.fromString("itemattributes:itemid");
        if (keyEI == null || keyIA == null) return null;
        if (itemMeta.getPersistentDataContainer().has(keyEI) || itemMeta.getPersistentDataContainer().has(keyIA)){
            LogUtil.log("Detected EI/IA");
            return null;
        }

        List <Component> nameColor = itemMeta.displayName().children();

        //TextColor nameColor = event.getItem().getItemStack().displayName().color();
        for (Component c:nameColor){
            if (c == null) continue;
            String color = String.valueOf(c.color());
            LogUtil.log(color);
            if (color.contains("#")){

                LogUtil.log("<red>Hex Color Detected</red>");
                itemMeta.displayName(null);
                return itemMeta;
                //event.getCurrentItem().setItemMeta(item);
            }

        }
        return null;
    }
}
