package org.BananasAmIRite.NaturalSelectionSimulation.api;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public interface APIBase {
    /**
     * Runs for 1 generation; triggers {@link org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.events.SimulationStartEvent}
     */
    void run();

    /**
     * Runs for specified amount of generations; triggers {@link org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.events.SimulationStartEvent} each generation start and {@link org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.events.SimulationEndEvent} each generation end
     */
    void run(int generations);

    /**
     * Runs for specified amount of generations with timeout; triggers {@link org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.events.SimulationStartEvent} each generation start and {@link org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.events.SimulationEndEvent} each generation end
     */
    void run(int generations, int timeout);

    /**
     * gets the simulation
     */
    Simulation getSimulation();
}
