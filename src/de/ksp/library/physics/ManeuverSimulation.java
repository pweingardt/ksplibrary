package de.ksp.library.physics;

/**
 * Created by paul on 2/3/14.
 */
public class ManeuverSimulation {

    public enum Type {LAUNCH, LANDING, HOHMANN_TRANSFER, INCLINATION_CHANGE, SPARE_DELTAV}

    public final Type type;
    public final Orbit fromOrbit, targetOrbit;
    public final double deltaV;
    public final double totalDeltaV;

    public ManeuverSimulation(Maneuver m, Orbit fromOrbit, Orbit targetOrbit, double deltaV,
                              double totalDeltaV) {
        if(m instanceof Launch) {
            type = Type.LAUNCH;
        } else if(m instanceof Landing) {
            type = Type.LANDING;
        } else if(m instanceof  HohmannTransfer) {
            type = Type.HOHMANN_TRANSFER;
        } else if(m instanceof InclinationChange) {
            type = Type.INCLINATION_CHANGE;
        } else if(m instanceof SpareDeltaV) {
            type = Type.SPARE_DELTAV;
        } else {
            type = null;
        }

        this.fromOrbit = fromOrbit;
        this.targetOrbit = targetOrbit;
        this.deltaV = deltaV;
        this.totalDeltaV = totalDeltaV;

    }

}
