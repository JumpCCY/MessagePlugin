package me.jumpccy.messageplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Invalid Usage! Use: /msg (player) (message)");
            } else {
                if (args[0].equals(player.getDisplayName())) {
                    player.sendMessage(ChatColor.RED + "You cannot send a message to this player");
                } else {
                    Player receiver = Bukkit.getPlayerExact(args[0]);
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                    if (receiver != null) {
                        receiver.sendMessage(args[1]);
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "To " + ChatColor.WHITE + player.getDisplayName() + ChatColor.GRAY + ": " + args[1]);
                        receiver.sendMessage(ChatColor.LIGHT_PURPLE + "From " + ChatColor.WHITE + player.getDisplayName() + ChatColor.GRAY + ": " + args[1]);
                    } else if (offlinePlayer.hasPlayedBefore()) {
                        player.sendMessage(ChatColor.RED + "That player is not online!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Can't find a player in the name of '" + args[0] + "'");
                    }
                }
            }
        }
        return true;
    }
}

