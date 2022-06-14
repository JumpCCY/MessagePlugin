package me.jumpccy.messageplugin;

import me.jumpccy.messageplugin.Commands.Message;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessagePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[MessagePlugin] " + ChatColor.GREEN + "ENABLED");
        getCommand("msg").setExecutor(new Message());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[MessagePlugin] " + ChatColor.RED + "DISABLED");
    }
}
