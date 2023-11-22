package net.kappa.boxpvp.utils.objects;

import org.bukkit.Location;

public class LocationObject {
    private final Location start;
    private Location last;

    public LocationObject(Location start) {
        this.start = start;
        this.last = start;
    }

    public Location getStart() {
        return this.start;
    }

    public Location getLast() {
        return this.last;
    }

    public void setLast(Location last) {
        this.last = last;
    }
}
