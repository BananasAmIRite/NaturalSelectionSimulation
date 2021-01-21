package org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits;

import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Trait;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.utils.TraitUtils;

public class Endurance extends Trait {

    public Endurance() {

    }

    @Override
    public double creatureReproduce(double value) {
        double nv = TraitUtils.getReproductionValue(-1.5, 1.5, value); // up to 1.5 units variation
        return !isSettable(nv) ? creatureReproduce(value) : nv;
    }

    @Override
    protected void setValues() {
        setLowestValue(0);
        setDefaultValue(3);
    }
}
