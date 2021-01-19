package org.BananasAmIRite.NaturalSelectionSimulation;

import org.BananasAmIRite.NaturalSelectionSimulation.api.APIManager;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.EventManager;
import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.TraitManager;
import org.BananasAmIRite.NaturalSelectionSimulation.listeners.CreaturesListener;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.traits.EnergyTrait;

public class Simulation {
    private final EventManager eventManager;
    private final SimulationMap map;
    private final CreaturesManager creaturesManager;
    private final CreaturesListener cListener;
    private final TraitManager traitManager;
    private final APIManager apiManager;
    private final GenerationManager generationManager;
    private final FoodManager foodManager;
    private boolean isFirstStarted;
    private Class<? extends Creature> creatureClass = Creature.class;
    private final int sizeX;
    private final int sizeY;

    public Simulation(int x, int y)  {
        eventManager = new EventManager();
        this.sizeX = x;
        this.sizeY = y;
        map = new SimulationMap(this, x, y);
        creaturesManager = new CreaturesManager(this);
        foodManager = new FoodManager(this);
        cListener = new CreaturesListener(this);
        traitManager = new TraitManager(this);
        apiManager = new APIManager(this);
        generationManager = new GenerationManager(this);
        getEventManager().registerEventListener(cListener);

        registerNativeAPIUsages();

        try {
            // run api main class
            apiManager.initMainClass();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void registerNativeAPIUsages() {
        traitManager.registerTrait(EnergyTrait.class);
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

    public FoodManager getFoodManager() {
        return foodManager;
    }

    public CreaturesListener getcListener() {
        return cListener;
    }

    public boolean isFirstStarted() {
        return isFirstStarted;
    }

    public void setFirstStarted(boolean firstStarted) {
        isFirstStarted = firstStarted;
    }

    public TraitManager getTraitManager() {
        return traitManager;
    }

    public GenerationManager getGenerationManager() {
        return generationManager;
    }

    public Class<? extends Creature> getCreatureClass() {
        return creatureClass;
    }

    public void setCreatureClass(Class<? extends Creature> creatureClass) {
        if (isFirstStarted)
            throw new IllegalStateException("Creature class cannot be set after a creature has been created!");
        this.creatureClass = creatureClass;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
