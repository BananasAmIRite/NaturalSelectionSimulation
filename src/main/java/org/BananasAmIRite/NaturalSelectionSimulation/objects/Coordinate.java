package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import java.util.Arrays;
import java.util.List;

public class Coordinate implements Comparable<Coordinate> {
    protected final int x;
    protected final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate move(Coordinate originalCoords, int direction, int amount) {
        return switch (direction) {
            default -> originalCoords.add(0, -(amount));
            case Direction.DOWN -> originalCoords.add(0, amount);
            case Direction.LEFT -> originalCoords.add(-(amount), 0);
            case Direction.RIGHT -> originalCoords.add(amount, 0);
        };
    }

    public Coordinate move(int direction, int amount) {
        return move(this, direction, amount);
    }

    public Coordinate moveCoords(int x, int y) {
        return new Coordinate(this.x + x, this.y + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinate[" + x + ", " + y + ']';
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

    public static class Direction {
        public final static int UP = 1;
        public final static int DOWN = 2;
        public final static int LEFT = 3;
        public final static int RIGHT = 4;

        public static List<Integer> getDirections() {
            return Arrays.asList(UP, DOWN, LEFT, RIGHT);
        }
    }
}
