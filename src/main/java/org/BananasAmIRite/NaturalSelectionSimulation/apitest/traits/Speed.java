package org.BananasAmIRite.NaturalSelectionSimulation.apitest.traits;

import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Trait;

public class Speed extends Trait {

    public Speed() {

    }

    @Override
    public double creatureReproduce(double value) {
        // no change during reproduction
        return value;
    }

    @Override
    protected void setValues() {

    }
}
