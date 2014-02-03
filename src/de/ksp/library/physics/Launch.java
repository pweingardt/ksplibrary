package de.ksp.library.physics;

import java.util.HashMap;

/**
 * Created by paul on 2/1/14, 12:48 PM.
 */
public class Launch extends Maneuver {

    private static final HashMap<Body, LaunchOrbit> map = new HashMap<Body, LaunchOrbit>();
    static {
        map.put(Body.Kerbin, new LaunchOrbit(80000, 4550));
        map.put(Body.Mun, new LaunchOrbit(14000, 640));
        map.put(Body.Minmus, new LaunchOrbit(10000, 240));
        map.put(Body.Moho, new LaunchOrbit(50000, 1400));
        map.put(Body.Eeloo, new LaunchOrbit(10000, 840));
        map.put(Body.Eve, new LaunchOrbit(107000, 12000));
        map.put(Body.Gilly, new LaunchOrbit(10000, 35));
        map.put(Body.Duna, new LaunchOrbit(52000, 1380));
        map.put(Body.Ike, new LaunchOrbit(10000, 535));
        map.put(Body.Dres, new LaunchOrbit(12000, 555));
        map.put(Body.Laythe, new LaunchOrbit(65000, 2800));
        map.put(Body.Vall, new LaunchOrbit(15000, 1180));
        map.put(Body.Tylo, new LaunchOrbit(20000, 3070));
        map.put(Body.Bop, new LaunchOrbit(10000, 276));
        map.put(Body.Pol, new LaunchOrbit(10000, 180));
    }

    private LaunchOrbit launchOrbit;

    public Launch() {
        super(null);
    }

    public void update(Body body) {
        target = new Orbit();
        launchOrbit = map.get(body);
        target.updateByCircularOrbit(body, launchOrbit.altitude);
    }

    @Override
    public double getDeltaV() {
        return launchOrbit.deltav;
    }

    private static class LaunchOrbit {
        public final double altitude;
        public final double deltav;

        public LaunchOrbit(double altitude, double deltav) {
            this.altitude = altitude;
            this.deltav = deltav;
        }
    }

}
