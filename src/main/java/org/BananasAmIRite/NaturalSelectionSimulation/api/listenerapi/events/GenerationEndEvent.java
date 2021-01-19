package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import java.util.List;

public class GenerationEndEvent extends GenerationEvent {
    public GenerationEndEvent(Simulation sim, int currentGeneration) {
        super(sim, currentGeneration);
    }
}
