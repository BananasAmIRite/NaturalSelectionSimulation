package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import java.util.List;

public class SimulationUpdateEvent extends SimulationEvent {

    private final List<List<Tile>> map;

    public SimulationUpdateEvent(List<List<Tile>> map) {
        this.map = map;
    }

    public List<List<Tile>> getMap() {
        return map;
    }
}
