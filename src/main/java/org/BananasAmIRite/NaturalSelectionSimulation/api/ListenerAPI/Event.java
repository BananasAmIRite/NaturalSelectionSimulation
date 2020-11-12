package org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI;

public abstract class Event {

    private String name;

    public String getEventName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}
