package org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;

public class GenerationSetFinishEvent extends GenerationEvent {

    private int amount;
    private int foods;
    private int creatures;

    public GenerationSetFinishEvent(Simulation sim, int currentGeneration, int amt, int foods, int creatures) {
        super(sim, currentGeneration);
        this.amount = amt;
        this.foods = foods;
        this.creatures = creatures; 
    }

    public int getAmount() {
        return amount;
    }

    public int getFoods() {
        return foods;
    }

    public int getCreatures() {
        return creatures;
    }
}
