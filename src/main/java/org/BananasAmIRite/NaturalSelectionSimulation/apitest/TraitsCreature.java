package org.BananasAmIRite.NaturalSelectionSimulation.apitest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;

import java.lang.reflect.InvocationTargetException;

public class TraitsCreature extends Creature {
    public TraitsCreature(Simulation sim, int id) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super(sim, id);
    }
}
