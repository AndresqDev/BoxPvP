package net.kappa.boxpvp.utils.objects;

import org.bukkit.Location;

public class LocationObject {
    private Location start;
    private Location last;

    public Location getStart() {
        return this.start;
    }

    public Location getLast() {
        return this.last;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public void setLast(Location last) {
        this.last = last;
    }
}
