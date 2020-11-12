package org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI;

public abstract class AbstractEventManager {
    /**
     * Registers an event listener that implements {@link org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.Listener}
     */
    public abstract void registerEventListener(Listener listener);


    /**
     * used for firing an event; calls all subscribers (using {@link org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.annotations.EventHandler}) to the fired event
     */
    public abstract void fireEvent(Event evt);
}
