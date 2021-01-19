package org.BananasAmIRite.NaturalSelectionSimulation.apitest.display;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.Listener;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.annotations.EventHandler;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.GenerationEndEvent;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.GenerationStartEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerationController extends JFrame implements Listener {

    private Simulation sim;
    private JLabel generationText;

    public GenerationController(Simulation sim) {

        this.sim = sim;

        sim.getEventManager().registerEventListener(this);

        generationText = new JLabel("Simulation not started");

        initFrame();
    }

    private void initFrame() {

        setTitle("Generation Controller");

        add(generationText);

        setSize(400, 200);
        setVisible(false);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @EventHandler
    public void onGenerationStart(GenerationStartEvent e) {
        generationText.setText("Currently running generation: " + e.getCurrentGeneration());
    }

    @EventHandler
    public void onGenerationEnd(GenerationEndEvent e) {
        generationText.setText("Finished generation: " + e.getCurrentGeneration());
    }
}
