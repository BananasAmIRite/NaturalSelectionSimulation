package org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractTraitManager {

    /**
     * Registers a trait; Traits cannot be registered after the simulation has first started to avoid inconsistencies between creatures
     *
     * @throws IllegalStateException if attempting to register traits after one has been created
     */
    public abstract void registerTrait(Class<? extends Trait> trait) throws IllegalStateException;

    /**
     * Adds all of the currently available traits to the specified creature
     *
     * */
    public abstract void addToCreature(Creature c) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
