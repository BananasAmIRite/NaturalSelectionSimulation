package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import java.util.List;

public class GenerationStartEvent extends GenerationEvent {

    public GenerationStartEvent(Simulation sim, int currentGen) {
        super(sim, currentGen);
    }
}
