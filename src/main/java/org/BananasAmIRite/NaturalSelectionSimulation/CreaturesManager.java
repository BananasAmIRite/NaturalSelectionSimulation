package org.BananasAmIRite.NaturalSelectionSimulation;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Coordinate;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;

import java.util.HashMap;
import java.util.List;

public class CreaturesManager {
    private final HashMap<Integer, Creature> creatures = new HashMap<>();
    private final Simulation sim;
    private int latestID = 0;

    public CreaturesManager(Simulation sim) {
        this.sim = sim;
    }

    public void registerCreature(Creature creature) {
        if (creature.getClass() != sim.getCreatureClass()) return;
        sim.setFirstStarted(true);
        creatures.put(creature.getCreatureID(), creature);
    }

    public void deregisterCreature(int id) {
        creatures.remove(id);
    }

    public void deregisterCreature(Creature creature) {
        creatures.remove(creature.getCreatureID());
    }

    public void incrementLatestID() {
        latestID = latestID + 1;
    }

    public int getLatestID() {
        return latestID;
    }

    // TODO: make creatures from creature manager have a home to return to (at border of map)

    public void returnAllHome() {
        for (Creature value : creatures.values()) {
            value.setLocation(value.getHome());
        }
    }

    public Coordinate generateHome() {
        List<Coordinate> coords = sim.getMap().getSideCoords();

        return coords.get((int) Math.floor(Math.random() * coords.size()));
    }

    public void pauseAll() {
        for (Creature creature : creatures.values()) {
            creature.pause();
        }
    }

    public void playAll() {
        for (Creature creature : creatures.values()) {
            creature.play();
        }
    }
}
