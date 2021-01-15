package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Coordinate;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Entity;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import java.util.List;

public class EntityMoveEvent extends SimulationUpdateEvent {

    private final Entity movedEntity;
    private final Coordinate from;
    private final Coordinate to;

    public EntityMoveEvent(List<List<Tile>> map, Entity movedCreature, Coordinate from, Coordinate to) {
        super(map);
        this.movedEntity = movedCreature;
        this.from = from;
        this.to = to;
    }

    public Entity getMovedEntity() {
        return movedEntity;
    }

    public Coordinate getFrom() {
        return from;
    }

    public Coordinate getTo() {
        return to;
    }
}
