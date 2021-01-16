package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.CoordinateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tile {

    private final List<Entity> entities = new CopyOnWriteArrayList<>();

    private SimulationCoordinate coords;

    public Tile(SimulationCoordinate coords) {
        this.coords = coords;
    }

    synchronized public boolean addEntity(Entity entity) {
        if (entities.contains(entity)) return false;
        entities.add(entity);
        return true;
    }

    synchronized public boolean removeEntity(Entity entity) {
        entities.remove(entity);
        return true;
    }

    public boolean hasEntity(Class<? extends Entity> clazz) {
        for (Entity entity : entities) {
            if (entity.getClass() == clazz) return true;
        }
        return false;
    }

    public Entity getEntity(Class<? extends Entity> clazz) {
        for (Entity entity : entities) {
            if (entity.getClass() == clazz) return entity;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "entities=" + entities +
                ", coords=" + coords +
                '}';
    }

    public static String translateTileToString(Tile t) {
        String[] arr = new String[t.entities.size()];
        for (int i = 0; i < t.entities.size(); i++) {
            Entity entity = t.entities.get(i);
            if (entity == null) continue;
            arr[i] = entity.getEntityAbbreviation() + (entity.showEntityID() ? entity.getEntityID() : "");
        }
        return String.join("|", arr);
    }

    public static List<Tile> getAllTilesBetween(SimulationCoordinate c1, SimulationCoordinate c2) {
        List<Tile> t = new ArrayList<>();


        Simulation sim = c1.getSimulation();

        // i needs to be smaller value or equal value
        int y = c1.getY() - c2.getY(); // if pos then i = c2 else i = c1 cuz c1 bigger
        int x = c1.getX() - c2.getX(); // same here


        for (int i = (y>=0?c2.getY():c1.getY()); i <= (y>=0?c1.getY():c2.getY()); i++) {
            for (int j = (x>=0?c2.getX():c1.getX()); j <= (x>=0?c1.getX():c2.getX()); j++) {
                t.add(sim.getMap().getMap().get(i).get(j));
            }
        }
        return t;
    }

    public SimulationCoordinate getCoords() {
        return coords;
    }
}
