package org.BananasAmIRite.NaturalSelectionSimulation.utils;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Coordinate;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.SimulationCoordinate;

public class CoordinateUtils {
    /**
     * Utility function to find an ideal path to go from location 1 to location 2
     *
     * @param loc1 Location to go from
     * @param loc2 Location to go to
     *
     * */
    public static Coordinate pathFind(Coordinate loc1, Coordinate loc2) {
        return new Coordinate(loc2.getX()-loc1.getX(), loc2.getY()-loc1.getY());
    }

    public static boolean isCoordinatesSafe(Simulation simulation, Coordinate coords) {
        return coords.getX() >= 0 && (coords.getX() <= simulation.getSizeX() - 1) && coords.getY() >= 0 && (coords.getY() <= simulation.getSizeY() - 1);
    }

    public static boolean isCoordinatesSafe(SimulationCoordinate coords) {
        return coords.getX() >= 0 && (coords.getX() <= coords.getSimulation().getSizeX() - 1) && coords.getY() >= 0 && (coords.getY() <= coords.getSimulation().getSizeY() - 1);
    }

    public static int pathfindNextDirection(Coordinate loc1, Coordinate loc2) {
        Coordinate c = pathFind(loc1, loc2);
        if (c.getX() != 0) {
            if (c.getX() > 0) {
                return Coordinate.Direction.RIGHT;
            } else return Coordinate.Direction.LEFT;
        } else if (c.getY() != 0) {
            if (c.getY() > 0) {
                return Coordinate.Direction.DOWN;
            } else return Coordinate.Direction.UP;
        }
        return 0; // no direction (same location so dont need to go anywhere)
    }
}
