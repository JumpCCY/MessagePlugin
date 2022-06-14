package me.jumpccy.messageplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor{
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
                        if (args.length == 2) {
                            receiver.sendMessage(args[1]);
                            player.sendMessage(ChatColor.LIGHT_PURPLE + "To " + ChatColor.WHITE + receiver.getDisplayName() + ChatColor.GRAY + ": " + args[1]);
                            receiver.sendMessage(ChatColor.LIGHT_PURPLE + "From " + ChatColor.WHITE + player.getDisplayName() + ChatColor.GRAY + ": " + args[1]);
                        }
                        else {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                stringBuilder.append(args[i]);
                                stringBuilder.append(" ");
                            }

                            String finalMessage = stringBuilder.toString();

                            receiver.sendMessage(finalMessage);
                            player.sendMessage(ChatColor.LIGHT_PURPLE + "To " + ChatColor.WHITE + receiver.getDisplayName() + ChatColor.GRAY + ": " + finalMessage);
                            receiver.sendMessage(ChatColor.LIGHT_PURPLE + "From " + ChatColor.WHITE + player.getDisplayName() + ChatColor.GRAY + ": " + finalMessage);
                        }


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

