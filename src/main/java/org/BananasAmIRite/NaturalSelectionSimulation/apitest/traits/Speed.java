package org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits;

import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Trait;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.utils.TraitUtils;

public class Speed extends Trait {

    public Speed() {

    }

    @Override
    public double creatureReproduce(double value) {
        double nv = TraitUtils.getReproductionValue(-3, 3, value);
        return !isSettable(nv) ? creatureReproduce(value) : nv;
    }

    @Override
    protected void setValues() {
        setHighestValue(10);
        setLowestValue(1);
        setDefaultValue(9);
    }
}
