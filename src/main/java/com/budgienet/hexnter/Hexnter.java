package com.budgienet.hexnter;

import com.budgienet.hexnter.listeners.ItemListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hexnter extends JavaPlugin {

    public static Hexnter plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new ItemListener(), this);
    }

    public static Hexnter getInstance() {
        return Hexnter.plugin;
    }
}
