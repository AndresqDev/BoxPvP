package net.kappa.boxpvp.utils.objects;

import net.kappa.boxpvp.utils.CuboidUtil;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class MineObject {
    private final List<Material> types;
    private String name;
    private CuboidUtil cuboid;
    private int time;

    public MineObject(String name, CuboidUtil cuboid, int time, List<String> types){
        this.types = new ArrayList<>();
        this.name = name;
        this.cuboid = cuboid;
        this.time = time;
        types.forEach(t -> this.types.add(Material.getMaterial(t)));
    }

    public String getName() {
        return this.name;
    }

    public CuboidUtil getCuboid() {
        return this.cuboid;
    }

    public int getTime(){
        return this.time;
    }

    public List<Material> getTypes() {
        return this.types;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuboid(CuboidUtil cuboid) {
        this.cuboid = cuboid;
    }
}
