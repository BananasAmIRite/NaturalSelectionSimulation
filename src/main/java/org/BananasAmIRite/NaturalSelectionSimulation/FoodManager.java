package org.BananasAmIRite.NaturalSelectionSimulation;

import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.EntityAddEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.EntityRemoveEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Food;

import java.util.Collection;
import java.util.HashMap;

public class FoodManager {
    private final HashMap<Integer, Food> foods = new HashMap<>();
    private final Simulation sim;

    public FoodManager(Simulation sim) {
        this.sim = sim;
    }

    public void registerFood(Food food) {
        sim.setFirstStarted(true);
        foods.put(food.getEntityID(), food);
        sim.getEventManager().fireEvent(new EntityAddEvent(sim.getMap().getMap(), food));
    }

    public void deregisterFood(int id) {
        if (foods.get(id) == null) return;
        sim.getEventManager().fireEvent(new EntityRemoveEvent(sim.getMap().getMap(), foods.get(id)));
        foods.remove(id);
    }

    public void deregisterFood(Food food) {
        foods.remove(food.getEntityID());
        sim.getEventManager().fireEvent(new EntityRemoveEvent(sim.getMap().getMap(), food));
    }

    public Collection<Food> getFoods() {
        return foods.values();
    }

    public Food getFood(int id) {
        return foods.get(id);
    }
}
