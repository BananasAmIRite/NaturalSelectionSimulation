package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import java.util.List;

public class Coordinate implements Comparable<Coordinate> {
    protected final int x;
    protected final int y;

    public static final List<Direction> DIRECTIONS = List.of(Direction.values());

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate move(Coordinate originalCoords, Direction direction, int amount) {
        return switch (direction) {
            default -> originalCoords.add(0, -(amount));
            case DOWN -> originalCoords.add(0, amount);
            case LEFT -> originalCoords.add(-(amount), 0);
            case RIGHT -> originalCoords.add(amount, 0);
        };
    }

    public Coordinate move(Direction direction, int amount) {
        return move(this, direction, amount);
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
        return new Coordinate(this.x + x, this.y + y);
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
