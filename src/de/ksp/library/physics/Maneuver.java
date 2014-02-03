package de.ksp.library.physics;

/**
 * Created by paul on 1/31/14, 9:58 PM.
 */
public abstract class Maneuver {

    protected Maneuver previous;
    protected Orbit target = null;

    protected Maneuver(Maneuver previous) {
        this.previous = previous;
    }

    public abstract double getDeltaV();

    public double getTotalDeltaV() {
        if(previous == null) {
            return getDeltaV();
        } else {
            return previous.getTotalDeltaV() + getDeltaV();
        }
    }

    public Orbit getTargetOrbit() {
        return target;
    }

}
