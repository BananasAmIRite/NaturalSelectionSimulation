package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import java.util.List;

public class CreatureRemoveEvent extends SimulationUpdateEvent {

    private final Creature creature;

    public CreatureRemoveEvent(List<List<Tile>> map, Creature creature) {
        super(map);
        this.creature = creature;
    }

    public Creature getCreature() {
        return creature;
    }
}
