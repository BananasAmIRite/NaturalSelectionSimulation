package org.BananasAmIRite.NaturalSelectionSimulation.exceptions.ConfigExceptions;

import org.BananasAmIRite.NaturalSelectionSimulation.exceptions.ConfigException;

/**
 * Used for if a config is not found
 */
public class ConfigNotFoundException extends ConfigException {
    public ConfigNotFoundException() {

    }

    public ConfigNotFoundException(String message) {
        super(message);
    }
}
