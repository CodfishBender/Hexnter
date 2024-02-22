package popopz.popopzlearn1;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import popopz.popopzlearn1.listeners.ItemTransfer;

public final class PopopzLearn1 extends JavaPlugin {


    public static PopopzLearn1 plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents((Listener) new ItemTransfer(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void reload() {
        // Plugin reload logic
    }

    public static PopopzLearn1 getInstance() {
        return PopopzLearn1.plugin;
    }
}
