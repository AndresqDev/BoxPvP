package net.kappa.boxpvp.commands.admin;

import net.kappa.boxpvp.managers.list.DataManager;
import net.kappa.boxpvp.managers.list.MineManager;
import net.kappa.boxpvp.utils.Cuboid;
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
        } else if (!sender.hasPermission("core.gamemode")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return false;
        } else if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Insufficient arguments.");
            return false;
        }

        final Player player = (Player) sender;
        final String name = args[0];
        final Material material = Material.getMaterial(args[1]);
        Integer time;
        try {
            time = Integer.parseInt(args[2]);
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

        DataManager.addMine(name, new Cuboid(MineManager.getWand(player).getStart(), MineManager.getWand(player).getLast()), new ArrayList<>(Collections.singleton(material)), time);
        MineManager.removeWand(player);
        player.sendMessage(ChatColor.GREEN + "Mine created!");

        return true;
    }
}
