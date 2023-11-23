package net.kappa.boxpvp.utils.impl.rank;

import net.kappa.boxpvp.utils.impl.RankInterface;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import static net.kappa.boxpvp.Main.plugin;

public class VaultImpl implements RankInterface {
    private final Chat instance;

    public VaultImpl() {
        final RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        this.instance = rsp.getProvider();
    }

    @Override
    public String getGroupPrefix(Player player) {
        if (this.instance.getPrimaryGroup(player) == null || this.instance.getGroupPrefix(player.getWorld(), this.instance.getPrimaryGroup(player)) == null)
            return "";
        else return this.instance.getGroupPrefix(player.getWorld(), this.instance.getPrimaryGroup(player));
    }

    @Override
    public String getGroupName(Player player) {
        if (this.instance.getPrimaryGroup(player) == null) return "";
        return this.instance.getPrimaryGroup(player);
    }
}
