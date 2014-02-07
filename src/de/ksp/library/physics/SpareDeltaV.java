package de.ksp.library.physics;

/**
 * Created by paul on 2/4/14.
 */
public class SpareDeltaV extends Maneuver {

    private double spareDeltaV;

    public SpareDeltaV update(double spareDeltaV) {
        this.spareDeltaV = spareDeltaV;
        return this;
    }

    @Override
    public DeltaV getDeltaV(Orbit from) {
        return new DeltaV(spareDeltaV);
    }

    @Override
    public Orbit getTargetOrbit(Orbit from) {
        return from;
    }
}
