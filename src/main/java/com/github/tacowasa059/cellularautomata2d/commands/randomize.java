package com.github.tacowasa059.cellularautomata2d.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.github.tacowasa059.cellularautomata2d.CellularAutomata2d.plugin;

public class randomize implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(plugin.run){
                player.sendMessage(ChatColor.RED+"描画中のため実行できません");
                return true;
            }
            if(plugin.updateState!=null){
                plugin.updateState.randomize();
                player.sendMessage(ChatColor.GREEN+"Randomized.");
            }
            else{
                player.sendMessage(ChatColor.RED+"先に/initializeをしてください。");
            }
        }

        return true;
    }
}