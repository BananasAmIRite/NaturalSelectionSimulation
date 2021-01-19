package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public class Food extends Entity {

    private static int FOOD_ID = 0;

    private final int id;

    /**
     * Class representing a food. Has almost no mechanisms.
     *
     * */
    public Food(Simulation sim) {
        super(sim);

        setLocationNew(SimulationCoordinate.randomCoordinate(sim, 1,  sim.getSizeX() - 1, 1, sim.getSizeY() - 1));

        id = FOOD_ID;

        sim.getFoodManager().registerFood(this);

        FOOD_ID++;
    }

    @Override
    public int getEntityID() {
        return id;
    }

    @Override
    public String getEntityAbbreviation() {
        return "f";
    }

    @Override
    public boolean showEntityID() {
        return false;
    }

    public void remove() {
        sim.getMap().getMap().get(getLocation().getY()).get(getLocation().getX()).removeEntity(this);
        sim.getFoodManager().deregisterFood(getEntityID());
    }
}
