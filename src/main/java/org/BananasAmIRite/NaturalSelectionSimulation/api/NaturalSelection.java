package org.BananasAmIRite.NaturalSelectionSimulation.api;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public abstract class NaturalSelection extends Thread implements APIBase {

    private final Simulation simulation;

    public NaturalSelection(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public final void runGeneration(int creatures, int foods) {
        runGeneration(creatures, foods, 1);
    }

    @Override
    public final void runGeneration(int creatures, int foods, int generations) {
        runGeneration(creatures, foods, generations, 2000);
    }

    @Override
    public final void runGeneration(int creatures, int foods, int generations, int delay) {
        simulation.getGenerationManager().startGeneration(creatures, foods, generations, delay);
    }

    @Override
    public final Simulation getSimulation() {
        return simulation;
    }

    @Override
    public String toString() {
        return "NaturalSelection{" +
                "simulation=" + simulation +
                '}';
    }
}
