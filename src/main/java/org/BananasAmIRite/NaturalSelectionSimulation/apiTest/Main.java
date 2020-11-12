package org.BananasAmIRite.NaturalSelectionSimulation.apiTest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.events.SimulationStartEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.NaturalSelection;
import org.BananasAmIRite.NaturalSelectionSimulation.apiTest.Traits.Speed;

public class Main extends NaturalSelection {
    public Main(Simulation simulation) throws InstantiationException, IllegalAccessException {
        super(simulation);
        System.out.println("MAIN API CLASS INITIALIZED");
        getSimulation().getEventManager().fireEvent(new SimulationStartEvent());
        simulation.setCreatureClass(TraitsCreature.class);
        simulation.getTraitManager().registerTrait(Speed.class);
        TraitsCreature c = new TraitsCreature(simulation, 0);
        simulation.getCreaturesManager().returnAllHome();
        System.out.println(c);
    }
}
