package org.BananasAmIRite.NaturalSelectionSimulation.api.TraitsAPI;

import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;

public interface TraitBase {

    /**
     * When this trait is changed, this will be executed in order to modify the creature as needed
     * @deprecated value changes should now be determined in creature wrapper classes for more flexibility
     * @return the resulting creature after value change
     */
    Creature onValueChange(Creature creature, int value);

    /**
     * When a creature has reproduced; changes the values randomly as needed (defines how often and how much to change trait values)
     * @return the new trait value
     */
    int creatureReproduce(int value);

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

    int getLowestValue();

    /**
     * this and {@link #setHighestValue(int)} define the boundaries of the values of the traits
     * Value must be set at constructor or it will not apply
     */
    void setLowestValue(int lowest);

    int getHighestValue();

    /**
     * this and {@link #setLowestValue(int)} define the boundaries of the values of the traits
     * If the value given is 0, there will not be any highest value bound
     * Value must be set at constructor or it will not apply
     */
    void setHighestValue(int highest);

    int getDefaultValue();

    /**
     * Sets the default value of this trait
     * Value must be set at constructor or it will not apply
     */
    void setDefaultValue(int value);
}
