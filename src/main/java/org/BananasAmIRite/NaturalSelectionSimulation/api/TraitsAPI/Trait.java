package org.BananasAmIRite.NaturalSelectionSimulation.api.TraitsAPI;

public abstract class Trait implements TraitBase {
    private int lowestValue = 0;
    private int highestValue = 0;
    private int defaultValue = 1;
    private String ID = getClass().getSimpleName();

    @Override
    public final int getLowestValue() {
        return lowestValue;
    }

    @Override
    public final void setLowestValue(int lowest) {
        this.lowestValue = lowest;
    }

    @Override
    public final int getHighestValue() {
        return highestValue;
    }

    @Override
    public final void setHighestValue(int highest) {
        this.highestValue = highest;
    }

    @Override
    public final int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public final void setDefaultValue(int value) {
        if (this.defaultValue > highestValue && highestValue != 0) return;
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
}
