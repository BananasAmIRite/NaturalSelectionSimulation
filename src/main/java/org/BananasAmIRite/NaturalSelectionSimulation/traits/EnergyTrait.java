package org.BananasAmIRite.NaturalSelectionSimulation.traits;

import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Trait;

public class EnergyTrait extends Trait {

    public EnergyTrait() {
    }

    @Override
    public double creatureReproduce(double value) {
        return value;
    }

    @Override
    protected void setValues() {
        setDefaultValue(400);
        setLowestValue(0);
    }
}
