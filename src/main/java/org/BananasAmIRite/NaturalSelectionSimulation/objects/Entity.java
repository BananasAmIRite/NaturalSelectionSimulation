package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.EntityAddEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.EntityMoveEvent;

public abstract class Entity {

    protected SimulationCoordinate location;
    protected Simulation sim;

    public Entity(Simulation sim) {
        this.sim = sim;
    }
    
    /**
     * Gets the ID of the entity; each one has a unique ID
     *
     * */
    abstract int getEntityID();

    /**
     * Gets the entity abbreviation
     *
     * */
    abstract String getEntityAbbreviation();

    /**
     * sets if the entity is allowed to have its entity id shown
     *
     * */
    abstract boolean showEntityID();

    /**
     * Gets the current stored location of the entity
     *
     * */
    public final SimulationCoordinate getLocation() {
        return location;
    }

    /**
     * Sets the current location of the entity
     * NOTE: This does not fire an {@link EntityMoveEvent}, please refer to {@link this#moveToLocation(SimulationCoordinate)} for one that fires an event
     *
     * */
    public final boolean setLocation(SimulationCoordinate location) {
        if (!sim.getMap().changeEntityLocation(this, this.location, location)) return false;
        this.location = location;

        return true;
    }

    /**
     * Used when setting the location before the location has been successfully set
     *
     * */
    public final boolean setLocationNew(SimulationCoordinate c) {
        boolean l = setLocation(c);
        if (l) sim.getEventManager().fireEvent(new EntityAddEvent(sim.getMap().getMap(), this));

        return l;
    }

    /**
     * Sets the current location of the entity and fires a {@link EntityMoveEvent}
     * Please refer to {@link this#setLocation(SimulationCoordinate)} for one that does not fire an event
     *
     * */
    public final boolean moveToLocation(SimulationCoordinate c) {
        SimulationCoordinate oldCoords = getLocation();
        boolean l = setLocation(c);
        if (l) sim.getEventManager().fireEvent(new EntityMoveEvent(sim.getMap().getMap(), this, oldCoords, c));

        return l;
    }
}
