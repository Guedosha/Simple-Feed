package com.guedosha.simplefeed;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Simplefeed extends JavaPlugin {

    private static Simplefeed plugin;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[SimpleFeed] &fSuccessfully Loaded Config!"));

        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("reload").setExecutor(new ReloadHandler());
    }

    public static Simplefeed getPlugin() {
        return plugin;
    }

}
