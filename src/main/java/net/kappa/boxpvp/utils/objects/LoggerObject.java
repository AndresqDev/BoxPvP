package net.kappa.boxpvp.utils.objects;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LoggerObject extends EntitySkeleton {
    private final World world;
    private final ItemStack[] contents;
    private final ItemStack[] armor;
    private final Player player;

    public LoggerObject(Player player) {
        super(EntityTypes.SKELETON, ((CraftWorld)player.getWorld()).getHandle());

        this.world = player.getWorld();
        this.setHealth((float) player.getHealth());
        this.setOnFire(0);
        this.setNoAI(true);
        this.persistent = true;

        this.player = player;
        this.contents = player.getInventory().getContents();
        this.armor = player.getInventory().getArmorContents();

        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(player.getInventory().getItemInMainHand()));
        for (int i = 0; i < 4; i++) {
            this.setSlot(EnumItemSlot.values()[i + 1], CraftItemStack.asNMSCopy(this.armor[i]));
        }

        this.setCustomName(CraftChatMessage.fromString(player.getDisplayName())[0]);
        this.setCustomNameVisible(true);

        Location loc = player.getLocation();
        double locY = loc.getBlock().getType() == Material.AIR ? loc.getY() : Math.floor(loc.getY()) + 1;

        this.setPosition(loc.getX(), locY, loc.getZ());
        this.yaw = loc.getYaw();
        this.pitch = loc.getPitch();

        this.setLocation(loc.getBlockX() + 0.5D, loc.getY(), loc.getBlockZ() + 0.5D, loc.getYaw(), loc.getPitch());
        ((CraftWorld) player.getWorld()).getHandle().addEntity(this);
    }

    @Override
    public void die(DamageSource damageSource) {
        for (ItemStack item : this.contents) {
            if (item == null) continue;
            this.world.dropItemNaturally(this.getBukkitEntity().getLocation(), item);
        }

        for (ItemStack item : this.armor) {
            if (item == null) continue;
            this.world.dropItemNaturally(this.getBukkitEntity().getLocation(), item);
        }

        this.player.getInventory().clear();
        this.player.setHealth(this.getHealth());
    }
}