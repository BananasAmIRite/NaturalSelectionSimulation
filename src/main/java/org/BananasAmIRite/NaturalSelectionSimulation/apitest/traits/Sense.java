package org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits;

import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Trait;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.utils.TraitUtils;

public class Sense extends Trait {

    public Sense() {

    }

    @Override
    public double creatureReproduce(double value) {
        double nv = TraitUtils.getReproductionValue(-2, 2, value); // up to 2 units variation
        return !isSettable(nv) ? creatureReproduce(value) : nv;
    }

    @Override
    protected void setValues() {
        setLowestValue(0);
        setDefaultValue(5);
    }
}
