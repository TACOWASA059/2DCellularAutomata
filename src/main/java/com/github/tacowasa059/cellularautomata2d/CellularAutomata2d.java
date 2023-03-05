package com.github.tacowasa059.cellularautomata2d;

import com.github.tacowasa059.cellularautomata2d.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class CellularAutomata2d extends JavaPlugin {
    public static CellularAutomata2d plugin;
    public static Boolean run=false;
    public UpdateState updateState;
    @Override
    public void onEnable() {
        this.plugin=this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("position1").setExecutor(new position1());
        getCommand("position2").setExecutor(new position2());
        getCommand("randomize").setExecutor(new randomize());
        getCommand("initialize").setExecutor(new initialize());
        getCommand("TimeEvolution").setExecutor(new TimeEvolution());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
