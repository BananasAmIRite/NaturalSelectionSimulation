package org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi;

import java.util.HashMap;

public class Traits {
    private final HashMap<Trait, Integer> data = new HashMap<>();

    public Traits() {

    }

    public void addTrait(Class<? extends Trait> clazz) throws IllegalAccessException, InstantiationException {
        Trait t = clazz.newInstance();
        data.put(t, t.getDefaultValue());
    }

    public HashMap<Trait, Integer> getTraits() {
        return data;
    }
}
