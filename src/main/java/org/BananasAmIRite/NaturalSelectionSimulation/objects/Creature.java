package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.EntityRemoveEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Trait;
import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Traits;
import org.BananasAmIRite.NaturalSelectionSimulation.traits.EnergyTrait;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.CoordinateUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Creature extends Entity implements Runnable {
    private final int id;
    private SimulationCoordinate home;
    private Traits traits;
    private int foodCount = 0;

    // multithreading stuff
    private final Thread thread;
    private final Object lock;
    private boolean waitQueue;
    private boolean isDead;

    private boolean isHome = false;

    private static int CURRENT_ID = 0;

    public Creature(Simulation sim) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super(sim);
        this.id = CURRENT_ID;
        CURRENT_ID++;

        this.traits = new Traits();

        this.lock = new Object();

        this.sim.getTraitManager().addToCreature(this);
        setHome(this.sim.getCreaturesManager().generateHome());
        setLocation(this.getHome());  // on creation of creature, default location is home

        this.sim.getCreaturesManager().registerCreature(this);

        thread = new Thread(this, "Creature-" + this.id);
    }

    public SimulationCoordinate getHome() {
        return home;
    }

    public void setHome(SimulationCoordinate home) {
        this.home = home;
    }

    public final int getEntityID() {
        return id;
    }

    @Override
    public final String getEntityAbbreviation() {
        return "c";
    }

    @Override
    public boolean showEntityID() {
        return true;
    }

    public Traits getTraits() {
        return traits;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "id=" + id +
                ", simulation=" + sim +
                ", home=" + home +
                ", location=" + location +
                ", traits=" + traits +
                '}';
    }

    // not busy waiting
    @SuppressWarnings("BusyWait")
    public void run() {
        while (!isDead) {
            try {

                if (waitQueue) {
                    synchronized (lock) {
                        lock.wait();
                    }
                    waitQueue = false;
                }

                // tasks
                Thread.sleep((long) (calculateWaitTime() * sim.getSimulationSpeed()));

                doTasks();

             } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        this.waitQueue = true;
    }

    public void play() {
        this.waitQueue = false;
        synchronized (lock) {
            lock.notify();
        }
    }

    /**
     * Calculates the wait time (milliseconds) for one step of a creature
     */
    protected long calculateWaitTime() {
        return 1000;
    }

    /**
     * method containing all the tasks thce creature will do (such as movement, increasing hunger)
     *
     * */
    protected void doTasks() {
        if (traits.getTraitValue(EnergyTrait.class) <= 0) {
            setDead(true);
            removeFromMap();
            return;
        }

        if (isDead) return;
        int direction = (getEnergyDistance(CoordinateUtils.pathFind(getLocation(), getHome())) >= traits.getTraitValue(EnergyTrait.class) && foodCount >= 1 /*req of 1 food count :D*/) ? goHome() : findFood();
        if (direction != 0) {
            moveToLocation(getLocation().move(direction, 1));
            traits.setTrait(EnergyTrait.class, Math.max(0, traits.getTraitValue(EnergyTrait.class) - getEnergyPerStep()));
        } else {
            // pause creature and do stuff
            setHome(true);
            pause();
        }


        Food f = (Food) sim.getMap().getMap().get(getLocation().getY()).get(getLocation().getX()).getEntity(Food.class);

        if (f != null) {
            f.remove();
            this.foodCount++;
        }
    }

    protected int goHome() {
        return CoordinateUtils.pathfindNextDirection(getLocation(), getHome());
    }

    protected int getEnergyDistance(Coordinate coord) {
        return ((Math.abs(coord.getX()) + Math.abs(coord.getY())) * getEnergyPerStep()) + (2 * getEnergyPerStep())/*cuz without, creature would have to make last step towards home or fail getting home*/;
    }

    protected int getEnergyPerStep() {
        return 10;
    }

    protected int findFood() {

        SimulationCoordinate co1 = getLocation().add(-(getSensingRange()), -(getSensingRange()));
        SimulationCoordinate co2 = getLocation().add((getSensingRange()), (getSensingRange()));

        List<Tile> t = Tile.getAllTilesBetween(co1, co2);

        Pair<Tile, Coordinate> lowestDist = null;

        for (Tile tile : t) {
            if (tile.hasEntity(Food.class)) {
                Coordinate pf = CoordinateUtils.pathFind(getLocation(), tile.getCoords());

                if (lowestDist == null || (Math.abs(pf.getX()) + Math.abs(pf.getY()) < Math.abs(lowestDist.getValue().getX()) + Math.abs(lowestDist.getValue().getY()) && Math.abs(pf.getX()) + Math.abs(pf.getY()) > 0)) {
                    lowestDist = new Pair<>(tile, pf);
                }
            }
        }

        if (lowestDist != null) {
            int c = CoordinateUtils.pathfindNextDirection(getLocation(), lowestDist.getKey().getCoords());
            if (c != 0) return c;
        }

        return Coordinate.Direction.getDirections().get(sim.getMap().getMapRandom().nextInt(Coordinate.Direction.getDirections().size())); // random direction
    }

//    /**
//     * Finds home, then goes one step towards it
//     *
//     * Moves x first, then y
//     *
//     * @return if creature is already at home
//     * */
//    protected boolean searchHome() {
//        Coordinate coordsToHome = getToHome();
//
//        if (coordsToHome.getX() != 0) {
//            moveToLocation(getLocation().add((coordsToHome.getX() > 0 ? 1 : -1),0));
//            return false;
//        }
//        if (coordsToHome.getY() != 0) {
//            moveToLocation(getLocation().add(0,(coordsToHome.getY() > 0 ? 1 : -1)));
//            return false;
//        }
//        // otherwise at home
//        return true;
//    }

    /**
     * Should ONLY be used when removing a creature
     *
     * */
    public void removeFromMap() {
        if (sim.getMap().getMap().get(getLocation().getY()).get(getLocation().getX()).removeEntity(this)) {
            sim.getEventManager().fireEvent(new EntityRemoveEvent(sim.getMap().getMap(), this));
        }
    }

    /**
     * Stops thread
     *
     * */
    public void setDead(boolean dead) {
        isDead = dead;
        if (dead) {
            sim.getCreaturesManager().deregisterCreature(this.id);
            if (isAllFinished()) sim.getGenerationManager().unlock();
        }
    }

    public Thread getThread() {
        return thread;
    }

    public boolean isHome() {
        return isHome;
    }

    /**
     * reset creature values in preparation for next generation
     *
     * */
    public final void resetGeneration() {
        moveToLocation(getHome()); // go home in case theyre not home
        setHome(false);
        this.foodCount = 0;
        getTraits().setTrait(EnergyTrait.class, getTraits().getTrait(EnergyTrait.class).getDefaultValue()); // set to default value
    }

    private void setHome(boolean home) {
        this.isHome = home;
        if (home) {
            if (isAllFinished()) sim.getGenerationManager().unlock();
        }
    }

    public void onGenerationFinish() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (foodCount >= 2) {
            // copies traits to new creature and mutates them
            Traits t = new Traits(this.traits);
            for (Trait trait : t.getTraits()) {
                double v = trait.creatureReproduce(trait.getValue());

                t.setTrait(trait.getClass(), v);

            }
            Creature c = sim.getCreatureClass().getDeclaredConstructor(Simulation.class).newInstance(sim);

            c.setTraits(t);

        }
    }

    public void setTraits(Traits traits) {
        this.traits = traits;
    }

    private boolean isAllFinished() {
        boolean isAllFinished = true;
        for (Creature creature : sim.getCreaturesManager().getCreatures()) {
            if (!creature.isHome()) {
                isAllFinished = false;
                break;
            }
        }
        return isAllFinished;
    }

    public int getSensingRange() {
        return 1;
    }
}
