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
            if (args.length == 0) {
                p.sendMessage(ChatColor.GOLD + "You have been fed");
                p.setFoodLevel(20);
            } else if (args.length == 1) {
                String playerName = args[0];
                Player target = Bukkit.getServer().getPlayerExact(playerName);
                if (target == null) {
                    p.sendMessage(ChatColor.GOLD + "That Player Isn't Online");
                } else {
                    p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has been fed");
                    target.sendMessage(ChatColor.GOLD + "You have been fed");
                    target.setFoodLevel(20);
                }
            } else {
                p.sendMessage(ChatColor.GOLD + "Usage: /feed ; /feed <target>");
            }
        } else {
            if (args.length == 0) {
                plugin.getLogger().info("Usage: /feed <target>");
            } else if (args.length == 1) {
                String playerNameConsole = args[0];
                Player t = Bukkit.getServer().getPlayerExact(playerNameConsole);

                if (t == null) {
                    plugin.getLogger().info("That Player is not Online");
                } else {
                    t.setFoodLevel(20);
                    t.sendMessage(ChatColor.GOLD + "You have been fed");
                    plugin.getLogger().info(t.getName() + " Has been fed");
                }
            } else {
                plugin.getLogger().info("Usage: /feed <target>");
            }
        }
        return true;
    }
}
