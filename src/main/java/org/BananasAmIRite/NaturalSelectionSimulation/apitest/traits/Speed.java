package org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits;

import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Trait;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.utils.TraitUtils;

public class Speed extends Trait {

    public Speed() {

    }

    @Override
    public double creatureReproduce(double value) {
        double nv = TraitUtils.getReproductionValue(-1000, 1000, value); // up to 1 second variation
        return !isSettable(nv) ? creatureReproduce(value) : nv;
    }

    @Override
    protected void setValues() {
        setHighestValue(10000);
        setLowestValue(1000);
        setDefaultValue(5000);
    }

    @Override
    public double getDisplayedValue() {
        return getValue() / 1000;
    }
}
