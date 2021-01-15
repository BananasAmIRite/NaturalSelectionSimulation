package org.BananasAmIRite.NaturalSelectionSimulation;

import org.BananasAmIRite.NaturalSelectionSimulation.utils.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        int SIMULATION_WIDTH = 10;
        int SIMULATION_HEIGHT = 10;

        // argument processing :D
        for (String arg : args) {
            if (arg.startsWith("-sw")) {
                SIMULATION_WIDTH = NumberUtils.isNumericInt(arg.replace("-sw", "")) ?
                        Integer.parseInt(arg.replace("-sw", "")) :
                        SIMULATION_WIDTH;
            } else if (arg.startsWith("-sh")) {
                SIMULATION_HEIGHT = NumberUtils.isNumericInt(arg.replace("-sh", "")) ?
                        Integer.parseInt(arg.replace("-sh", "")) :
                        SIMULATION_HEIGHT;
            } else if (arg.equals("-runAnother")) { // just for funs and testing
                Runtime.getRuntime().exec("java -jar " + new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                        .toURI()).getPath() + " -sw" + SIMULATION_WIDTH + " -sh" + SIMULATION_HEIGHT);
                System.exit(0);
            }
        }

        Simulation sim = new Simulation(SIMULATION_WIDTH, SIMULATION_HEIGHT);
 

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
