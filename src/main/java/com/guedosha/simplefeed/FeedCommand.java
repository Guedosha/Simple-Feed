package com.guedosha.simplefeed;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FeedCommand implements CommandExecutor {

    Plugin plugin = Simplefeed.getPlugin(Simplefeed.class);
    ConsoleCommandSender logger = Bukkit.getConsoleSender();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Configuration config = new ReloadHandler().getConfig();
        if (sender instanceof Player p) {
            if (!p.hasPermission("simplefeed.use")) { p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.no-permission").replace("%caster%", p.getName()).replace("%target%", ""))); return true; }
            if (args.length == 0) {
                feed(p, false);
            } else if (args.length == 1) {
                String target = args[0];
                Player t = Bukkit.getPlayerExact(target);

                if (t==null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.player-offline").replace("%target%", target).replace("%caster%", p.getName())));
                } else {
                    feed(p, t);
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.usage").replace("%caster%", p.getName()).replace("%target%", p.getName())));
            }
        } else if (sender instanceof ConsoleCommandSender) {
            if (args.length == 1) {
                String target = args[0];
                Player t = Bukkit.getPlayerExact(target);

                if (t==null) logger.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.player-offline").replace("%target%", target).replace("%caster%", "Console")));
                else feed(t, true);
            } else logger.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.usage").replace("%caster%", "").replace("%target%", "Console")));
        }
        return true;
    }

    public void feed(Player caster, Player target) {
        Configuration config = new ReloadHandler().getConfig();
        String targetFed = ChatColor.translateAlternateColorCodes( '&',config.getString("messages.target-fed")).replace("%target%", target.getName()).replace("%caster%", caster.getName());
        String casterFed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-fed")).replace("%target%", target.getName()).replace("%caster%", caster.getName());

        target.setFoodLevel(20);
        target.sendMessage(targetFed);
        if (!caster.getName().equals(target.getName())) caster.sendMessage(casterFed);
    }

    public void feed(Player target, boolean consoleSender) {
        Configuration config = new ReloadHandler().getConfig();

        if (consoleSender) {
            String targetFed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.target-fed")).replace("%target%", target.getName()).replace("%caster%", "Console");
            String casterFed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-fed")).replace("%target%", target.getName()).replace("%caster%", "Console");

            target.setFoodLevel(20);
            target.sendMessage(targetFed);
            logger.sendMessage(casterFed);
        } else {
            String targetHealed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.target-fed")).replace("%target%", target.getName()).replace("%caster%", target.getName());

            target.setFoodLevel(20);
            target.sendMessage(targetHealed);
        }
    }
}
