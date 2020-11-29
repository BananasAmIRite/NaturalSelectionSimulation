package org.BananasAmIRite.NaturalSelectionSimulation.objects;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Traits;

import java.util.Random;

public class Creature extends Thread implements Entity {
    private final int id;
    private final Simulation simulation;
    private Coordinate home;
    private Coordinate location;
    private Traits traits;

    // multithreading stuff
    private final Object lock;
    private boolean waitQueue;
    private boolean isDead;

    private Random RANDOM;

    public Creature(Simulation sim, int id) throws IllegalAccessException, InstantiationException {
        this.id = id;
        this.simulation = sim;
        this.traits = new Traits();

        this.lock = new Object();
        this.RANDOM = new Random();

        simulation.getCreaturesManager().registerCreature(this);
        simulation.getTraitManager().addToCreature(this);
        setHome(this.simulation.getCreaturesManager().generateHome());
        setLocation(this.getHome());  // on creation of creature, default location is home
    }

    public Coordinate getHome() {
        return home;
    }

    public void setHome(Coordinate home) {
        this.home = home;
    }

    public int getCreatureID() {
        return id;
    }

    public Coordinate getLocation() {
        return location;

    }

    public boolean setLocation(Coordinate location) {
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
                Thread.sleep(calculateWaitTime());

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
    private long calculateWaitTime() {
        return 1;
    }

    /**
     * method containing all the tasks the creature will do (such as movement, increasing hunger)
     *
     * */
    protected void doTasks() {
        setLocation(getLocation().move(Coordinate.DIRECTIONS.get(RANDOM.nextInt(Coordinate.DIRECTIONS.size())), 1));
    }


}
