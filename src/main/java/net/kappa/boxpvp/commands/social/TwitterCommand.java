package net.kappa.boxpvp.commands.social;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.files.list.messages.MessagesFile;
import net.kappa.boxpvp.utils.PlaceholderUtil;
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

        sender.sendMessage(PlaceholderUtil.setPlaceholders((Player) sender, MessagesFile.social_twitter));
        return true;
    }
}
