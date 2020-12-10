package org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;

public interface TraitBase {

    /**
     * When this trait is changed, this will be executed in order to modify the creature as needed
     *
     * @return the resulting creature after value change
     * @deprecated value changes should now be determined in creature wrapper classes for more flexibility
     */
    @Deprecated
    Creature onValueChange(Creature creature, int value);

    /**
     * When a creature has reproduced; changes the values randomly as needed (defines how often and how much to change trait values)
     *
     * @param value the old trait value
     * @return the new trait value
     */
    double creatureReproduce(double value);

    /**
     * Gets ID of the trait
     *
     * @return ID of the trait
     */
    String getID();

    /**
     * Sets the ID of the trait
     * The default ID is the class name
     */
    void setID(String id);

    /**
     * Gets the lowest value of the trait
     *
     * */
    double getLowestValue();

    /**
     * this and {@link #setHighestValue(double)} define the boundaries of the values of the traits
     * Value must be set at method, {@link #setValues()} or it will not apply
     */
    void setLowestValue(double lowest);

    /**
     * Gets the highest value of the trait
     *
     * */
    double getHighestValue();

    /**
     * this and {@link #setLowestValue(double)} define the boundaries of the values of the traits
     * If the value given is 0, there will not be any highest value bound
     * Value must be set at method, {@link #setValues()} or it will not apply
     */
    void setHighestValue(double highest);

    /**
     * Gets the default value of the trait
     *
     * */
    double getDefaultValue();

    /**
     * Sets the default value of this trait
     * Value must be set at method, {@link #setValues()} or it will not apply
     */
    void setDefaultValue(int value);

    /**
     * Overridable method that sets all the values (eg. {@link Trait#lowestValue}, {@link Trait#highestValue})
     *
     * */
    void setValues();
}
