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
        String[] arr = new String[t.creatures.size()];
        for (int i = 0; i < t.creatures.size(); i++) {
            Entity creature = t.creatures.get(i);
            if (creature == null) continue;
            arr[i] = creature.getEntityAbbreviation() + creature.getEntityID();
        }
        return String.join("|", arr);
    }
}
