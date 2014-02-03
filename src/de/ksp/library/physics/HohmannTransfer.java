package de.ksp.library.physics;

/**
 * Created by paul on 2/1/14, 10:34 AM.
 */
public class HohmannTransfer extends Maneuver {

    public HohmannTransfer(Maneuver previous) {
        super(previous);
    }

    public void update(Orbit targetOrbit) {
        this.target = targetOrbit;
    }

    @Override
    public double getDeltaV() {
        if(previous == null) {
            return -1;
        }

        if(previous.getTargetOrbit().getBody() != target.getBody()) {
            return -1;
        }

        Body b = target.getBody();

        Orbit from = previous.getTargetOrbit();

        double r12 = from.getSemiMajorAxis() + target.getSemiMajorAxis();

        double v1 = Math.sqrt(b.getGravitationalParameter() / from.getSemiMajorAxis())
                * (Math.sqrt(2.0 * target.getSemiMajorAxis() / r12) - 1.0);

        double v2 = Math.sqrt(b.getGravitationalParameter() / target.getSemiMajorAxis())
                * (1.0 - Math.sqrt(2 * from.getSemiMajorAxis() / r12));

        return Math.abs(v1 + v2);
    }
}
