package com.github.tacowasa059.cellularautomata2d.commands;

import com.github.tacowasa059.cellularautomata2d.UpdateState;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.github.tacowasa059.cellularautomata2d.CellularAutomata2d.plugin;

public class initialize implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(plugin.run){
                player.sendMessage(ChatColor.RED+"描画中のため実行できません");
                return true;
            }
            Location location1 = plugin.getConfig().getLocation("position1");
            Location location2 = plugin.getConfig().getLocation("position2");
            //設定されてないときにNUllを返す
            if (location1 != null && location2 != null) {
                Location location_min = new Location(player.getWorld(), Math.min(location1.getX(), location2.getX()), 255.0, Math.min(location1.getZ(), location2.getZ()));
                int x = Math.abs((int) (location1.getX() - location2.getX()));
                int z = Math.abs((int) (location1.getZ() - location2.getZ()));
                plugin.updateState=new UpdateState(x,z,location_min);
                player.sendMessage(ChatColor.GREEN+"Initialized.");
            }
            else{
                player.sendMessage(ChatColor.RED+"先に位置を設定してください。");
            }
        }

        return true;
    }
}
