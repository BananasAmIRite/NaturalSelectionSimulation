package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.CreatureMoveEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Traits;
import org.BananasAmIRite.NaturalSelectionSimulation.traits.EnergyTrait;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.CoordinateUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Creature extends Thread implements Entity {
    private final int id;
    private final Simulation simulation;
    private SimulationCoordinate home;
    private SimulationCoordinate location;
    private final String abb = "c";
    private Traits traits;

    // multithreading stuff
    private final Object lock;
    private boolean waitQueue;
    private boolean isDead;

    private final Random RANDOM;

    public Creature(Simulation sim, int id) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        this.id = id;
        this.simulation = sim;
        this.traits = new Traits();

        this.lock = new Object();
        this.RANDOM = new Random();

        simulation.getTraitManager().addToCreature(this);
        setHome(this.simulation.getCreaturesManager().generateHome());
        setLocation(this.getHome());  // on creation of creature, default location is home

        // creature trait test
        System.out.println("EnergyTrait Value: ");
        System.out.println(getTraits().getTraitValue(EnergyTrait.class));
        getTraits().setTrait(EnergyTrait.class, 10);
        System.out.println(getTraits().getTraitValue(EnergyTrait.class));
        getTraits().setTrait(EnergyTrait.class, -1);
        System.out.println(getTraits().getTraitValue(EnergyTrait.class));

        simulation.getCreaturesManager().registerCreature(this);
    }

    public SimulationCoordinate getHome() {
        return home;
    }

    public void setHome(SimulationCoordinate home) {
        this.home = home;
    }

    public int getEntityID() {
        return id;
    }

    @Override
    public String getEntityAbbreviation() {
        return abb;
    }

    public SimulationCoordinate getLocation() {
        return location;

    }

    public boolean setLocation(SimulationCoordinate location) {
        if (!simulation.getMap().changeCreatureLocation(this, this.location, location)) return false;
        this.location = location;

        return true;
    }

    public Traits getTraits() {
        return traits;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "id=" + id +
                ", simulation=" + simulation +
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
        moveToLocation(getLocation().move(Coordinate.DIRECTIONS.get(RANDOM.nextInt(Coordinate.DIRECTIONS.size())), 1));

        // TODO: add energy system
        // TODO: setup food/home logic (energy > calcEnergyDistance(Coordinate coordToHome) ? food() : home())

        // System.out.println(getTraits().getTrait(EnergyTrait.class));

        //  set energy
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
        simulation.getMap().getMap().get(getLocation().getY()).get(getLocation().getX()).removeCreature(this);
    }

    /**
     * Now with Event Firing!!1!
     *
     */
    public void moveToLocation(SimulationCoordinate c) {
        SimulationCoordinate oldCoords = getLocation();
        if (setLocation(c)) simulation.getEventManager().fireEvent(new CreatureMoveEvent(simulation.getMap().getMap(), this, oldCoords, c));

    }

    /**
     * Stops thread
     *
     * */
    public void setDead(boolean dead) {
        isDead = dead;
    }
}
