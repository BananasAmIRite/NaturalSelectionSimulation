package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public class GenerationEndBeforeCleanupEvent extends GenerationEvent {
    public GenerationEndBeforeCleanupEvent(Simulation sim, int currentGeneration) {
        super(sim, currentGeneration);
    }
}
