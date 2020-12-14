package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    private final List<Entity> creatures = new ArrayList<>();

    private SimulationCoordinate coords;

    public Tile(SimulationCoordinate coords) {
        this.coords = coords;
    }

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
                ", coords=" + coords +
                '}';
    }

    public static String translateTileToString(Tile t) {
        StringBuilder builder = new StringBuilder();
        for (Entity creature : t.creatures) {
            builder.append(creature.getEntityAbbreviation()).append(creature.getEntityID()).append("|");
        }
        return builder.toString();
    }
}
