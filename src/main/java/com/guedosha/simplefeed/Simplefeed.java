package com.guedosha.simplefeed;

import org.bukkit.plugin.java.JavaPlugin;

public final class Simplefeed extends JavaPlugin {

    private static Simplefeed plugin;

    @Override
    public void onEnable() {
        getCommand("feed").setExecutor(new FeedCommand());
    }

    public static Simplefeed getPlugin() {
        return plugin;
    }

}
