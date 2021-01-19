package org.BananasAmIRite.NaturalSelectionSimulation;

import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.GenerationEndEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.GenerationStartEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.apitest.TraitsCreature;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Creature;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Food;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GenerationManager {

    private Simulation sim;

    // thread to handle generations internally
    private Thread thread;

    private boolean isInGeneration;

    private int generationAmount;

    private final Object lock;

    public GenerationManager(Simulation sim) {
        this.sim = sim;
        this.generationAmount = 0;
        this.lock = new Object();
    }

    public void startGeneration(int creatures, int food, int amount, int delay) {
        if (isInGeneration) return;
        thread = new Thread(new Generation(creatures, food, amount, delay), "Generation");
        thread.start();
    }

    private void finishGeneration() {
        isInGeneration = false;
    }

    private class Generation implements Runnable {

        private final int creatures;
        private final int food;
        private final int amount;
        private final int delay;

        public Generation(int c, int f, int amount, int delay) {
            this.creatures = c;
            this.food = f;
            this.amount = amount;
            this.delay = delay;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < amount; i++) {
                    sim.setFirstStarted(true);
                    sim.getEventManager().fireEvent(new GenerationStartEvent(sim, generationAmount + 1));
                    isInGeneration = true;
                    if (generationAmount == 0) { // first initialization, add creatures and stuff
                        Class<? extends Creature> creatureClass = sim.getCreatureClass();
                        for (int j = 0; j < creatures; j++) {
                            try {
                                creatureClass.getDeclaredConstructor(Simulation.class).newInstance(sim);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    generationAmount++;

                    for (int j = 0; j < food; j++) {
                        new Food(sim);
                    }

                    for (Creature creature : sim.getCreaturesManager().getCreatures()) {
                        creature.resetGeneration();
                    }

                    // run simulation and wait for it to finish

                    System.out.println(sim.getCreaturesManager().getCreatures());
                    for (Creature creature : sim.getCreaturesManager().getCreatures()) { // start all creatures up
                        System.out.println(creature);
                        if (creature.getThread().isAlive()) {
                            System.out.println("PLAYING");
                            creature.play();
                        } else {
                            System.out.println("STARTING");
                            creature.getThread().start();
                        }
                    }

                    // blocking using wait; waiting for creature to call notify
                    try {
                        synchronized (lock) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("FINISHED GENERATION :D");

                    // cleanup (mutations and removing all extra food)
                    for (Creature creature : sim.getCreaturesManager().getCreatures()) {
                        try {
                            creature.onGenerationFinish();
                        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    for (Food f : new HashSet<>(sim.getFoodManager().getFoods())) { // copy so no cme
                        // cleanup food
                        f.remove();
                    }

                    sim.getEventManager().fireEvent(new GenerationEndEvent(sim, generationAmount));

                    finishGeneration();
                    try {
                        Thread.sleep(this.delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object getLock() {
        return lock;
    }

    /**
     * Unlocks the lock, signaling that generation is finished
     *
     * */
    public void unlock() {
        synchronized (lock) {
            lock.notify();
        }
    }
}
