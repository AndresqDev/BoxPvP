package net.kappa.boxpvp.utils.objects;

import net.kappa.boxpvp.utils.CuboidUtil;

public class ClaimObject {
    private String name;
    private CuboidUtil cuboid;
    private final boolean pvp;
    private final boolean mine;

    public ClaimObject(String name, CuboidUtil cuboid, boolean pvp, boolean mine){
        this.name = name;
        this.cuboid = cuboid;
        this.pvp = pvp;
        this.mine = mine;
    }

    public String getName() {
        return this.name;
    }

    public CuboidUtil getCuboid() {
        return this.cuboid;
    }

    public boolean isPvP(){
        return this.pvp;
    }

    public boolean isMine() {
        return this.mine;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuboid(CuboidUtil cuboid) {
        this.cuboid = cuboid;
    }
}
