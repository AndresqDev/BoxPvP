package net.kappa.boxpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CuboidUtil implements Iterable<Block>, Cloneable, ConfigurationSerializable {
    protected final String worldName;
    protected final int x1;
    protected final int y1;
    protected final int z1;
    protected final int x2;
    protected final int y2;
    protected final int z2;

    public CuboidUtil(Location l1, Location l2) {
        if (!l1.getWorld().equals(l2.getWorld())) {
            throw new IllegalArgumentException("Locations must be on the same world");
        } else {
            this.worldName = l1.getWorld().getName();
            this.x1 = Math.min(l1.getBlockX(), l2.getBlockX());
            this.y1 = Math.min(l1.getBlockY(), l2.getBlockY());
            this.z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
            this.x2 = Math.max(l1.getBlockX(), l2.getBlockX());
            this.y2 = Math.max(l1.getBlockY(), l2.getBlockY());
            this.z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
        }
    }

    public CuboidUtil(String serealized) {
        final String[] unserialized = serealized.split("\\|");
        final Location l1 = new Location(
                Bukkit.getWorld(unserialized[0]),
                Double.parseDouble(unserialized[1]),
                Double.parseDouble(unserialized[2]),
                Double.parseDouble(unserialized[3])
                );

        final Location l2 = new Location(
                Bukkit.getWorld(unserialized[0]),
                Double.parseDouble(unserialized[4]),
                Double.parseDouble(unserialized[5]),
                Double.parseDouble(unserialized[6])
        );

        this.worldName = unserialized[0];
        this.x1 = Math.min(l1.getBlockX(), l2.getBlockX());
        this.y1 = Math.min(l1.getBlockY(), l2.getBlockY());
        this.z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
        this.x2 = Math.max(l1.getBlockX(), l2.getBlockX());
        this.y2 = Math.max(l1.getBlockY(), l2.getBlockY());
        this.z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
    }

    public CuboidUtil(CuboidUtil other) {
        this(other.getWorld().getName(), other.x1, other.y1, other.z1, other.x2, other.y2, other.z2);
    }

    private CuboidUtil(String worldName, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.worldName = worldName;
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("worldName", this.worldName);
        map.put("x1", this.x1);
        map.put("y1", this.y1);
        map.put("z1", this.z1);
        map.put("x2", this.x2);
        map.put("y2", this.y2);
        map.put("z2", this.z2);
        return map;
    }

    public String toString() {
        return this.worldName + "|"
                + this.x1 + "|" + this.y1 + "|" + this.z1 + "|" +
                this.x2 + "|" + this.y2 + "|" + this.z2;
    }


    public World getWorld() {
        World world = Bukkit.getWorld(this.worldName);
        if (world == null) {
            throw new IllegalStateException("World '" + this.worldName + "' is not loaded");
        } else {
            return world;
        }
    }

    public int getSizeX() {
        return this.x2 - this.x1 + 1;
    }

    public int getSizeY() {
        return this.y2 - this.y1 + 1;
    }

    public int getSizeZ() {
        return this.z2 - this.z1 + 1;
    }

    public CuboidUtil unexpand(CuboidUtil.CuboidDirection dir, int amount) {
        switch (dir) {
            case North:
                return new CuboidUtil(this.worldName, this.x1 + amount, this.y1, this.z1, this.x2, this.y2, this.z2);
            case South:
                return new CuboidUtil(this.worldName, this.x1, this.y1, this.z1, this.x2 - amount, this.y2, this.z2);
            case East:
                return new CuboidUtil(this.worldName, this.x1, this.y1, this.z1 + amount, this.x2, this.y2, this.z2);
            case West:
                return new CuboidUtil(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z2 - amount);
            case Down:
                return new CuboidUtil(this.worldName, this.x1, this.y1 + amount, this.z1, this.x2, this.y2, this.z2);
            case Up:
                return new CuboidUtil(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2 - amount, this.z2);
            default:
                throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    public int getVolume() {
        return this.getSizeX() * this.getSizeY() * this.getSizeZ();
    }

    public CuboidUtil getFace(CuboidUtil.CuboidDirection dir) {
        switch (dir) {
            case North:
                return new CuboidUtil(this.worldName, this.x1, this.y1, this.z1, this.x1, this.y2, this.z2);
            case South:
                return new CuboidUtil(this.worldName, this.x2, this.y1, this.z1, this.x2, this.y2, this.z2);
            case East:
                return new CuboidUtil(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z1);
            case West:
                return new CuboidUtil(this.worldName, this.x1, this.y1, this.z2, this.x2, this.y2, this.z2);
            case Down:
                return new CuboidUtil(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y1, this.z2);
            case Up:
                return new CuboidUtil(this.worldName, this.x1, this.y2, this.z1, this.x2, this.y2, this.z2);
            default:
                throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    public List<Block> getFaces() {
        ArrayList<Block> blocks = new ArrayList<>();
        Location min = new Location(this.getWorld(), this.x1, this.y1, this.z1);
        Location max = new Location(this.getWorld(), this.x2, this.y2, this.z2);
        int minX = min.getBlockX();
        int minY = min.getBlockY();
        int minZ = min.getBlockZ();
        int maxX = max.getBlockX();
        int maxY = max.getBlockY();
        int maxZ = max.getBlockZ();

        int z2;
        int x2;
        for (z2 = minX; z2 <= maxX; ++z2) {
            for (x2 = minY; x2 <= maxY; ++x2) {
                blocks.add((new Location(this.getWorld(), z2, x2, minZ)).getBlock());
                blocks.add((new Location(this.getWorld(), z2, x2, maxZ)).getBlock());
            }
        }

        for (z2 = minY; z2 <= maxY; ++z2) {
            for (x2 = minZ; x2 <= maxZ; ++x2) {
                blocks.add((new Location(this.getWorld(), minX, z2, x2)).getBlock());
                blocks.add((new Location(this.getWorld(), maxX, z2, x2)).getBlock());
            }
        }

        for (z2 = minZ; z2 <= maxZ; ++z2) {
            for (x2 = minX; x2 <= maxX; ++x2) {
                blocks.add((new Location(this.getWorld(), x2, minY, z2)).getBlock());
                blocks.add((new Location(this.getWorld(), x2, maxY, z2)).getBlock());
            }
        }

        return blocks;
    }

    public boolean contains(Location location) {
        return this.x1 <= location.getX() && this.x2 >= location.getX()
                && this.y1 <= location.getY() && this.y2 >= location.getY()
                && this.z1 <= location.getZ() && this.z2 >= location.getZ();
    }

    public boolean contains(Block block) {
        return contains(block.getLocation());
    }

    public boolean contains(Player player) {
        return contains(player.getLocation());
    }

    public @NotNull Iterator<Block> iterator() {
        return new CuboidIterator(this.getWorld(), this.x1, this.y1, this.z1, this.x2, this.y2, this.z2);
    }

    public CuboidUtil clone() throws CloneNotSupportedException {
        CuboidUtil clone = (CuboidUtil) super.clone();
        return new CuboidUtil(this);
    }

    public enum CuboidDirection {
        North,
        East,
        South,
        West,
        Up,
        Down,

    }

    public static class CuboidIterator implements Iterator<Block> {
        private final World w;
        private final int baseX;
        private final int baseY;
        private final int baseZ;
        private final int sizeX;
        private final int sizeY;
        private final int sizeZ;
        private int x;
        private int y;
        private int z;

        public CuboidIterator(World w, int x1, int y1, int z1, int x2, int y2, int z2) {
            this.w = w;
            this.baseX = x1;
            this.baseY = y1;
            this.baseZ = z1;
            this.sizeX = Math.abs(x2 - x1) + 1;
            this.sizeY = Math.abs(y2 - y1) + 1;
            this.sizeZ = Math.abs(z2 - z1) + 1;
            this.x = this.y = this.z = 0;
        }

        public boolean hasNext() {
            return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
        }

        public Block next() {
            Block b = this.w.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);
            if (++this.x >= this.sizeX) {
                this.x = 0;
                if (++this.y >= this.sizeY) {
                    this.y = 0;
                    ++this.z;
                }
            }

            return b;
        }
    }
}
