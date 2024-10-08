package net.kappa.boxpvp.listeners.list.system;

import net.kappa.boxpvp.files.list.OptionsFile;
import net.kappa.boxpvp.files.list.system.DataFile;
import net.kappa.boxpvp.managers.list.ClaimManager;
import net.kappa.boxpvp.managers.list.CombatManager;
import net.kappa.boxpvp.managers.list.TimerManager;
import net.kappa.boxpvp.utils.objects.ClaimObject;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class SystemListener implements Listener {
    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if (event.getEntity().getType() == EntityType.SKELETON) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void BreakBlocks(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        final ClaimObject claim = ClaimManager.getClaimAt(event.getBlock());

        if (claim != null && claim.isMine()) return;

        event.setCancelled(!OptionsFile.world_block_break);
    }

    @EventHandler
    public void PlaceBlocks(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(!OptionsFile.world_block_place);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)) return;
        final Player player = ((Player) event.getEntity()).getPlayer();
        final Player damager = ((Player) event.getDamager()).getPlayer();

        final ClaimObject claim = ClaimManager.getClaimAt(player);

        if (TimerManager.isActive(player, "pvp") &&
                claim != null && !claim.isPvP()) {
            event.setCancelled(true);
            return;
        }

        CombatManager.addPvPTag(damager);
        CombatManager.addPvPTag(player);

        event.setCancelled(!OptionsFile.world_entity_damage);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void Move(PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (player.getLocation().getY() < 0) {
            if (DataFile.spawn_location == null) player.teleport(DataFile.alternative_spawn);
            else player.teleport(DataFile.spawn_location);
        }
    }

    @EventHandler
    public void Weather(WeatherChangeEvent event) {
        event.setCancelled(!OptionsFile.world_weather_changes);
    }

    @EventHandler
    public void MobSpawning(CreatureSpawnEvent event) {
        event.setCancelled(!OptionsFile.world_mob_spawn);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void Dropping(PlayerDropItemEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(!OptionsFile.world_item_drop);
    }

    @EventHandler
    private void FoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(!OptionsFile.world_hunger_loss);
    }

    @EventHandler
    private void EntityExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    private void ItemPickup(PlayerPickupItemEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(!OptionsFile.world_item_pickup);
    }
}
