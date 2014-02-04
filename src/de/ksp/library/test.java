package de.ksp.library;

import de.ksp.library.physics.*;

/**
 * Created by paul on 1/31/14, 11:10 PM.
 */
public class test {

    public static void main(String[] args) {
        ManeuverManager manager = new ManeuverManager();
        manager.addManeuver(new Launch().update(Body.Kerbin, 80000, 5.0, 3.5));
        manager.addManeuver(new HohmannTransfer().update(2868750));
        manager.addManeuver(new InclinationChange().update(4.0, false));

        for(ManeuverSimulation sim : manager.simulate()) {
            System.out.println("Type: " + sim.type.toString());
            if(sim.fromOrbit != null) {
                System.out.println("From: " + sim.fromOrbit.toString());
            }
            if(sim.targetOrbit != null) {
                System.out.println("To: " + sim.targetOrbit.toString());
            }
            System.out.println("Delta-v: " + sim.deltaV);
            System.out.println("Total DeltaV: " + sim.totalDeltaV);
            System.out.println("-------");
        }
    }

}
