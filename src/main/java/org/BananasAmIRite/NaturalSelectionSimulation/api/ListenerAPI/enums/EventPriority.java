package org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.enums;

public enum EventPriority {

    HIGHEST(4),
    HIGH(3),
    MEDIUM(2),
    LOW(1),
    LOWEST(0);

    private int priority = 2;

    EventPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
