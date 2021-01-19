package org.BananasAmIRite.NaturalSelectionSimulation.apitest.display;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationController extends JFrame {

    private Simulation sim;

    public SimulationController(Simulation sim) {

        this.sim = sim;

        initFrame();
    }

    private void initFrame() {

        setTitle("Simulation Controller");

        // pause all button
        JButton pauseBtn = new JButton("Pause All");
        pauseBtn.addActionListener(new ButtonPauseListener());
        add(pauseBtn);

        // play all button
        JButton playBtn = new JButton("Play All");
        playBtn.addActionListener(new ButtonPlayListener());
        add(playBtn);

        // play specific button
        JTextField playSpecField = new JTextField("Play Specific Creature");
        JButton playSpecBtn = new JButton("Play Specific");
        add(playSpecField);
        add(playSpecBtn);
        playSpecBtn.addActionListener(new ButtonPlaySpecificListener(playSpecField));

        // pause specific button
        JTextField pauseSpecField = new JTextField("Pause Specific Creature");
        JButton pauseSpecBtn = new JButton("Pause Specific");
        add(pauseSpecField);
        add(pauseSpecBtn);
        pauseSpecBtn.addActionListener(new ButtonPauseSpecificListener(pauseSpecField));

        // kill creature btn
        JTextField killSpecField = new JTextField("Kill Specific");
        JButton killSpecBtn = new JButton("Kill Specific");
        add(killSpecField);
        add(killSpecBtn);
        killSpecBtn.addActionListener(new CreatureKillListener(killSpecField));


        setSize(400, 200);
        setVisible(false);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private class ButtonPauseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            sim.getCreaturesManager().pauseAll();
        }
    }

    private class ButtonPlayListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            sim.getCreaturesManager().playAll();
        }
    }

    private class ButtonPlaySpecificListener implements ActionListener {

        private JTextField field;

        public ButtonPlaySpecificListener(JTextField field) {
            this.field = field;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int test = Integer.parseInt(field.getText());
            } catch (NumberFormatException ignored) {
                return;
            }

            int index = Integer.parseInt(field.getText());

            if (sim.getCreaturesManager().getCreature(index) == null) return;

            sim.getCreaturesManager().getCreature(index).play();
        }
    }

    private class ButtonPauseSpecificListener implements ActionListener {

        private JTextField field;

        public ButtonPauseSpecificListener(JTextField field) {
            this.field = field;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int test = Integer.parseInt(field.getText());
            } catch (NumberFormatException ignored) {
                return;
            }

            int index = Integer.parseInt(field.getText());

            if (sim.getCreaturesManager().getCreature(index) == null) return;

            sim.getCreaturesManager().getCreature(index).pause();
        }
    }

    private class CreatureKillListener implements ActionListener {

        private JTextField field;

        public CreatureKillListener(JTextField field) {
            this.field = field;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int test = Integer.parseInt(field.getText());
            } catch (NumberFormatException ignored) {
                return;
            }

            int index = Integer.parseInt(field.getText());

            if (sim.getCreaturesManager().getCreature(index) == null) return;

            sim.getCreaturesManager().getCreature(index).setDead(true);
        }
    }
}
