package com.guedosha.simplefeed;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FeedCommand implements CommandExecutor {

    Plugin plugin = Simplefeed.getPlugin(Simplefeed.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (!p.hasPermission("simplefeed.feed")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to use that command");
                return true;
            }

            if (args.length == 0) {
                p.sendMessage(ChatColor.GOLD + "You have been fed");
                p.setSaturation(10);
                p.setFoodLevel(20);
            } else if (args.length == 1) {
                String playerName = args[0];
                Player t = Bukkit.getServer().getPlayerExact(playerName);
                if (t == null) {
                    p.sendMessage(ChatColor.RED + "That player isn't online");
                } else {
                    p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + t.getName() + ChatColor.GOLD + " has been fed");
                    t.sendMessage(ChatColor.GOLD + "You have been fed");
                    t.setSaturation(10);
                    t.setFoodLevel(20);
                }
            } else {
                p.sendMessage(ChatColor.RED + "Usage: /feed or /feed <target>");
            }
        } else {
            if (args.length == 0) {
                plugin.getLogger().info(ChatColor.RED + "Usage: /feed <target>");
            } else if (args.length == 1) {
                String playerName = args[0];
                Player t = Bukkit.getServer().getPlayerExact(playerName);

                if (t == null) {
                    plugin.getLogger().info(ChatColor.RED + "That player isn't online");
                } else {
                    t.setFoodLevel(20);
                    t.setSaturation(10);
                    t.sendMessage(ChatColor.GOLD + "You have been fed");
                    plugin.getLogger().info(ChatColor.YELLOW + "" + ChatColor.BOLD + t.getName() + ChatColor.GOLD + " Has been fed");
                }
            } else {
                plugin.getLogger().info(ChatColor.RED + "Usage: /feed <target>");
            }
        }
        return true;
    }
}
