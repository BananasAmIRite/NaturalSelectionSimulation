package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.Event;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import java.util.List;

public class GenerationEvent extends Event {

    private final Simulation simulation;
    private final int currentGeneration;

    public GenerationEvent(Simulation sim, int currentGeneration) {
        this.simulation = sim;
        this.currentGeneration = currentGeneration;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public int getCurrentGeneration() {
        return currentGeneration;
    }
}
