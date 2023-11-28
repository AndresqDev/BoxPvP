package net.kappa.boxpvp.files.list.messages;

import net.kappa.boxpvp.utils.ColorUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Objects;

import static net.kappa.boxpvp.managers.list.FileManager.fileconfig;

public class AdminFile {

    // Prefix

    public static String pluginPrefix;

    // General
    public static String admin_general_noPermission;
    public static String admin_general_insufficientArgs;

    // Commands - Claim
    public static String admin_command_claim_create;
    public static String admin_command_claim_unplacedPositions;
    public static String admin_command_claim_cancel;

    // Help Messages
    public static List<String> helpcmd_claim;
    public static List<String> helpcmd_mine;
    public AdminFile() {
        this.setup();
    }

    private void setup() {
        final FileConfiguration content = fileconfig.get("admin");

        // Prefix

        pluginPrefix = ColorUtil.translate(Objects.requireNonNull(content.getString("admin.plugin-prefix")));

        // General
        admin_general_noPermission = ColorUtil.translate(Objects.requireNonNull(content.getString("admin.general.noPermission")));
        admin_general_insufficientArgs = ColorUtil.translate(Objects.requireNonNull(content.getString("admin.general.insufficientArgs")));

        // Commands - Claim
        admin_command_claim_create = ColorUtil.translate(Objects.requireNonNull(content.getString("admin.commands.claim.create")));
        admin_command_claim_unplacedPositions = ColorUtil.translate(Objects.requireNonNull(content.getString("admin.commands.claim.unplacedPositions")));
        admin_command_claim_cancel = ColorUtil.translate(Objects.requireNonNull(content.getString("admin.commands.claim.cancel")));

        // Help Messages
        helpcmd_claim = ColorUtil.translate(content.getStringList("admin.helpcmd.claim"));
        helpcmd_mine = ColorUtil.translate(content.getStringList("admin.helpcmd.mine"));
    }
}
