package net.kappa.boxpvp.managers.list;

import net.kappa.boxpvp.utils.StatusUtil;
import net.kappa.boxpvp.utils.impl.RankInterface;
import net.kappa.boxpvp.utils.impl.rank.*;

public class RankManager {
    private static RankInterface impl;

    public static void load() {
        if (StatusUtil.isEnabled("AquaCore")) impl = new AquaCoreImpl();
        else if (StatusUtil.isEnabled("LuckPerms")) impl = new LuckPermsImpl();
        else if (StatusUtil.isEnabled("Helium")) impl = new HeliumImpl();
        else if (StatusUtil.isEnabled("Vault")) impl = new VaultImpl();
        else impl = new VoidImpl();
    }

    public static RankInterface getImpl() {
        return impl;
    }

    public static void disable() {
        impl = null;
    }
}

