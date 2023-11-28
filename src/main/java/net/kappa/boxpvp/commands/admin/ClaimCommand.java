package net.kappa.boxpvp.commands.admin;

import net.kappa.boxpvp.files.list.messages.AdminFile;
import net.kappa.boxpvp.files.list.system.DataFile;
import net.kappa.boxpvp.managers.list.ClaimManager;
import net.kappa.boxpvp.managers.list.DataManager;
import net.kappa.boxpvp.utils.CuboidUtil;
import net.kappa.boxpvp.utils.objects.ClaimObject;
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
            sender.sendMessage(AdminFile.admin_general_noPermission);
            return false;
        } else if (args.length < 1) {
            for (String str : AdminFile.helpcmd_claim) sender.sendMessage(str);
            return false;
        }

        if (args[1].equals("create")) {
            if (args.length < 4) {
                for (String str : AdminFile.helpcmd_claim) sender.sendMessage(str);
                return false;
            }
            final Player player = (Player) sender;
            final String name = args[0];
            boolean pvp = Boolean.parseBoolean(args[2]);
            boolean mine = Boolean.parseBoolean(args[3]);

            if(ClaimManager.containsWand(player)) {
                ClaimManager.putWand(player, new LocationObject());
                player.sendMessage(AdminFile.admin_command_claim_unplacedPositions);
                player.sendMessage(AdminFile.admin_command_claim_cancel);
                return true;
            } else {
                if(ClaimManager.getWand(player).getStart() == null || ClaimManager.getWand(player).getLast() == null) {
                    ClaimManager.removeWand(player);
                    player.sendMessage(AdminFile.admin_command_claim_unplacedPositions);
                    return true;
                }
            }

            DataManager.addClaim(name, new CuboidUtil(ClaimManager.getWand(player).getStart(), ClaimManager.getWand(player).getLast()), pvp, mine);
            ClaimManager.removeWand(player);
            player.sendMessage(AdminFile.admin_command_claim_create);

            return true;
        } else if (args[1].equals("remove")) {
            if (args.length < 3) {
                for (String str : AdminFile.helpcmd_claim) sender.sendMessage(str);
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
        } else {
            for (String str : AdminFile.helpcmd_claim) sender.sendMessage(str);
            return false;
        }
    }
}
