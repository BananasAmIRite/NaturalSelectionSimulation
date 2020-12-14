package org.BananasAmIRite.NaturalSelectionSimulation.apitest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.NaturalSelection;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Speed;

import java.lang.reflect.InvocationTargetException;

public class Main extends NaturalSelection {
    public Main(Simulation simulation) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        super(simulation);
        System.out.println("MAIN API CLASS INITIALIZED");
        simulation.setCreatureClass(TraitsCreature.class);
        simulation.getTraitManager().registerTrait(Speed.class);

        DisplayListener listener = new DisplayListener(simulation.getSizeX(), simulation.getSizeY(), simulation);

        getSimulation().getEventManager().registerEventListener(listener);

        for (int i = 0; i < 100; i++) {
            TraitsCreature c = new TraitsCreature(simulation, i);
            c.start();
        }

        simulation.getCreaturesManager().returnAllHome();
    }
}
