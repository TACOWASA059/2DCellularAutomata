package com.github.tacowasa059.cellularautomata2d.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.github.tacowasa059.cellularautomata2d.CellularAutomata2d.plugin;

public class position2 implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player =(Player)sender;
            Location location=player.getLocation();
            plugin.getConfig().set("position2",location);
            plugin.saveConfig();
            player.sendMessage(ChatColor.GREEN + "position2 was set!");
        }
        else{
            System.out.println("Run by player.");
        }
        return true;
    }
}
