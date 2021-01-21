package org.BananasAmIRite.NaturalSelectionSimulation.apitest.display;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.Listener;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.annotations.EventHandler;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.*;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.GenericArrayList;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Pair;
import org.BananasAmIRite.NaturalSelectionSimulation.objects.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class DisplayListener implements Listener {

    private JFrame frame;
    private GenericArrayList<GenericArrayList<Pair<Component, Tile>>> simulationComponentMapping; // used for relating the Tile to its corresponding JComponent
    private int x;
    private int y;
    private Simulation sim;
    private SimulationController simulationController;
    private ConsoleWindow consoleWindow;
    private GenerationController generationController;

    public DisplayListener(int x, int y, Simulation sim) {

        this.x = x;
        this.y = y;
        this.sim = sim;

        initFrame(x, y);


        this.simulationController = new SimulationController(sim);
        this.generationController = new GenerationController(sim, this);
        // this.consoleWindow = new ConsoleWindow();
    }

    private void initFrame(int x, int y) {
        frame = new JFrame();
        frame.setLayout(new GridLayout(x, y));

        frame.setTitle("Simulation - Not Started");

        // initiate simulation component mapping
        simulationComponentMapping = new GenericArrayList<>(y);

        // populate arrays
        for (int i = 0; i < y; i++) {
            simulationComponentMapping.add(new GenericArrayList<>());
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {

                Button button = new Button();

                button.addKeyListener(new ControllerKeyListener());

                simulationComponentMapping.get(i).put(j, new Pair<>(button, sim.getMap().getMap().get(i).get(j)));
                frame.add(button);
            }
        }

        frame.addKeyListener(new ControllerKeyListener());

        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Component getComponentAt(int x, int y) {
        if (x >= this.x || y >= this.y || x < 0 || y < 0) return null; // guaranteed not on map
        return simulationComponentMapping.get(y).get(x).getKey();
    }

    public Tile getTileAt(int x, int y) {
        if (x >= this.x || y >= this.y || x < 0 || y < 0) return null; // guaranteed not on map
        return simulationComponentMapping.get(y).get(x).getValue();
    }

    @EventHandler
    public void onMapUpdate(SimulationUpdateEvent e) {
        if (e instanceof EntityMoveEvent) return; // leave for separate handler
        for (int i = 0; i < e.getMap().size(); i++) {
            List<Tile> tiles = e.getMap().get(i);
            for (int j = 0; j < tiles.size(); j++) {
                Tile tile = tiles.get(j);

                ((Button) getComponentAt(j, i)).setLabel(Tile.translateTileToString(tile));
            }
        }
    }

    @EventHandler
    public void onCreatureMove(EntityMoveEvent e) {
        ((Button) getComponentAt(e.getFrom().getX(), e.getFrom().getY())).setLabel(Tile.translateTileToString(e.getMap().get(e.getFrom().getY()).get(e.getFrom().getX())));

        ((Button) getComponentAt(e.getTo().getX(), e.getTo().getY())).setLabel(Tile.translateTileToString(e.getMap().get(e.getTo().getY()).get(e.getTo().getX())));

    }

    @EventHandler
    public void onGenerationStart(GenerationStartEvent e) {
        frame.setTitle("Simulation - Currently running generation: " + e.getCurrentGeneration());
    }

    @EventHandler
    public void onGenerationEnd(GenerationEndEvent e) {
        frame.setTitle("Simulation - Finished Generation: " + e.getCurrentGeneration());
    }

    @EventHandler
    public void onGenerationDeath(GenerationDeathEvent e) {
        frame.setTitle("Simulation - Generation Death: " + e.getCurrentGeneration());
    }


    private class ControllerKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == 'o') simulationController.setVisible(true);
            if (e.getKeyChar() == 'g') generationController.setVisible(true);
            // if (e.getKeyChar() == 'i') consoleWindow.setVisible(true);
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public SimulationController getSimulationController() {
        return simulationController;
    }

    public JFrame getFrame() {
        return frame;
    }
}
