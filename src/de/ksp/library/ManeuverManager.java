package de.ksp.library;

import de.ksp.library.physics.Maneuver;
import de.ksp.library.physics.ManeuverSimulation;
import de.ksp.library.physics.Orbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by paul on 2/3/14.
 */
public class ManeuverManager {

    private ArrayList<Maneuver> maneuvers;
    private Orbit startOrbit;

    public ManeuverManager() {
        maneuvers = new ArrayList<Maneuver>();
    }

    public void setStartOrbit(Orbit startOrbit) {
        this.startOrbit = startOrbit;
    }

    public List<Maneuver> getManeuvers() {
        return Collections.unmodifiableList(maneuvers);
    }

    public void addManeuver(Maneuver maneuver) {
        maneuvers.add(maneuver);
    }

    public double getTotalDeltaV() {
        List<ManeuverSimulation> list = simulate();
        return list.get(list.size() - 1).totalDeltaV;
    }

    public List<ManeuverSimulation> simulate() {
        List<ManeuverSimulation> list = new ArrayList<ManeuverSimulation>();

        double deltav = 0.0;
        Orbit orbit = startOrbit;

        for(Maneuver m : maneuvers) {
            deltav += m.getDeltaV(orbit);

            list.add(new ManeuverSimulation(m, orbit, m.getTargetOrbit(orbit), m.getDeltaV(orbit), deltav));
            orbit = m.getTargetOrbit(orbit);
        }

        return list;
    }

}
