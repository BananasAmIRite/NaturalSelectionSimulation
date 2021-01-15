package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Entity;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import java.util.List;

public class EntityAddEvent extends SimulationUpdateEvent {

    private final Entity entity;

    public EntityAddEvent(List<List<Tile>> map, Entity entity) {
        super(map);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
