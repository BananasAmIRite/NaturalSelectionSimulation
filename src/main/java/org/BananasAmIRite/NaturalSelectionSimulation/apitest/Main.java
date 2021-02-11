package org.BananasAmIRite.NaturalSelectionSimulation.apitest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.NaturalSelection;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.display.DisplayListener;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Endurance;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Sense;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Speed;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Food;

import java.lang.reflect.InvocationTargetException;

public class Main extends NaturalSelection {

    private Simulation sim;

    public Main(Simulation simulation) {
        super(simulation);
        this.sim = simulation;
        simulation.setCreatureClass(TraitsCreature.class);
        simulation.getTraitManager().registerTrait(Speed.class);
        simulation.getTraitManager().registerTrait(Sense.class);
        simulation.getTraitManager().registerTrait(Endurance.class);
        DisplayListener listener = new DisplayListener(simulation.getSizeX(), simulation.getSizeY(), simulation);
        getSimulation().getEventManager().registerEventListener(listener);
    }

    @Override
    public void run() {
        sim.setSimulationSpeed(0.1); // lower = faster
    }


}
