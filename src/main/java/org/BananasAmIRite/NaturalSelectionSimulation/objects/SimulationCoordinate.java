package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.NumberUtils;

public class SimulationCoordinate extends Coordinate {
    private final Simulation simulation;
    public SimulationCoordinate(Simulation sim, int x, int y) {
        super(x, y);
        this.simulation = sim;
    }

    @Override
    public SimulationCoordinate add(int x, int y) {
        SimulationCoordinate coords = new SimulationCoordinate(this.simulation, this.x + x, this.y + y);

        if (coords.getX() < 0) coords = new SimulationCoordinate(this.simulation, 0, coords.getY());
        if (coords.getX() > simulation.getSizeX() - 1)
            coords = new SimulationCoordinate(this.simulation, simulation.getSizeX() - 1, coords.getY());
        if (coords.getY() < 0) coords = new SimulationCoordinate(this.simulation, coords.getX(), 0);
        if (coords.getY() > simulation.getSizeY() - 1)
            coords = new SimulationCoordinate(this.simulation, coords.getX(), simulation.getSizeY() - 1);

        return coords;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public static SimulationCoordinate asSimulationCoord(Coordinate e, Simulation sim) {
        return new SimulationCoordinate(sim, e.getX(), e.getY());
    }

    @Override
    public SimulationCoordinate move(int direction, int amount) {
        Coordinate coord = move(this, direction, amount);

        return asSimulationCoord(coord, getSimulation());
    }

    /**
     * Generates a random coordinate within the simulation bounds
     *
     * */
    public static SimulationCoordinate randomCoordinate(Simulation sim) {
        return new SimulationCoordinate(sim,
                NumberUtils.randInt(0, sim.getSizeX()),
                NumberUtils.randInt(0, sim.getSizeY())
        );
    }

    /**
     * Generates a random coordinate within the specified bounds
     *
     * */
    public static SimulationCoordinate randomCoordinate(Simulation sim, int xBL, int xBH, int hBL, int hBH) {
        return new SimulationCoordinate(sim,
                NumberUtils.randInt(Math.max(xBL, 0), Math.min(sim.getSizeX(), xBH)),
                NumberUtils.randInt(Math.max(hBL, 0), Math.min(sim.getSizeY(), hBH))
        );
    }

    @Override
    public String toString() {
        return "SimulationCoordinate[" + x + ", " + y + "]";
    }
}
