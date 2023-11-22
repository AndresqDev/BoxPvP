package net.kappa.boxpvp.commands.admin;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "No console.");
            return false;
        } else if (!sender.hasPermission("hub.gamemode")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return false;
        } else if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Insufficient arguments.");
            return false;
        }

        final Player player = (Player) sender;
        final String mode = args[0];
        if (mode.equalsIgnoreCase("0")) {
            player.setGameMode(GameMode.SURVIVAL);
        } else if (mode.equalsIgnoreCase("1")) {
            player.setGameMode(GameMode.CREATIVE);
        } else if (mode.equalsIgnoreCase("2")) {
            player.setGameMode(GameMode.ADVENTURE);
        } else if (mode.equalsIgnoreCase("3")) {
            player.setGameMode(GameMode.SPECTATOR);
        } else {
            player.sendMessage(ChatColor.RED + "Invalid mode.");
            return false;
        }
        player.sendMessage(ChatColor.GREEN + "You have changed your game mode!");

        return true;
    }
}
