package de.ksp.library.physics;

/**
 * Created by paul on 2/3/14.
 */
public class Landing extends Maneuver {

    private Launch.LaunchOrbit launchOrbit;

    public Landing() {
    }

    public Landing update(Body body) {
        launchOrbit = Launch.map.get(body);

        return this;
    }

    @Override
    public DeltaV getDeltaV(Orbit from) {
        return new DeltaV(0);
    }

    @Override
    public Orbit getTargetOrbit(Orbit from) {
        return null;
    }
}
