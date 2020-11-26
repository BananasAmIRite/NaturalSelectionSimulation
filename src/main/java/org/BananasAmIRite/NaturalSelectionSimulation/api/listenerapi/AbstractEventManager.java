package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi;

public abstract class AbstractEventManager {
    /**
     * Registers an event listener that implements {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.Listener}
     */
    public abstract void registerEventListener(Listener listener);


    /**
     * used for firing an event; calls all subscribers (using {@link org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.annotations.EventHandler}) to the fired event
     */
    public abstract void fireEvent(Event evt);
}
