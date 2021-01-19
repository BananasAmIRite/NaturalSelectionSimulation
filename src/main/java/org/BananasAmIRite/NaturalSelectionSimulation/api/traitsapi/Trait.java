package org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;

public abstract class Trait implements TraitBase {
    protected double lowestValue = 0;
    protected double highestValue = 0;
    protected double defaultValue = 1;
    protected double currentValue;
    private String ID = getClass().getSimpleName();

    public Trait() {
        setValues();
        currentValue = defaultValue;
    }

    @Override
    public double getLowestValue() {
        return lowestValue;
    }

    @Override
    public final void setLowestValue(double lowest) {
        this.lowestValue = lowest;
    }

    @Override
    public double getHighestValue() {
        return highestValue;
    }

    @Override
    public final void setHighestValue(double highest) {
        this.highestValue = highest;
    }

    @Override
    public final double getDefaultValue() {
        return defaultValue;
    }

    @Override
    public final void setDefaultValue(int value) {
        if (!isSettable(value)) return;
        this.defaultValue = value;
    }

    @Override
    public final String getID() {
        return ID;
    }

    @Override
    public final void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public final Creature onValueChange(Creature creature, int value) {
        // make unimplementable because deprecated but too lazy to remove
        return creature;
    }

    public boolean isSettable(double value) {
        return (value <= highestValue || highestValue == 0) && (value >= lowestValue);
    }

    public void setValue(double value) {
        this.currentValue = value;
    }

    public double getValue() {
        return currentValue;
    }

    /**
     * Overridable method that sets all the values (eg. {@link Trait#lowestValue}, {@link Trait#highestValue})
     *
     * */
    protected abstract void setValues();

    @Override
    public String toString() {
        return "Trait:" + ID + "{" + getValue() + '}';
    }
}