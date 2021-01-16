package org.BananasAmIRite.NaturalSelectionSimulation;

import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.EntityAddEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.EntityRemoveEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.SimulationCoordinate;

import java.util.Collection;
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
        creatures.put(creature.getEntityID(), creature);
        sim.getEventManager().fireEvent(new EntityAddEvent(sim.getMap().getMap(), creature));
    }

    public void deregisterCreature(int id) {
        if (creatures.get(id) == null) return;
        creatures.get(id).removeFromMap();
        sim.getEventManager().fireEvent(new EntityRemoveEvent(sim.getMap().getMap(), creatures.get(id)));
        creatures.remove(id);
    }

    public void deregisterCreature(Creature creature) {
        creatures.remove(creature.getEntityID());
        creature.removeFromMap();
        sim.getEventManager().fireEvent(new EntityRemoveEvent(sim.getMap().getMap(), creature));
    }

    public void incrementLatestID() {
        latestID = latestID + 1;
    }

    public int getLatestID() {
        return latestID;
    }

    public void returnAllHome() {
        for (Creature value : creatures.values()) {
            value.moveToLocation(value.getHome());
        }
    }

    public SimulationCoordinate generateHome() {
        List<SimulationCoordinate> coords = sim.getMap().getSideCoords();

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

    public Collection<Creature> getCreatures() {
        return creatures.values();
    }

    public Creature getCreature(int id) {
        return creatures.get(id);
    }
}
