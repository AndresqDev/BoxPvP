package net.kappa.boxpvp.utils.objects;

import net.kappa.boxpvp.utils.Cuboid;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class MineObject {
    private final List<Material> types;
    private String name;
    private Cuboid cuboid;
    private int time;

    public MineObject(String name, Cuboid cuboid, int time, List<String> types){
        this.types = new ArrayList<>();
        this.name = name;
        this.cuboid = cuboid;
        this.time = time;
        types.forEach(t -> this.types.add(Material.getMaterial(t)));
    }

    public String getName() {
        return this.name;
    }

    public Cuboid getCuboid() {
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

    public void setCuboid(Cuboid cuboid) {
        this.cuboid = cuboid;
    }
}
