package org.BananasAmIRite.NaturalSelectionSimulation;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Coordinate;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.SimulationCoordinate;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.CoordinateUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationMap {
    private int rows;
    private int cols;
    private final Simulation sim;
    private List<List<Tile>> map = new ArrayList<>();
    private List<SimulationCoordinate> sideCoords = new ArrayList<>();

    public SimulationMap(Simulation sim, int rows, int columns) {
        this.rows = rows;
        this.cols = columns;
        this.sim = sim;
        try {
            generateMap(rows, columns);
        } catch (Exception e) {
            System.out.println("Error when generating map: " + e.getMessage());
            e.printStackTrace();
        }
        fillCoordsCache();
    }

    /**
     * Creates a map (3d {@link List}) with the class provided
     *
     * @return a new map
     */
    public static <T> List<List<T>> createMap(Class<T> clazz, int rows, int columns, Simulation sim) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<List<T>> map = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<T> l = new ArrayList<>();

            for (int j = 0; j < columns; j++) {
                l.add(clazz.getDeclaredConstructor(SimulationCoordinate.class).newInstance(new SimulationCoordinate(sim, i, j)));
            }

            map.add(l);
        }
        return map;
    }

    private void generateMap(int rows, int columns) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        map = createMap(Tile.class, rows, columns, sim);
    }

    /**
     * @return if the creature was successfully moved
     */
    public boolean changeCreatureLocation(Creature creature, Coordinate before, Coordinate after) {
        if (after.getX() >= cols || after.getY() >= rows) {
            return false;
        }

        if (before != null) {
            if (!CoordinateUtils.isCoordinatesSafe(sim, before) || !CoordinateUtils.isCoordinatesSafe(sim, after)) return false;
        }

        List<List<Tile>> backupMap = map; // restore if something goes wrong

        if (before != null) {
            if (!map.get(before.getY()).get(before.getX()).removeCreature(creature)) {
                map = backupMap;
                return false;
            }
        }

        if (!map.get(after.getY()).get(after.getX()).addCreature(creature)) {
            map = backupMap;
            return false;
        }

        return !after.equals(before);
    }

    private void fillCoordsCache() {
        // top row
        for (int i = 0; i < this.cols; i++) {
            sideCoords.add(new SimulationCoordinate(sim, i, 0));
        }

        // bottom row
        for (int i = 0; i < this.cols; i++) {
            sideCoords.add(new SimulationCoordinate(sim, i, this.rows - 1));
        }

        // left col
        for (int i = 0; i < this.rows; i++) {
            sideCoords.add(new SimulationCoordinate(sim, 0, i));
        }

        // right col
        for (int i = 0; i < this.rows; i++) {
            sideCoords.add(new SimulationCoordinate(sim, this.cols - 1, i));
        }

        sideCoords = sideCoords.stream().distinct().collect(Collectors.toList());
    }

    public List<SimulationCoordinate> getSideCoords() {
        return sideCoords;
    }

    public List<List<Tile>> getMap() {
        return map;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}