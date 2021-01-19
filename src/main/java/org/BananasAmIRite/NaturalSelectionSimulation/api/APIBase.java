package org.BananasAmIRite.NaturalSelectionSimulation.api;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public interface APIBase {
    /**
     * Runs for 1 generation; triggers {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.GenerationStartEvent}
     */
    void runGeneration(int creatures, int foods);

    /**
     * Runs for specified amount of generations; triggers {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.GenerationStartEvent} each generation start and {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.GenerationEvent} each generation end
     */
    void runGeneration(int creatures, int foods, int generations);

    /**
     * Runs for specified amount of generations; triggers {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.GenerationStartEvent} each generation start and {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.GenerationEvent} each generation end
     *
     * @param delay the delay in milliseconds between each generation
     * */
    void runGeneration(int creatures, int foods, int generations, int delay);

    /**
     * gets the simulation
     */
    Simulation getSimulation();
}
