package lib.ziggurat.tab;

import lib.ziggurat.utils.PlayerUtility;
import lib.ziggurat.utils.playerversion.PlayerVersion;
import org.bukkit.entity.Player;

public class ZigguratTablistClassic {
    private final Player player;
    private final Ziggurat ziggurat;

    public ZigguratTablistClassic(Player player, Ziggurat ziggurat) {
        this.player = player;
        this.ziggurat = ziggurat;
        this.setup();
    }

    private void setup() {
        if (PlayerUtility.getPlayerVersion(this.player) != PlayerVersion.v1_7 && (this.ziggurat.getAdapter().getHeader() != null || this.ziggurat.getAdapter().getFooter() != null)) {
            String header = this.ziggurat.getAdapter().getHeader() == null ? "" : this.ziggurat.getAdapter().getHeader();
            String footer = this.ziggurat.getAdapter().getFooter() == null ? "" : this.ziggurat.getAdapter().getFooter();
            this.ziggurat.getImplementation().updateHeaderAndFooter(this, header, footer);
        }
        this.update();
    }

    public void update() {
        this.player.setPlayerListName(this.ziggurat.getAdapter().getClassicSlot(player));
    }

    public Player getPlayer() {
        return this.player;
    }
}
