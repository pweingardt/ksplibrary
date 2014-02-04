package de.ksp.library;

/**
 * Created by paul on 1/31/14, 10:49 PM.
 *
 * Math Utils
 */
public class MathUtils {

    public static final double gravitationalConstant = 6.674e-11;

    public static double degreeToRadian(double degree) {
        return 2.0 * Math.PI / 360.0 * degree;
    }

    public static double sind(double degree) {
        return Math.sin(degreeToRadian(degree));
    }

    public static double cosd(double degree) {
        return Math.cos(degreeToRadian(degree));
    }

}
