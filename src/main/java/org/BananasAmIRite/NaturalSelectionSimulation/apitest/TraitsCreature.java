package org.BananasAmIRite.NaturalSelectionSimulation.apitest;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Traits;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Endurance;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Sense;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits.Speed;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.NumberUtils;

import java.lang.reflect.InvocationTargetException;

public class TraitsCreature extends Creature {

    public TraitsCreature(Simulation sim) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super(sim);
    }

    @Override
    protected long calculateWaitTime() {
        return (long) NumberUtils.invertNumber(getTraits().getTrait(Speed.class).getLowestValue(), getTraits().getTrait(Speed.class).getHighestValue(), getTraits().getTraitValue(Speed.class));
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected int getEnergyPerStep() {
        return Math.max(1, (super.getEnergyPerStep() + (int) (getTraits().getTraitValue(Speed.class) / 1000)) - (int) getTraits().getTraitValue(Endurance.class).doubleValue()); // minimum energy used is 1 energy
    }

    @Override
    public int getSensingRange() {
        return (int) getTraits().getTraitValue(Sense.class).doubleValue();
    }
}
