package org.BananasAmIRite.NaturalSelectionSimulation.objects;

public class Coordinate implements Comparable<Coordinate> {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
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
}
