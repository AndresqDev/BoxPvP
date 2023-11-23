package net.kappa.boxpvp.commands.admin;

import net.kappa.boxpvp.files.list.system.DataFile;
import net.kappa.boxpvp.managers.list.DataManager;
import net.kappa.boxpvp.utils.objects.ClaimObject;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RemoveClaimCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "No console.");
            return false;
        } else if (!sender.hasPermission("core.claim")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return false;
        } else if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Insufficient arguments.");
            return false;
        }

        final Player player = (Player) sender;
        final String name = args[0];

        for(ClaimObject claim : DataFile.claims) {
            if (claim.getName().equals(name)) {
                DataManager.removeClaim(name);
                player.sendMessage(ChatColor.GREEN + "Claim removed!");
                return true;
            }
        }

        player.sendMessage(ChatColor.RED + "Claim not found!");

        return false;
    }
}
