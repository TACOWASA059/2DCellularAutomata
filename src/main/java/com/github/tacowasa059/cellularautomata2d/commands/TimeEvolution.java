package com.github.tacowasa059.cellularautomata2d.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import static com.github.tacowasa059.cellularautomata2d.CellularAutomata2d.plugin;
public class TimeEvolution implements CommandExecutor {
    private BukkitTask task;//繰り返し処理タスク
    private int step=0;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(plugin.run){
                player.sendMessage(ChatColor.RED+"描画中のため実行できません");
                return true;
            }
            if(plugin.updateState!=null){
                if (args.length != 2) {
                    player.sendMessage(ChatColor.RED + "引数が不適切です。");
                    player.sendMessage(ChatColor.AQUA+"/<command> <neighbor_rule> <rule_number>");
                } else {
                    int neighbor_rule = Integer.parseInt(args[0]);
                    int rule_num=Integer.parseInt(args[1]);
                    plugin.updateState.setRule(neighbor_rule,rule_num);
                    Integer[] max_rule_num={1024,262144,64,1024};
                    if(neighbor_rule>=0&&neighbor_rule<=3){
                        if(rule_num>=max_rule_num[neighbor_rule]||rule_num<0) {
                            player.sendMessage(ChatColor.RED + "その更新規則でのルール番号は" + max_rule_num[neighbor_rule] + "未満にしてください");
                            return true;
                        }
                        step=0;
                        plugin.run=true;
                        task=plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
                            plugin.updateState.update();
                            if(step==255-2){
                                step=0;
                                task.cancel();
                                plugin.updateState.resetLoc();
                                player.sendMessage(ChatColor.GREEN + "Success!");
                                plugin.run=false;

                            }
                            step++;
                        }, 0L, 2L);

                    }
                    else{
                        player.sendMessage(ChatColor.RED+"neighbor_ruleの値は0から3にしてください。");
                    }
                }
            }
            else{
                player.sendMessage(ChatColor.RED+"まず初期化してください。/initialize");
            }

        }
        return true;
    }
}
