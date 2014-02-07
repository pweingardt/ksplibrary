package de.ksp.library;

import de.ksp.library.physics.*;

/**
 * Created by paul on 1/31/14, 11:10 PM.
 */
public class test {

    public static void main(String[] args) {
        ManeuverManager manager = new ManeuverManager();
        manager.addManeuver(new Launch().update(Body.Kerbin, 200000, 5.0, 3.5));
        manager.addManeuver(new InterplanetaryTransfer().update(Body.Moho));

        for(ManeuverSimulation sim : manager.simulate()) {
            System.out.println("Type: " + sim.type.toString());
            if(sim.fromOrbit != null) {
                System.out.println("From: " + sim.fromOrbit.toString());
            }
            if(sim.targetOrbit != null) {
                System.out.println("To: " + sim.targetOrbit.toString());
            }
            System.out.println("min. Delta-v: " + sim.deltaV.min);
            System.out.println("max. Delta-v: " + sim.deltaV.max);
            System.out.println("Total DeltaV: " + sim.totalDeltaV.min);
            System.out.println("-------");
        }
    }

}
