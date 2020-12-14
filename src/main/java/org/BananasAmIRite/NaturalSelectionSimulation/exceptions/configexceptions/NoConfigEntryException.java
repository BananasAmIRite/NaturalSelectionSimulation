package org.BananasAmIRite.NaturalSelectionSimulation.exceptions.configexceptions;

import org.BananasAmIRite.NaturalSelectionSimulation.exceptions.ConfigException;

/**
 * Used for when a config does not contain a required entry
 */
public class NoConfigEntryException extends ConfigException {


    public NoConfigEntryException(String message) {
        super(message);
    }

}
