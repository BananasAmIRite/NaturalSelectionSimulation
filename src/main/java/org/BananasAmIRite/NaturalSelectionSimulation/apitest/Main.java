package org.BananasAmIRite.NaturalSelectionSimulation.apitest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.NaturalSelection;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.SimulationStartEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Speed;

import java.lang.reflect.InvocationTargetException;

public class Main extends NaturalSelection {
    public Main(Simulation simulation) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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
