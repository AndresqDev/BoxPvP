package net.kappa.boxpvp.commands.social;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.files.list.messages.MessagesFile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TwitterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "No console.");
            return false;
        }

        sender.sendMessage(MessagesFile.social_twitter);
        return true;
    }
}
