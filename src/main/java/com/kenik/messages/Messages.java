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
            Bukkit.getLogger().info("Я включился! Плагин был разработан kenik#6291");

            // config
            saveDefaultConfig();

            // sheduler
            BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.broadcastMessage(getConfig().getString("messages.main"));
                }
            };
            bukkitRunnable.runTaskLater(this, 3600);

            // commands
            getCommand("messages").setExecutor(new CommandExecutor() {
                @Override
                public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                    if(args.length == 0) {
                        sender.sendMessage("Для перезагрузки конфига используйте: /messages reload");
                        return true;
                    }

                    if(args[0].equalsIgnoreCase("reload")) {
                        reloadConfig();
                        sender.sendMessage("Вы успешно перезагрузили конфиг.");
                        return true;
                    } else {
                        sender.sendMessage("Для перезагрузки конфига используйте: /messages reload");
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
        Bukkit.getLogger().info("Я выключен в конфиге и поэтому я не включился! Плагин был разработан kenik#6291");
    }
}
