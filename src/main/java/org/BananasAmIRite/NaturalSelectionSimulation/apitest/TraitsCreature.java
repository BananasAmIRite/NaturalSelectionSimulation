package org.BananasAmIRite.NaturalSelectionSimulation.apitest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.NumberUtils;

import java.lang.reflect.InvocationTargetException;

public class TraitsCreature extends Creature {

    private long waitTime;

    public TraitsCreature(Simulation sim, int id) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super(sim, id);

        waitTime = (long) NumberUtils.randDouble(1, 2, this.sim.getMap().getMapRandom());
    }

    @Override
    protected long calculateWaitTime() {
        return waitTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
