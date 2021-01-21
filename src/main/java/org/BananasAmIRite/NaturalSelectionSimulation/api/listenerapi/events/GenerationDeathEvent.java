package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public class GenerationDeathEvent extends GenerationEvent {

    public GenerationDeathEvent(Simulation sim, int currentGen) {
        super(sim, currentGen);
    }
}
