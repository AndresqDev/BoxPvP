package net.kappa.boxpvp.commands.others;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.files.list.messages.MessagesFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MsgCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "No console.");
            return false;
        } else if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Insufficient arguments.");
            return false;
        }

        final String message = args[0];
        final Player player = (Player) sender;
        final Player target = Bukkit.getPlayer(message);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return false;
        }

        player.sendMessage(MessagesFile.private_message_send_format.replace("%target%", target.getName()));
        target.sendMessage(MessagesFile.private_message_receive_format.replace("%target%", target.getName()));
        return true;
    }
}
