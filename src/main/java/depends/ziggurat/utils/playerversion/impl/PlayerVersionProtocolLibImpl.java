package depends.ziggurat.utils.playerversion.impl;

import com.comphenix.protocol.ProtocolLibrary;
import depends.ziggurat.utils.playerversion.IPlayerVersion;
import depends.ziggurat.utils.playerversion.PlayerVersion;
import org.bukkit.entity.Player;

public class PlayerVersionProtocolLibImpl implements IPlayerVersion {
    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(ProtocolLibrary.getProtocolManager().getProtocolVersion(player));
    }

    @Override
    public int getPlayerVersionRaw(Player player) {
        return ProtocolLibrary.getProtocolManager().getProtocolVersion(player);
    }

}

