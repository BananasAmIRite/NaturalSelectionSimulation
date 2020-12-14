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

    public Trait getTrait(Class<? extends Trait> clazz) {
        for (Trait trait : data) {
            if (trait.getClass() == clazz) return trait;
        }
        return null;
    }

    public Double getTraitValue(Class<? extends Trait> clazz) {
        for (Trait trait : data) {
            if (trait.getClass() == clazz) return trait.getValue();
        }
        return null;
    }

    public void setTrait(Class<? extends Trait> trait, int value) {
        Trait t = getTrait(trait);

        if (t == null) return;

        if (t.isSettable(value)) t.setValue(value);
    }
}
