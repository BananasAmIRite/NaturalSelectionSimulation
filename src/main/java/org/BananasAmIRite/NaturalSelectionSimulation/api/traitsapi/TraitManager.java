package org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;

import java.util.ArrayList;
import java.util.List;

public class TraitManager extends AbstractTraitManager {

    private final Simulation simulation;
    private final List<Class<? extends Trait>> traits;

    public TraitManager(Simulation simulation) {
        this.simulation = simulation;
        this.traits = new ArrayList<>();
    }

    @Override
    public void registerTrait(Class<? extends Trait> trait) throws IllegalStateException {
        if (simulation.isFirstStarted())
            throw new IllegalStateException("Trait cannot be registered after a creature has been created!");
        traits.add(trait);
    }

    public void addToCreature(Creature c) throws InstantiationException, IllegalAccessException {
        for (Class<? extends Trait> trait : traits) {
            c.getTraits().addTrait(trait);
        }
    }
}
