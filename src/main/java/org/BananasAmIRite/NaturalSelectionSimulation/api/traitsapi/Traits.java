package org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Traits {
    private final List<Trait> data = new ArrayList<>();

    public Traits() {

    }

    public void addTrait(Class<? extends Trait> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Trait t = clazz.getDeclaredConstructor().newInstance();
        data.add(t);
    }

    public List<Trait> getTraits() {
        return data;
    }

    public Double getTrait(Class<? extends Trait> clazz) {
        for (Trait trait : data) {
            if (trait.getClass() == clazz) return trait.getValue();
        }
        return null;
    }

    public void setTrait(Trait trait, int value) {
        if (trait.isSettable(value)) trait.setValue(value);
    }
}
