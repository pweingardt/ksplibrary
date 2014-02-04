package de.ksp.library.physics;

import de.ksp.library.MathUtils;

/**
 * Created by paul on 2/4/14.
 */
public class InclinationChange extends Maneuver {

    private boolean perform = false;
    private double deltaI = 0.0;
    private double trueAnomaly = 0.0;

    public InclinationChange update(double deltaI, boolean perform) {
        return update(deltaI, 0.0, perform);
    }

    public InclinationChange update(double deltaI, double trueAnomaly, boolean perform) {
        this.deltaI = deltaI;
        this.trueAnomaly = trueAnomaly;
        this.perform = perform;
        return this;
    }

    @Override
    public double getDeltaV(Orbit from) {
        if(from.getEccentricity() == 0.0) {
            return 2.0 * Math.sqrt(from.getBody().getGravitationalParameter() / from.getSemiMajorAxis()) *
                    MathUtils.sind(deltaI / 2.0);
        } else {
            return 2.0 * MathUtils.sind(deltaI / 2.0) * Math.sqrt(1.0 - Math.pow(from.getEccentricity(), 2.0)) *
                    MathUtils.cosd(from.getArgumentOfPeriapsis() + trueAnomaly) * from.getMeanMotion() * from.getSemiMajorAxis()
                    / (1 + from.getEccentricity() * MathUtils.cosd(trueAnomaly));
        }
    }

    @Override
    public Orbit getTargetOrbit(Orbit from) {
        Orbit target = new Orbit(from);
        if(perform) {
            target.setInclination(target.getInclination() + deltaI);
        }
        return target;
    }
}
