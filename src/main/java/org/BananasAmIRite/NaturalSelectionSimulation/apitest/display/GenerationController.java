package org.BananasAmIRite.NaturalSelectionSimulation.apitest.display;

import org.BananasAmIRite.NaturalSelectionSimulation.Simulation;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.Listener;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.annotations.EventHandler;
import org.BananasAmIRite.NaturalSelectionSimulation.api.listenerapi.events.*;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerationController extends JFrame implements Listener {

    private Simulation sim;
    private JLabel generationText;
    private JLabel currentGenSet;
    private JTextField cAmt;
    private JTextField fAmt;
    private JTextField aAmt;
    private JPanel panel;
    private DisplayListener displayListener;

    public GenerationController(Simulation sim, DisplayListener l) {

        this.sim = sim;
        this.panel = new JPanel();
        this.displayListener = l;

        sim.getEventManager().registerEventListener(this);

        generationText = new JLabel("Simulation not started");
        currentGenSet = new JLabel();

        initFrame();
    }

    private void initFrame() {

        setTitle("Generation Controller");

        JPanel c = new JPanel();
        JLabel cAmtLbl = new JLabel("Amount of Creatures: ");
        JTextField cAmt = new JTextField();
        cAmt.setColumns(10);
        this.cAmt = cAmt;
        c.add(cAmtLbl);
        c.add(cAmt);


        JPanel f = new JPanel();
        JLabel fAmtLbl = new JLabel("Amount of Foods: ");
        JTextField fAmt = new JTextField();
        fAmt.setColumns(10);
        this.fAmt = fAmt;
        f.add(fAmtLbl);
        f.add(fAmt);

        JPanel a = new JPanel();
        JLabel amtLbl = new JLabel("Amount of Generations: ");
        JTextField amt = new JTextField();
        amt.setColumns(10);
        this.aAmt = amt;
        a.add(amtLbl);
        a.add(amt);

        JButton startGen = new JButton("Start Generations");
        startGen.addActionListener(new SimulateMultGen(cAmt, fAmt, amt));

        JButton resetGen = new JButton("Reset Simulation");
        resetGen.addActionListener(new GenerationReset());

        // c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setAlignmentX(Component.CENTER_ALIGNMENT);
        // f.setLayout(new BoxLayout(f, BoxLayout.Y_AXIS));
        f.setAlignmentX(Component.CENTER_ALIGNMENT);
        // a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS));
        a.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(generationText);
        panel.add(c);
        panel.add(f);
        panel.add(a);
        panel.add(startGen);
        panel.add(resetGen);
        panel.add(currentGenSet);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(panel);

        setSize(300, 300);
        setVisible(false);
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // setResizable(false);
    }

    @EventHandler
    public void onGenerationStart(GenerationStartEvent e) {
        cAmt.setEnabled(false);
        generationText.setText("<html><p>Currently running generation: " + e.getCurrentGeneration() + "</p></html>");
    }

    @EventHandler
    public void onGenerationEnd(GenerationEndEvent e) {
        generationText.setText("<html><p>Finished generation: " + e.getCurrentGeneration() + "</p></html>");
    }

    @EventHandler
    public void onGenerationDeath(GenerationDeathEvent e) {
        generationText.setText("<html><p>Generation Death: " + e.getCurrentGeneration() + "</p></html>");
    }

    @EventHandler
    public void onGenSetStart(GenerationSetStartEvent e) {
        currentGenSet.setText("<html><p>Currently running " + e.getAmount() + " generation(s) with " + e.getCreatures() + " creature(s) and " + e.getFoods() + " food(s). </p></html>");
    }

    @EventHandler
    public void onGenSetFinish(GenerationSetFinishEvent e) {
        currentGenSet.setText("");
    }

    private class SimulateMultGen implements ActionListener {
        private final JTextField creatures;
        private final JTextField foods;
        private final JTextField amount;

        public SimulateMultGen(JTextField creatures, JTextField foods, JTextField amount) {
            this.creatures = creatures;
            this.foods = foods;
            this.amount = amount;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isIntC = NumberUtils.isInt(creatures.getText());
            boolean isIntF = NumberUtils.isInt(foods.getText());
            boolean isIntA = NumberUtils.isInt(amount.getText());

            // extracted numbers
            int c = isIntC ? (Integer.parseInt(creatures.getText()) <= 0 ? 1 : Integer.parseInt(creatures.getText())) : 1;
            int f = isIntF ? (Integer.parseInt(foods.getText()) <= 0 ? 1 : Integer.parseInt(foods.getText())) : 1;
            int a = isIntA ? (Integer.parseInt(amount.getText()) <= 0 ? 1 : Integer.parseInt(amount.getText())) : 1;

            sim.getGenerationManager().startGeneration(c, f, a, 5000);

            cAmt.setText("");
            fAmt.setText("");
            aAmt.setText("");
        }
    }

    private class GenerationReset implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            sim.getGenerationManager().resetGen();
            cAmt.setEnabled(true);
            cAmt.setText("");
            fAmt.setText("");
            aAmt.setText("");
            displayListener.getFrame().setTitle("Simulation - Not Started");
            generationText.setText("Simulation not started");
        }
    }
}
