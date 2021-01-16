package org.BananasAmIRite.NaturalSelectionSimulation.objects;

public class Pair<T, V> {
    private final T key;
    private final V value;

    public Pair(T k, V v) {
        this.key = k;
        this.value = v;
    }

    public T getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair[" + key + ", " + value + ']';
    }

    @Override
    public int hashCode() {
        return key.hashCode() + value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) return false;
        return hashCode() == obj.hashCode();
    }
}
