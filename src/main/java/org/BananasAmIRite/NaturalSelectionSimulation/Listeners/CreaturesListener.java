package org.BananasAmIRite.NaturalSelectionSimulation.Listeners;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.Listener;
import org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.annotations.EventHandler;
import org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.events.SimulationEndEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.events.SimulationStartEvent;

public class CreaturesListener implements Listener {

    private final Simulation simulation;

    public CreaturesListener(Simulation s) {
        this.simulation = s;
    }

    @EventHandler
    public void onSimulationEnd(SimulationEndEvent e) {
        this.simulation.getCreaturesManager().returnAllHome();
    }

    @EventHandler
    public void onSimulationStart(SimulationStartEvent e) {
        System.out.println("SIMULATION START");
    }
}
