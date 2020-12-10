package org.BananasAmIRite.NaturalSelectionSimulation;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(args));
        Simulation sim = new Simulation(10, 10);
        System.out.println(sim.getMap().getMap());
        System.out.println("test");


 

//        System.out.println("Launching Test GUI...");
//
//
//        JFrame f = new JFrame();
//
//        JPanel map  = new JPanel();
//        map.setLayout(new GridLayout(5, 5));
//  
//        map.add(new JButton("eee"));
//        map.add(new JButton("eee"));
//        map.add(new JButton("eee"));
//
//        f.setSize(400, 500);
//        f.setLayout(new FlowLayout());
//        f.add(map);
//        f.add(new JButton("Aaaaa"));
//        f.setTitle("Natural Selection");
//        f.setVisible(true);

        /*
         *
         * Creature food sensing
         *
         * foodLoop:
         * if energy less or equal to energyToGoHome:
         *   goHome();
         *   return;
         *
         * findFood();
         * wait(convertSpeedCooldown(speed));
         * foodLoop();
         *
         * */
    }
}
