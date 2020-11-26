package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Coordinate;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import java.util.List;

public class CreatureMoveEvent extends SimulationUpdateEvent {

    private final Creature movedCreature;
    private final Coordinate from;
    private final Coordinate to;

    public CreatureMoveEvent(List<List<Tile>> map, Creature movedCreature, Coordinate from, Coordinate to) {
        super(map);
        this.movedCreature = movedCreature;
        this.from = from;
        this.to = to;
    }

    public Creature getMovedCreature() {
        return movedCreature;
    }

    public Coordinate getFrom() {
        return from;
    }

    public Coordinate getTo() {
        return to;
    }
}
