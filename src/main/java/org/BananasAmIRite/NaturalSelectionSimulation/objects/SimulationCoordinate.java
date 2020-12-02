package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public class SimulationCoordinate extends Coordinate {
    private final Simulation simulation;
    public SimulationCoordinate(Simulation sim, int x, int y) {
        super(x, y);
        this.simulation = sim;
    }

    @Override
    public Coordinate add(int x, int y) {
        Coordinate coords = new SimulationCoordinate(this.simulation, this.x + x, this.y + y);

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
}
