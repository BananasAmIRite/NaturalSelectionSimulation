package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tile {

    private final List<Entity> creatures = new CopyOnWriteArrayList<>();

    private SimulationCoordinate coords;

    public Tile(SimulationCoordinate coords) {
        this.coords = coords;
    }

    synchronized public boolean addCreature(Entity entity) {
        if (creatures.contains(entity)) return false;
        creatures.add(entity);
        return true;
    }

    synchronized public boolean removeCreature(Entity entity) {
        if (!creatures.contains(entity)) return false;
        creatures.remove(entity);
        return true;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "creatures=" + creatures +
                ", coords=" + coords +
                '}';
    }

    public static String translateTileToString(Tile t) {
        StringBuilder builder = new StringBuilder();
        for (Entity creature : t.creatures) {
            if (creature == null) continue;
            builder.append(creature.getEntityAbbreviation()).append(creature.getEntityID()).append("|");
        }
        return builder.toString();
    }
}
