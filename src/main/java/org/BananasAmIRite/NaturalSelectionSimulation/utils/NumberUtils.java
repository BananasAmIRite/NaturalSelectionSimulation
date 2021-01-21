package org.BananasAmIRite.NaturalSelectionSimulation.utils;

import java.util.Random;

public class NumberUtils {
    public static float randFloat(float min, float max) {
        Random rand = new Random();

        return rand.nextFloat() * (max - min) + min;
    }

    public static float randFloat(float min, float max, Random randGen) {
        return randGen.nextFloat() * (max - min) + min;
    }

    public static double randDouble(double min, double max) {
        Random rand = new Random();

        return rand.nextDouble() * (max - min) + min;
    }

    public static double randDouble(double min, double max, Random randGen) {

        return randGen.nextDouble() * (max - min) + min;
    }

    public static int randInt(int min, int max) {
        return (int)(Math.random() * (max - min) + min);
    }

    public static long randLong(long min, long max) {
        Random rand = new Random();

        return rand.nextLong() * (max - min) + min;
    }

    public static long randLong(long min, long max, Random randGen) {

        return randGen.nextLong() * (max - min) + min;
    }

    public static boolean isNumericInt(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Inverts numbers with bounds
     *
     * @param b1 lower bounds
     * @param b2 higher bounds
     * @param value value to invert
     * */
    public static double invertNumber(double b1, double b2, double value) {
        return b2-value+b1;
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s); // would throw an error if not integer
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
