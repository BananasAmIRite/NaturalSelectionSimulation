package org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi;

import java.util.List;

public abstract class AbstractTraitManager {
    List<Trait> traits;

    /**
     * Registers a trait; Traits cannot be registered after the simulation has first started to avoid inconsistencies between creatures
     *
     * @throws IllegalStateException
     */
    public abstract void registerTrait(Class<? extends Trait> trait) throws IllegalStateException;

}
