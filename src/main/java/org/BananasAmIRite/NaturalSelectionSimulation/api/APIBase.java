package org.BananasAmIRite.NaturalSelectionSimulation.api;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public interface APIBase {
    /**
     * Runs for 1 generation; triggers {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.SimulationStartEvent}
     */
    void runGeneration(int creatures, int foods);

    /**
     * Runs for specified amount of generations; triggers {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.SimulationStartEvent} each generation start and {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.SimulationEndEvent} each generation end
     */
    void runGeneration(int creatures, int foods, int generations);

    /**
     * gets the simulation
     */
    Simulation getSimulation();
}
