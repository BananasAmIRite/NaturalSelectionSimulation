package org.BananasAmIRite.NaturalSelectionSimulation;

import org.BananasAmIRite.NaturalSelectionSimulation.Listeners.CreaturesListener;
import org.BananasAmIRite.NaturalSelectionSimulation.api.ListenerAPI.EventManager;
import org.BananasAmIRite.NaturalSelectionSimulation.api.TraitsAPI.TraitManager;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.api.APIManager; 

public class Simulation {
    private final EventManager eventManager;
    private final SimulationMap map;
    private final CreaturesManager creaturesManager;
    private final CreaturesListener cListener;
    private final TraitManager traitManager;
        private final APIManager apiManager; 
    private boolean isFirstStarted;
    private Class<? extends Creature> creatureClass = Creature.class;

    public Simulation() {
        eventManager = new EventManager();
        map = new SimulationMap(this, 10, 10);
        creaturesManager = new CreaturesManager(this);
        cListener = new CreaturesListener(this);
        traitManager = new TraitManager(this);
        apiManager = new APIManager(this); 
        getEventManager().registerEventListener(cListener);
        try {
            // run api main class
            apiManager.initMainClass(); 
        } catch(Exception e) {
            e.printStackTrace(); 
        }

    }

    public SimulationMap getMap() {
        return map;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public CreaturesManager getCreaturesManager() {
        return creaturesManager;
    }

    public CreaturesListener getcListener() {
        return cListener;
    }

    public boolean isFirstStarted() {
        return isFirstStarted;
    }

    public TraitManager getTraitManager() {
        return traitManager;
    }

    public void setCreatureClass(Class<? extends Creature> creatureClass) {
        if (isFirstStarted) throw new IllegalStateException("Creature class cannot be set after a creature has been created!");
        this.creatureClass = creatureClass;
    }

    public Class<? extends Creature> getCreatureClass() {
        return creatureClass;
    }

    public void setFirstStarted(boolean firstStarted) {
        isFirstStarted = firstStarted;
    }
}
