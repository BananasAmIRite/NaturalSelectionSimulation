package org.BananasAmIRite.NaturalSelectionSimulation.apitest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.NaturalSelection;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.display.DisplayListener;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Speed;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Food;

import java.lang.reflect.InvocationTargetException;

public class Main extends NaturalSelection {
    public Main(Simulation simulation) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        super(simulation);
        System.out.println("API Class Initialized");
        simulation.setCreatureClass(TraitsCreature.class);
        simulation.getTraitManager().registerTrait(Speed.class);

        DisplayListener listener = new DisplayListener(simulation.getSizeX(), simulation.getSizeY(), simulation);

        getSimulation().getEventManager().registerEventListener(listener);

        for (int i = 0; i < 10; i++) {
            TraitsCreature c = new TraitsCreature(simulation, i);
            c.getThread().start();
        }

        for (int i = 0; i < 10;  i++) {
            Food f = new Food(simulation);
        }

        simulation.getCreaturesManager().returnAllHome();
    }
}
