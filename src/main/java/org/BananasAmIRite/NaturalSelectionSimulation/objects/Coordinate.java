package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public class Coordinate implements Comparable<Coordinate> {
    private final int x;
    private final int y;
    private Simulation simulation;

    public Coordinate(Simulation sim, int x, int y) {
        this.simulation = sim;
        this.x = x;
        this.y = y;
    }

    public static Coordinate move(Coordinate originalCoords, Direction direction, int amount) {
        switch (direction) {
            case UP:
            default:
                return originalCoords.add(0, -(amount));
            case DOWN:
                return originalCoords.add(0, amount);
            case LEFT:
                return originalCoords.add(-(amount), 0);
            case RIGHT:
                return originalCoords.add(amount, 0);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;
        return hashCode() == obj.hashCode();
    }

    @Override
    public int compareTo(Coordinate o) {
        return (hashCode() == o.hashCode()) ? 1 : 0;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(Integer.toString(this.x).concat(Integer.toString(this.y)));
    }

    public Coordinate add(int x, int y) {
        Coordinate coords = new Coordinate(this.simulation, this.x + x, this.y + y);
        ;

        if (coords.getX() < 0) coords = new Coordinate(this.simulation, 0, coords.getY());
        if (coords.getX() > simulation.getSizeX() - 1)
            coords = new Coordinate(this.simulation, simulation.getSizeX() - 1, coords.getY());
        if (coords.getY() < 0) coords = new Coordinate(this.simulation, coords.getX(), 0);
        if (coords.getY() > simulation.getSizeY() - 1)
            coords = new Coordinate(this.simulation, coords.getX(), simulation.getSizeY() - 1);
        return coords;
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
