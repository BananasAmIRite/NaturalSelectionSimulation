package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import java.util.*;

public class GenericArrayList<T> {

    private Map<Integer, T> data;

    public GenericArrayList() {
        data = new HashMap<>();
    }

    public GenericArrayList(int size) {
        this();
        for (int i = 0; i < size; i++) {
            data.put(i, null);
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.values().stream().allMatch(Objects::isNull);
    }

    public boolean contains(T t) {
        return data.containsValue(t);
    }

    public Iterator<T> iterator() {
        return data.values().iterator();
    }

    public Object[] toArray() {
        return new ArrayList<>(data.values()).toArray();
    }

    public <T1> T1[] toArray(T1[] a) {
        return new ArrayList<>(data.values()).toArray(a);
    }

    public void add(T t) {
        int index = new ArrayList<>(data.values()).indexOf(null);

        if (index == -1) {
            // not found
            data.put(data.size(), t);
        } else {
            data.put(index, t);
        }
    }

    public void put(int index, T t) {
        if (index > data.size())  {
            for (int i = data.size(); i < index; i++) {
                // fill with null values
                data.put(i, null);
            }
        }
        data.put(index, t);
    }

    public boolean remove(T t) {
        if (t == null || !contains(t)) return false;
        for (Map.Entry<Integer, T> integerTEntry : data.entrySet()) {
            if (integerTEntry.getValue() == t) {
                data.put(integerTEntry.getKey(), null);
                return true;
            }
        }
        return false;
    }

    public T get(int index) {
        return data.get(index);
    }

    public T remove(int index) {
        return data.put(index, null);
    }

    public int indexOf(T t) {
        for (Map.Entry<Integer, T> integerTEntry : data.entrySet()) {
            if (integerTEntry.getValue() == t) return integerTEntry.getKey();
        }
        return -1;
    }

    public ListIterator<T> listIterator() {
        return new ArrayList<>(data.values()).listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return new ArrayList<>(data.values()).listIterator(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return new ArrayList<>(data.values()).subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        return new ArrayList<>(data.values()).toString();
    }
}
