package org.BananasAmIRite.NaturalSelectionSimulation.traits;

import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Trait;

public class EnergyTrait extends Trait {

    public EnergyTrait() {
    }

    @Override
    public double creatureReproduce(double value) {
        return 0;
    }

    @Override
    public void setValues() {
        setDefaultValue(400);
        setLowestValue(0);
    }
}
