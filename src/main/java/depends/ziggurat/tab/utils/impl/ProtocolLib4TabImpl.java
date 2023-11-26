package depends.ziggurat.tab.utils.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.*;
import depends.ziggurat.tab.Ziggurat;
import depends.ziggurat.tab.ZigguratCommons;
import depends.ziggurat.tab.ZigguratTablistClassic;
import depends.ziggurat.tab.ZigguratTablistModern;
import depends.ziggurat.tab.utils.*;
import depends.ziggurat.utils.PlayerUtility;
import depends.ziggurat.utils.playerversion.PlayerVersion;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProtocolLib4TabImpl implements IZigguratHelper {
    private static ProtocolManager pm;
    private final PacketAdapter sb;
    private final PacketAdapter pi;

    public ProtocolLib4TabImpl(final Ziggurat ziggurat) {
        pm = ProtocolLibrary.getProtocolManager();
        this.sb = new PacketAdapter(ziggurat.getPlugin(), ListenerPriority.MONITOR, PacketType.Play.Server.SCOREBOARD_TEAM) {

            public void onPacketSending(PacketEvent event) {
                try {
                    PacketContainer packet = event.getPacket();
                    int mode = event.getPacket().getIntegers().read(1);
                    if (mode == 4 && !event.getPacket().getStrings().read(0).equals("\u000181")) {
                        packet.getIntegers().write(1, 3);
                        packet.getStrings().write(0, "\u000181");
                        packet.getStrings().write(1, "\u000181");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        pm.addPacketListener(this.sb);
        this.pi = new PacketAdapter(ziggurat.getPlugin(), PacketType.Play.Server.PLAYER_INFO) {

            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Server.PLAYER_INFO) {
                    try {
                        EnumWrappers.PlayerInfoAction action = event.getPacket().getPlayerInfoAction().read(0);
                        if (action == EnumWrappers.PlayerInfoAction.ADD_PLAYER) {
                            List<PlayerInfoData> data = event.getPacket().getPlayerInfoDataLists().read(0);
                            for (PlayerInfoData playerInfoData : data) {
                                UUID uuid = playerInfoData.getProfile().getUUID();
                                boolean yes = false;
                                if (ziggurat.getTablists().containsKey(event.getPlayer().getUniqueId())) {
                                    for (TabEntry tabEntry : ziggurat.getTablists().get(event.getPlayer().getUniqueId()).getCurrentEntries()) {
                                        if (tabEntry.getUuid() != uuid) continue;
                                        yes = true;
                                    }
                                }
                                if (yes) continue;
                                Team team = event.getPlayer().getScoreboard().getTeam("\u000181");
                                if (team == null) {
                                    team = event.getPlayer().getScoreboard().registerNewTeam("\u000181");
                                }
                                team.addEntry(playerInfoData.getProfile().getName());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        pm.addPacketListener(this.pi);
    }

    public static void sendPacket(Player player, PacketContainer packetContainer) {
        try {
            pm.sendServerPacket(player, packetContainer);
        } catch (Exception ignored){

        }
    }

    @Override
    public void cleanup() {
        pm.removePacketListener(this.pi);
        pm.removePacketListener(this.sb);
    }

    @Override
    public TabEntry createFakePlayer(ZigguratTablistModern zigguratTablist, String string, TabColumn column, Integer slot, Integer rawSlot) {
        UUID uuid = UUID.randomUUID();
        Player player = zigguratTablist.getPlayer();
        PlayerVersion playerVersion = PlayerUtility.getPlayerVersion(player);
        PacketContainer packet = pm.createPacket(PacketType.Play.Server.PLAYER_INFO);
        packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        WrappedGameProfile profile = new WrappedGameProfile(uuid, string);
        PlayerInfoData playerInfoData = new PlayerInfoData(profile, 1, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText(playerVersion != PlayerVersion.v1_7 ? "" : profile.getName()));
        if (playerVersion != PlayerVersion.v1_7) {
            playerInfoData.getProfile().getProperties().put("textures", new WrappedSignedProperty("textures", ZigguratCommons.defaultTexture.SKIN_VALUE, ZigguratCommons.defaultTexture.SKIN_SIGNATURE));
        }
        packet.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));
        ProtocolLib4TabImpl.sendPacket(player, packet);
        return new TabEntry(string, uuid, "", ZigguratCommons.defaultTexture, column, slot, rawSlot, 1);
    }

    @Override
    public void destroyFakePlayer(ZigguratTablistModern zigguratTablist, TabEntry tabEntry, String customName) {
        Player player = zigguratTablist.getPlayer();
        PlayerVersion playerVersion = PlayerUtility.getPlayerVersion(player);
        PacketContainer packet = pm.createPacket(PacketType.Play.Server.PLAYER_INFO);
        packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
        WrappedGameProfile profile = new WrappedGameProfile(tabEntry.getUuid(), playerVersion != PlayerVersion.v1_7 ? tabEntry.getId() : LegacyClientUtils.tabEntrys.get(tabEntry.getRawSlot() - 1));
        PlayerInfoData playerInfoData = new PlayerInfoData(profile, 1, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText(playerVersion != PlayerVersion.v1_7 ? "" : profile.getName()));
        packet.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));
        ProtocolLib4TabImpl.sendPacket(player, packet);
    }

    @Override
    public void updateFakeName(ZigguratTablistModern zigguratTablist, TabEntry tabEntry, String text) {
        Player player = zigguratTablist.getPlayer();
        PlayerVersion playerVersion = PlayerUtility.getPlayerVersion(player);
        String[] newStrings = ZigguratTablistModern.splitStrings(text);
        if (playerVersion == PlayerVersion.v1_7) {
            Team team = player.getScoreboard().getTeam(LegacyClientUtils.teamNames.get(tabEntry.getRawSlot() - 1));
            if (team == null) {
                team = player.getScoreboard().registerNewTeam(LegacyClientUtils.teamNames.get(tabEntry.getRawSlot() - 1));
                team.addEntry(LegacyClientUtils.tabEntrys.get(tabEntry.getRawSlot() - 1));
            }
            team.setPrefix(newStrings[0]);
            if (newStrings.length > 1) {
                team.setSuffix(newStrings[1]);
            } else {
                team.setSuffix("");
            }
        } else {
            PacketContainer packet = pm.createPacket(PacketType.Play.Server.PLAYER_INFO);
            packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME);
            WrappedGameProfile profile = new WrappedGameProfile(tabEntry.getUuid(), tabEntry.getId());
            PlayerInfoData playerInfoData;
            if (PlayerUtility.getPlayerVersionRaw(player) < 735) {
                playerInfoData = new PlayerInfoData(profile, 1, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText(newStrings.length > 1 ? newStrings[0] + newStrings[1] : newStrings[0]));
            } else {
                String displayNameJson = ComponentSerializer.toString(TextComponent.fromLegacyText(text));
                playerInfoData = new PlayerInfoData(profile, 1, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromJson(displayNameJson));
            }
            packet.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));
            ProtocolLib4TabImpl.sendPacket(player, packet);
        }
        tabEntry.setText(text);
    }

    @Override
    public void updateFakeLatency(ZigguratTablistModern zigguratTablist, TabEntry tabEntry, Integer latency) {
        PacketContainer packet = pm.createPacket(PacketType.Play.Server.PLAYER_INFO);
        packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.UPDATE_LATENCY);
        WrappedGameProfile profile = new WrappedGameProfile(tabEntry.getUuid(), tabEntry.getId());
        PlayerInfoData playerInfoData = new PlayerInfoData(profile, latency, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText(tabEntry.getText()));
        packet.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));
        ProtocolLib4TabImpl.sendPacket(zigguratTablist.getPlayer(), packet);
        tabEntry.setLatency(latency);
    }

    @Override
    public void updateFakeSkin(ZigguratTablistModern zigguratTablist, TabEntry tabEntry, SkinTexture skinTexture) {
        Player player = zigguratTablist.getPlayer();
        PlayerVersion playerVersion = PlayerUtility.getPlayerVersion(player);
        WrappedGameProfile profile = new WrappedGameProfile(tabEntry.getUuid(), playerVersion != PlayerVersion.v1_7 ? tabEntry.getId() : LegacyClientUtils.tabEntrys.get(tabEntry.getRawSlot() - 1));
        PlayerInfoData playerInfoData = new PlayerInfoData(profile, 1, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText(playerVersion != PlayerVersion.v1_7 ? "" : profile.getName()));
        playerInfoData.getProfile().getProperties().put("textures", new WrappedSignedProperty("textures", skinTexture.SKIN_VALUE, skinTexture.SKIN_SIGNATURE));
        PacketContainer remove = pm.createPacket(PacketType.Play.Server.PLAYER_INFO);
        remove.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
        remove.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));
        PacketContainer add = pm.createPacket(PacketType.Play.Server.PLAYER_INFO);
        add.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
        add.getPlayerInfoDataLists().write(0, Collections.singletonList(playerInfoData));
        ProtocolLib4TabImpl.sendPacket(player, remove);
        ProtocolLib4TabImpl.sendPacket(player, add);
        tabEntry.setTexture(skinTexture);
    }

    @Override
    public void updateHeaderAndFooter(ZigguratTablistModern zigguratTablist, String header, String footer) {
        Player player = zigguratTablist.getPlayer();
        PacketContainer headerAndFooter = new PacketContainer(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        if (PlayerUtility.getPlayerVersionRaw(player) < 735) {
            headerAndFooter.getChatComponents().write(0, WrappedChatComponent.fromText(header));
            headerAndFooter.getChatComponents().write(1, WrappedChatComponent.fromText(footer));
        } else {
            String headerJson = ComponentSerializer.toString(TextComponent.fromLegacyText(header));
            String footerJson = ComponentSerializer.toString(TextComponent.fromLegacyText(footer));
            headerAndFooter.getChatComponents().write(0, WrappedChatComponent.fromJson(headerJson));
            headerAndFooter.getChatComponents().write(1, WrappedChatComponent.fromJson(footerJson));
        }

        ProtocolLib4TabImpl.sendPacket(player, headerAndFooter);
    }

    @Override
    public void updateHeaderAndFooter(ZigguratTablistClassic zigguratTablist, String header, String footer) {
        Player player = zigguratTablist.getPlayer();
        PacketContainer headerAndFooter = new PacketContainer(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        if (PlayerUtility.getPlayerVersionRaw(player) < 735) {
            headerAndFooter.getChatComponents().write(0, WrappedChatComponent.fromText(header));
            headerAndFooter.getChatComponents().write(1, WrappedChatComponent.fromText(footer));
        } else {
            String headerJson = ComponentSerializer.toString(TextComponent.fromLegacyText(header));
            String footerJson = ComponentSerializer.toString(TextComponent.fromLegacyText(footer));
            headerAndFooter.getChatComponents().write(0, WrappedChatComponent.fromJson(headerJson));
            headerAndFooter.getChatComponents().write(1, WrappedChatComponent.fromJson(footerJson));
        }

        ProtocolLib4TabImpl.sendPacket(player, headerAndFooter);
    }
}

