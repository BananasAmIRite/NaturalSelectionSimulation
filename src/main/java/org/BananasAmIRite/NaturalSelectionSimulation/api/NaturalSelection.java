package org.BananasAmIRite.NaturalSelectionSimulation.api;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public abstract class NaturalSelection implements APIBase {

    private final Simulation simulation;

    public NaturalSelection(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public final void run() {

    }

    @Override
    public final void run(int generations) {

    }

    @Override
    public final void run(int generations, int timeout) {

    }

    @Override
    public final Simulation getSimulation() {
        return simulation;
    }
}
