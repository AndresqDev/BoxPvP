package net.kappa.boxpvp.commands.admin;

import net.kappa.boxpvp.files.list.messages.AdminFile;
import net.kappa.boxpvp.managers.list.ClaimManager;
import net.kappa.boxpvp.managers.list.DataManager;
import net.kappa.boxpvp.utils.CuboidUtil;
import net.kappa.boxpvp.utils.PlaceholderUtil;
import net.kappa.boxpvp.utils.objects.LocationObject;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClaimCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "No console.");
            return false;
        } else if (!sender.hasPermission("core.claim")) {
            sender.sendMessage(PlaceholderUtil.setPlaceholders((Player) sender, AdminFile.admin_general_noPermission));
            return false;
        } else if (args.length < 3) {
            sender.sendMessage(PlaceholderUtil.setPlaceholders((Player) sender, AdminFile.admin_general_insufficientArgs));
            return false;
        }

        final Player player = (Player) sender;
        final String name = args[0];
        boolean pvp = Boolean.parseBoolean(args[1]);
        boolean mine = Boolean.parseBoolean(args[2]);
 
        if(ClaimManager.containsWand(player)) {
            ClaimManager.putWand(player, new LocationObject());
            player.sendMessage(PlaceholderUtil.setPlaceholders(player, AdminFile.admin_command_claim_unplacedPositions));
            player.sendMessage(PlaceholderUtil.setPlaceholders(player, AdminFile.admin_command_claim_cancel));
            return true;
        } else {
            if(ClaimManager.getWand(player).getStart() == null || ClaimManager.getWand(player).getLast() == null) {
                ClaimManager.removeWand(player);
                player.sendMessage(PlaceholderUtil.setPlaceholders(player, AdminFile.admin_command_claim_unplacedPositions));
                return true;
            }
        }

        DataManager.addClaim(name, new CuboidUtil(ClaimManager.getWand(player).getStart(), ClaimManager.getWand(player).getLast()), pvp, mine);
        ClaimManager.removeWand(player);
        player.sendMessage(PlaceholderUtil.setPlaceholders(player, AdminFile.admin_command_claim_create));

        return true;
    }
}
