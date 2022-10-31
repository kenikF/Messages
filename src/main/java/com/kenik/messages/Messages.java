package com.kenik.messages;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Messages extends JavaPlugin {

    @Override
    public void onEnable() {
        if(getConfig().getBoolean("config.works")) {
            // config
            saveDefaultConfig();

            // sheduler
            BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.broadcastMessage(getConfig().getString("messages.main"));
                }
            };
            int delay = getConfig().getInt("config.cooldown")*20;
            bukkitRunnable.runTaskLater(this, delay);

            // commands
            getCommand("messages").setExecutor(new CommandExecutor() {
                @Override
                public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                    if(args.length == 0) {
                        sender.sendMessage("To reload the config use: /messages reload");
                        return true;
                    }

                    if(args[0].equalsIgnoreCase("reload")) {
                        reloadConfig();
                        sender.sendMessage("You have successfully reloaded the config.");
                        return true;
                    } else {
                        sender.sendMessage("To reload the config use: /messages reload");
                        return true;
                    }
                }
            });

        } else {
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
    @Override
    public void onDisable() {
        Bukkit.getLogger().info("I'm turned off in the config and that's why I didn't turn on!");
    }
}
