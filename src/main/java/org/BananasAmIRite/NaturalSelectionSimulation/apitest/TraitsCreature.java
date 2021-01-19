package org.BananasAmIRite.NaturalSelectionSimulation.apitest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Traits;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Speed;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.NumberUtils;

import java.lang.reflect.InvocationTargetException;

public class TraitsCreature extends Creature {

    private long waitTime;

    public TraitsCreature(Simulation sim) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super(sim);

        waitTime = (long) getTraits().getTrait(Speed.class).getValue();
    }

    @Override
    protected long calculateWaitTime() {
        return (long) NumberUtils.invertNumber(getTraits().getTrait(Speed.class).getLowestValue(), getTraits().getTrait(Speed.class).getHighestValue(), waitTime);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
