package net.kappa.boxpvp.utils.impl.rank;

import net.kappa.boxpvp.utils.impl.RankInterface;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.group.GroupManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import org.bukkit.entity.Player;

import java.util.Objects;

public class LuckPermsImpl implements RankInterface {
    private final UserManager users;
    private final GroupManager groups;

    public LuckPermsImpl() {
        this.users = LuckPermsProvider.get().getUserManager();
        this.groups = LuckPermsProvider.get().getGroupManager();
    }

    @Override
    public String getGroupPrefix(Player player) {
        final User user = this.users.getUser(player.getUniqueId());
        if (user == null) return "";
        final Group group = this.groups.getGroup(user.getPrimaryGroup());
        if (group == null) return "";
        return group.getFriendlyName();
    }

    @Override
    public String getGroupName(Player player) {
        final User user = this.users.getUser(player.getUniqueId());
        if (user == null) return "";
        final Group group = this.groups.getGroup(user.getPrimaryGroup());
        if (group == null) return "";
        return group.getName();
    }
}
