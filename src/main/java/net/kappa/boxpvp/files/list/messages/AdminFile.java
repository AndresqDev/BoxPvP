package net.kappa.boxpvp.files.list.messages;

import net.kappa.boxpvp.utils.ColorUtil;
import org.bukkit.configuration.file.FileConfiguration;

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
        admin_command_claim_create = ColorUtil.translate(Objects.requireNonNull(content.getString("admin.command.claim.create")));
        admin_command_claim_unplacedPositions = ColorUtil.translate(Objects.requireNonNull(content.getString("admin.command.claim.unplacedPositions")));
        admin_command_claim_cancel = ColorUtil.translate(Objects.requireNonNull(content.getString("admin.command.claim.cancel")));


    }
}
