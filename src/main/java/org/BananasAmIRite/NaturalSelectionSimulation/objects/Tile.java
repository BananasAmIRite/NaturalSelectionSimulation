package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    private final List<Entity> creatures = new ArrayList<>();

    public boolean addCreature(Entity entity) {
        if (creatures.contains(entity)) return false;
        creatures.add(entity);
        return true;
    }

    public boolean removeCreature(Entity entity) {
        if (!creatures.contains(entity)) return false;
        creatures.remove(entity);
        return true;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "creatures=" + creatures +
                '}';
    }
}
