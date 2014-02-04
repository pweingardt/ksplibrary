package de.ksp.library.physics;

/**
 * Created by paul on 2/1/14, 10:34 AM.
 */
public class HohmannTransfer extends Maneuver {

    private double altitude;

    public HohmannTransfer update(double altitude) {
        this.altitude = altitude;
        return this;
    }

    public double getDeltaV(Orbit from) {
        Orbit target = getTargetOrbit(from);
        Body b = target.getBody();

        double r12 = from.getSemiMajorAxis() + target.getSemiMajorAxis();

        double v1 = Math.sqrt(b.getGravitationalParameter() / from.getSemiMajorAxis())
                * (Math.sqrt(2.0 * target.getSemiMajorAxis() / r12) - 1.0);

        double v2 = Math.sqrt(b.getGravitationalParameter() / target.getSemiMajorAxis())
                * (1.0 - Math.sqrt(2 * from.getSemiMajorAxis() / r12));

        return Math.abs(v1 + v2);
    }

    @Override
    public Orbit getTargetOrbit(Orbit from) {
        Orbit orbit = new Orbit(from);
        orbit.updateByCircularOrbit(orbit.getBody(), altitude);
        return orbit;
    }

}
