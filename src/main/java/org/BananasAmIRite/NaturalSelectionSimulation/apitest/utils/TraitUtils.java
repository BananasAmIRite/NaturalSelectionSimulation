package org.BananasAmIRite.NaturalSelectionSimulation.apitest.utils;

import org.BananasAmIRite.NaturalSelectionSimulation.api.traitsapi.Trait;
import org.BananasAmIRite.NaturalSelectionSimulation.utils.NumberUtils;

public class TraitUtils {
    /**
     * Generates a new reproduction value
     *
     * @param v1 lower bounds of the change in reproduction value
     * @param v2 high bounds of the chang ein reproduction value
     *
     * */
    public static double getReproductionValue(double v1, double v2, double curValue) {
        return curValue + NumberUtils.randDouble(v1, v2);
    }
}
