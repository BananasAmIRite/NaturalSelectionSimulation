package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.EntityRemoveEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Traits;
import org.BananasAmIRite.NaturalSelectionSimulation.traits.EnergyTrait;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.CoordinateUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Creature extends Entity implements Runnable {
    private final int id;
    private SimulationCoordinate home;
    private Traits traits;
    private final int SENSING_RANGE = 10;

    // multithreading stuff
    private final Thread thread;
    private final Object lock;
    private boolean waitQueue;
    private boolean isDead;

    private boolean isHome = false;

    public Creature(Simulation sim, int id) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        super(sim);
        this.id = id;
        this.traits = new Traits();

        this.lock = new Object();

        this.sim.getTraitManager().addToCreature(this);
        setHome(this.sim.getCreaturesManager().generateHome());
        setLocation(this.getHome());  // on creation of creature, default location is home

        this.sim.getCreaturesManager().registerCreature(this);

        thread = new Thread(this);
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
                Thread.sleep(calculateWaitTime() * 1000);

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
        return 1;
    }

    /**
     * method containing all the tasks the creature will do (such as movement, increasing hunger)
     *
     * */
    protected void doTasks() {
        moveToLocation(getLocation().move(Coordinate.Direction.getDirections().get(sim.getMap().getMapRandom().nextInt(Coordinate.Direction.getDirections().size())), 1));

        // TODO: add energy system
        // TODO: setup food/home logic (energy > calcEnergyDistance(Coordinate coordToHome) ? food() : home())

        int direction = traits.getTraitValue(EnergyTrait.class) > getEnergyDistance(getHome()) ? findFood() : goHome();

        if (direction != 0) {
            moveToLocation(getLocation().move(direction, 1));
            traits.setTrait(EnergyTrait.class, traits.getTraitValue(EnergyTrait.class));
        } else {
            // pause creature and do stuff
            isHome = true;
            pause();
        }


        // System.out.println(getTraits().getTrait(EnergyTrait.class));

        //  set energy
    }

    protected int goHome() {
        return CoordinateUtils.pathfindNextDirection(getLocation(), getHome());
    }

    protected int getEnergyDistance(Coordinate coord) {
        return coord.getX() + coord.getY();
    }

    protected int findFood() {
        SimulationCoordinate co1 = getLocation().moveCoords(-(this.SENSING_RANGE), -(this.SENSING_RANGE));
        SimulationCoordinate co2 = getLocation().moveCoords((this.SENSING_RANGE), (this.SENSING_RANGE));

        List<Tile> t = Tile.getAllTilesBetween(co1, co2);

        for (Tile tile : t) {
            if (tile.hasEntity(Food.class)) {
                CoordinateUtils.pathFind(getLocation(), tile.getCoords());
            }
        }
        return Coordinate.Direction.getDirections().get(sim.getMap().getMapRandom().nextInt(Coordinate.Direction.getDirections().size())); // random direction
    }

    /**
     * Finds home, then goes one step towards it
     *
     * Moves x first, then y
     *
     * @return if creature is already at home
     * */
    protected boolean searchHome() {
        Coordinate coordsToHome = getToHome();

        if (coordsToHome.getX() != 0) {
            moveToLocation(getLocation().add((coordsToHome.getX() > 0 ? 1 : -1),0));
            return false;
        }
        if (coordsToHome.getY() != 0) {
            moveToLocation(getLocation().add(0,(coordsToHome.getY() > 0 ? 1 : -1)));
            return false;
        }
        // otherwise at home
        return true;
    }

    public Coordinate getToHome() {
        return CoordinateUtils.pathFind(getLocation(), getHome());
    }

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
    }

    public Thread getThread() {
        return thread;
    }

    public boolean isHome() {
        return isHome;
    }
}
