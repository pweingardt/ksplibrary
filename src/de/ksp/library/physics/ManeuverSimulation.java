package de.ksp.library.physics;

/**
 * Created by paul on 2/3/14.
 */
public class ManeuverSimulation {

    public enum Type {LAUNCH, LANDING, HOHMANN_TRANSFER, INCLINATION_CHANGE, SPARE_DELTAV,
        INTERPLANETARY_TRANSFER}

    public final Type type;
    public final Orbit fromOrbit, targetOrbit;
    public final DeltaV deltaV;
    public final DeltaV totalDeltaV;

    public ManeuverSimulation(Maneuver m, Orbit fromOrbit, Orbit targetOrbit, DeltaV deltaV,
                              DeltaV totalDeltaV) {
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
        } else if(m instanceof InterplanetaryTransfer) {
            type = Type.INTERPLANETARY_TRANSFER;
        } else {
            type = null;
        }

        this.fromOrbit = fromOrbit;
        this.targetOrbit = targetOrbit;
        this.deltaV = deltaV;
        this.totalDeltaV = totalDeltaV;

    }

}
