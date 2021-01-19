package org.BananasAmIRite.NaturalSelectionSimulation.apitest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.NaturalSelection;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.display.DisplayListener;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Speed;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Food;

import java.lang.reflect.InvocationTargetException;

public class Main extends NaturalSelection {
    public Main(Simulation simulation) {
        super(simulation);
        simulation.setCreatureClass(TraitsCreature.class);
        simulation.getTraitManager().registerTrait(Speed.class);
        DisplayListener listener = new DisplayListener(simulation.getSizeX(), simulation.getSizeY(), simulation);
        getSimulation().getEventManager().registerEventListener(listener);
    }

    @Override
    public void run() {
        runGeneration(10, 10, 2);
    }
}
