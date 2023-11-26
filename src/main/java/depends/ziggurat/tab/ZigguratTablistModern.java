package depends.ziggurat.tab;

import com.comphenix.protocol.ProtocolLibrary;
import depends.ziggurat.tab.utils.*;
import depends.ziggurat.tab.utils.impl.ProtocolLib4TabImpl;
import depends.ziggurat.utils.PlayerUtility;
import depends.ziggurat.utils.playerversion.PlayerVersion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ZigguratTablistModern {
    private final Player player;
    private final Scoreboard scoreboard;
    private final Set<TabEntry> currentEntries = ConcurrentHashMap.newKeySet();
    private final Ziggurat ziggurat;

    public ZigguratTablistModern(Player player, Ziggurat ziggurat) {
        this.player = player;
        this.ziggurat = ziggurat;
        this.scoreboard = ziggurat.isHook() || player.getScoreboard() != Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard() ? player.getScoreboard() : Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(this.scoreboard);
        this.setup();
    }

    public static String[] splitStrings(String text) {
        String suffix;
        if (text.length() <= 16) {
            return new String[]{text, ""};
        }
        String prefix = text.substring(0, 16);
        if (prefix.charAt(15) == '§' || prefix.charAt(15) == '&') {
            prefix = prefix.substring(0, 15);
            suffix = text.substring(15);
        } else if (prefix.charAt(14) == '§' || prefix.charAt(14) == '&') {
            prefix = prefix.substring(0, 14);
            suffix = text.substring(14);
        } else {
            suffix = ChatColor.getLastColors(prefix) + text.substring(16);
        }
        if (suffix.length() > 16) {
            suffix = suffix.substring(0, 16);
        }
        return new String[]{prefix, suffix};
    }

    private void setup() {
        int possibleSlots = PlayerUtility.getPlayerVersion(this.player) == PlayerVersion.v1_7 ? 60 : 80;
        if (PlayerUtility.getPlayerVersion(this.player) != PlayerVersion.v1_7 && (this.ziggurat.getAdapter().getHeader() != null || this.ziggurat.getAdapter().getFooter() != null)) {
            String header = this.ziggurat.getAdapter().getHeader() == null ? "" : this.ziggurat.getAdapter().getHeader();
            String footer = this.ziggurat.getAdapter().getFooter() == null ? "" : this.ziggurat.getAdapter().getFooter();
            this.ziggurat.getImplementation().updateHeaderAndFooter(this, header, footer);
        }
        for (int i = 1; i <= possibleSlots; ++i) {
            TabColumn tabColumn;
            if (this.scoreboard == null || this.scoreboard != this.player.getScoreboard() || (tabColumn = TabColumn.getFromSlot(this.player, i)) == null)
                continue;
            TabEntry tabEntry = this.ziggurat.getImplementation().createFakePlayer(this, LegacyClientUtils.tabEntrys.get(i - 1), tabColumn, tabColumn.getNumb(this.player, i), i);
            Team team = this.player.getScoreboard().getTeam(LegacyClientUtils.teamNames.get(i - 1));
            if (team != null) {
                team.unregister();
            }
            team = this.player.getScoreboard().registerNewTeam(LegacyClientUtils.teamNames.get(i - 1));
            team.setPrefix("hey");
            team.setSuffix("there");
            team.addEntry(LegacyClientUtils.tabEntrys.get(i - 1));
            this.currentEntries.add(tabEntry);
        }
    }

    public void cleanup() {
        for (TabEntry tabEntry : this.getCurrentEntries()) {
            Team team = this.player.getScoreboard().getTeam(LegacyClientUtils.teamNames.get(tabEntry.getRawSlot() - 1));
            if (team != null) {
                team.unregister();
            }
            boolean skip = this.ziggurat.getImplementation() instanceof ProtocolLib4TabImpl && (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null || !Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ProtocolLib")).isEnabled() || ProtocolLibrary.getProtocolManager() == null);
            if (skip) continue;
            this.getZiggurat().getImplementation().destroyFakePlayer(this, tabEntry, null);
            this.getZiggurat().getImplementation().cleanup();
        }
        if (!this.getZiggurat().isHook()) {
            this.player.setScoreboard(Objects.requireNonNull(Objects.requireNonNull(Bukkit.getScoreboardManager())).getMainScoreboard());
        }
    }

    public void update() {
        HashSet<TabEntry> previous = new HashSet<>(this.currentEntries);
        Set<BufferedTabObject> processedObjects = this.ziggurat.getAdapter().getSlots(this.player);
        if (this.ziggurat.getAdapter().getSlots(this.player) == null) {
            processedObjects = new HashSet<>();
        }
        for (BufferedTabObject scoreObject : processedObjects) {
            TabEntry tabEntry = this.getEntry(scoreObject.getColumn(), scoreObject.getSlot());
            if (tabEntry == null) continue;
            previous.remove(tabEntry);
            this.updateTabEntry(tabEntry, scoreObject);
        }
        for (TabEntry tabEntry : previous) {
            this.updateTabEntry(tabEntry, null);
        }
        previous.clear();
        if (this.player.getScoreboard() != this.scoreboard && this.scoreboard != null && !this.getZiggurat().isHook()) {
            this.player.setScoreboard(this.scoreboard);
        }
    }

    private void updateTabEntry(TabEntry tabEntry, BufferedTabObject tabObject) {
        if (PlayerUtility.getPlayerVersion(this.player) != PlayerVersion.v1_7) {
            this.updateFakeSkin(tabEntry, tabObject == null ? ZigguratCommons.defaultTexture : tabObject.getSkinTexture());
        }
        this.updateName(tabEntry, tabObject == null ? "" : tabObject.getText());
        this.updateLatency(tabEntry, tabObject == null || tabObject.getPing() == null ? -1 : tabObject.getPing());
    }

    private void updateFakeSkin(TabEntry tabEntry, SkinTexture skinTexture) {
        if (tabEntry.getTexture().toString().equals(skinTexture.toString())) {
            return;
        }
        this.ziggurat.getImplementation().updateFakeSkin(this, tabEntry, skinTexture);
    }

    private void updateLatency(TabEntry tabEntry, int latency) {
        if (tabEntry.getLatency() == latency) {
            return;
        }
        this.ziggurat.getImplementation().updateFakeLatency(this, tabEntry, latency);
    }

    private void updateName(TabEntry tabEntry, String string) {
        this.ziggurat.getImplementation().updateFakeName(this, tabEntry, string);
    }

    public TabEntry getEntry(TabColumn column, Integer slot) {
        for (TabEntry entry : this.currentEntries) {
            if (!entry.getColumn().name().equalsIgnoreCase(column.name()) || entry.getSlot() != slot) continue;
            return entry;
        }
        return null;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Set<TabEntry> getCurrentEntries() {
        return this.currentEntries;
    }

    public Ziggurat getZiggurat() {
        return this.ziggurat;
    }
}

