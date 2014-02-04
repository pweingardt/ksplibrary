package de.ksp.library.physics;

/**
 * Created by paul on 1/31/14, 9:58 PM.
 */
public abstract class Maneuver {

    public abstract double getDeltaV(Orbit from);

    public abstract Orbit getTargetOrbit(Orbit from);

}
