package net.kappa.boxpvp.commands.admin;

import net.kappa.boxpvp.files.list.messages.AdminFile;
import net.kappa.boxpvp.files.list.system.DataFile;
import net.kappa.boxpvp.managers.list.DataManager;
import net.kappa.boxpvp.managers.list.MineManager;
import net.kappa.boxpvp.utils.CuboidUtil;
import net.kappa.boxpvp.utils.objects.ClaimObject;
import net.kappa.boxpvp.utils.objects.LocationObject;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class MineCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "No console.");
            return false;
        } else if (!sender.hasPermission("core.mine")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return false;
        } else if (args.length < 1) {
            for (String str : AdminFile.helpcmd_mine) sender.sendMessage(str);
            return false;
        }

        if (args[0].equals("create")) {
            if (args.length < 3) {
                for (String str : AdminFile.helpcmd_mine) sender.sendMessage(str);
                return false;
            }
            final Player player = (Player) sender;
            final String name = args[1];
            final Material material = Material.getMaterial(args[2]);
            int time;
            try {
                time = Integer.parseInt(args[3]);
            } catch (NumberFormatException exc) {
                player.sendMessage(ChatColor.RED + "Invalid time format.");
                return false;
            }

            if(time <= 0){
                player.sendMessage(ChatColor.RED + "Invalid time.");
                return false;
            }

            if(material == null){
                player.sendMessage(ChatColor.RED + "Invalid material.");
                return false;
            }

            if(MineManager.containsWand(player)) {
                MineManager.putWand(player, new LocationObject());
                player.sendMessage(ChatColor.GREEN + "Please select area using your hand! (Left click & Right Click)");
                player.sendMessage(ChatColor.RED + "Execute again the command for cancel this action!");
                return true;
            } else {
                if(MineManager.getWand(player).getStart() == null || MineManager.getWand(player).getLast() == null) {
                    MineManager.removeWand(player);
                    player.sendMessage(ChatColor.RED + "Please select area using your hand! (Left click & Right Click)");
                    return true;
                }
            }

            DataManager.addMine(name, new CuboidUtil(MineManager.getWand(player).getStart(), MineManager.getWand(player).getLast()), new ArrayList<>(Collections.singleton(material)), time);
            MineManager.removeWand(player);
            player.sendMessage(ChatColor.GREEN + "Mine created!");

            return true;
        } else if (args[0].equals("remove")) {
            if (args.length < 2) {
                for (String str : AdminFile.helpcmd_mine) sender.sendMessage(str);
                return false;
            }
            final Player player = (Player) sender;
            final String name = args[1];

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
            for (String str : AdminFile.helpcmd_mine) sender.sendMessage(str);
            return false;
        }


    }
}
