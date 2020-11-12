package org.BananasAmIRite.NaturalSelectionSimulation.apiTest.Traits;

import org.BananasAmIRite.NaturalSelectionSimulation.api.TraitsAPI.Trait;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;

public class Speed extends Trait {

    public Speed() {

    }

    @Override
    public Creature onValueChange(Creature creature, int value) {
        return creature;
    }

    @Override
    public int creatureReproduce(int value) {
        // no change during reproduction
        return value;
    }
}
