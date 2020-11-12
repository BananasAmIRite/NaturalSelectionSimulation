package org.BananasAmIRite.NaturalSelectionSimulation;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Coordinate;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationMap {
    private List<List<Tile>> map = new ArrayList<>();
    private List<Coordinate> sideCoords = new ArrayList<>();

    private final int rows;
    private final int cols;
    private final Simulation sim;

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
    public static <T> List<List<T>> createMap(Class<T> clazz, int rows, int columns) throws IllegalAccessException, InstantiationException {
        List<List<T>> map = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<T> l = new ArrayList<>();

            for (int j = 0; j < columns; j++) {
                l.add(clazz.newInstance());
            }

            map.add(l);
        }
        return map;
    }

    private void generateMap(int rows, int columns) throws InstantiationException, IllegalAccessException {
        map = createMap(Tile.class, rows, columns);
    }

    /**
     * @return if the creature was successfully moved
     */
    public boolean changeCreatureLocation(Creature creature, Coordinate before, Coordinate after) {
        if (after.getX() >= cols || after.getY() >= rows) {
            return false;
        }

        if (before != null) {
            try {
                map.get(after.getY());
                map.get(after.getY()).get(after.getX());
                map.get(before.getY());
                map.get(before.getY()).get(before.getX());
            } catch (Exception e) {
                return false;
            } // further prove that the coords are safe
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

        if (after.equals(before)) {
            return false;
        }

        return true;
    }

    private void fillCoordsCache() {
        // top row
        for (int i = 0; i < this.cols; i++) {
            sideCoords.add(new Coordinate(i, 0));
        }

        // bottom row
        for (int i = 0; i < this.cols; i++) {
            sideCoords.add(new Coordinate(i, this.rows - 1));
        }

        // left col
        for (int i = 0; i < this.rows; i++) {
            sideCoords.add(new Coordinate(0, i));
        }

        // right col
        for (int i = 0; i < this.rows; i++) {
            sideCoords.add(new Coordinate(this.cols - 1, i));
        }

        sideCoords = sideCoords.stream().distinct().collect(Collectors.toList());
    }

    public List<Coordinate> getSideCoords() {
        return sideCoords;
    }

    public List<List<Tile>> getMap() {
        return map;
    }
}