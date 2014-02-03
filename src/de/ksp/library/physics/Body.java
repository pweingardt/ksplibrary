package de.ksp.library.physics;

import de.ksp.library.MathContants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by paul on 1/31/14, 10:01 PM.
 */
public class Body {

    public static final Body Kerbol = new Body("Kerbol",    0,              0,      0,      1.7565670e28,
            261600000,  432000, 0,      0,      null);

    public static final Body Moho = new Body("Moho",        52631383e2,     0.2,    7.0,    2.5263617e21,
            250000,     1210000, 0,      0,      Kerbol);

    public static final Body Eve = new Body("Eve",          983268454e1,     0.01,   2.1,   1.2244127e23,
            700000,     80500, 5,      7000,   Kerbol);

    public static final Body Gilly = new Body("Gilly",      31500000,       0.55,   12,     1.2420512e17,
            13000,      28255, 0,      0,      Eve);

    public static final Body Kerbin = new Body("Kerbin",    1359984026e1,   0,      0,      5.2915793e22,
            600000,     21600, 1,      5000,   Kerbol);

    public static final Body Mun = new Body("Mun",          12000000,       0,      0,      9.7600236e20,
            200000,     138984, 0,     0,      Kerbin);

    public static final Body Minmus = new Body("Minmus",    47000000,       0,      6,      2.6457897e19,
            60000,      40400, 0,      0,      Kerbin);

    public static final Body Duna = new Body("Duna",        2072615526e1,    0.05,   0.06,  4.5154812e21,
            320000,     65518, 0.2,    3000,   Kerbol);

    public static final Body Ike = new Body("Ike",          3200000,        0.03,   0.2,    2.7821949e20,
            130000,     65518, 0,      0,      Duna);

    public static final Body Dres = new Body("Dres",        408393482e2,    0.14,   5,      3.2191322e20,
            138000,     34800, 0,      0,      Kerbol);

    public static final Body Jool = new Body("Jool",        6877356032e1,    0.05,   1.304, 4.2332635e24,
            6000000,    36000, 15,     10000,  Kerbol);

    public static final Body Laythe = new Body("Laythe",    27184000,       0,      0,      2.9397663e22,
            500000,     52981, 0.8,    4000,   Jool);

    public static final Body Vall = new Body("Vall",        43152000,       0,      0,      3.1088028e21,
            300000,     105962, 0,      0,      Jool);

    public static final Body Tylo = new Body("Tylo",        68500000,       0,      0.025,  4.2332635e22,
            600000,     211926, 0,      0,      Jool);

    public static final Body Bop = new Body("Bop",          128500000,      0.24,   15,     3.7261536e19,
            65000,      544507, 0,      0,      Jool);

    public static final Body Pol = new Body("Pol",          179890000,      0.17,   4.25,   1.0813636e19,
            44000,      901903, 0,      0,      Jool);

    public static final Body Eeloo = new Body("Eeloo",      90118820e3,    0.26,   6.15,    1.1149358e21,
            210000,     19460, 0,      0,      Kerbol);

    public static final Body[] BODIES = new Body[] {Kerbol, Moho, Eve, Gilly, Kerbin, Mun, Minmus, Duna, Ike, Dres, Jool, Laythe, Vall, Tylo,
                    Bop, Pol, Eeloo};

    /**
     * The bodies name
     */
    public final String name;

    /**
     * The semi major axis in m
     */
    public final double semiMajorAxis;

    /**
     * The numerical eccentricity
     */
    public final double eccentricity;

    /**
     * The inclination in degrees
     */
    public final double inclination;

    /**
     * the mass in kg
     */
    public final double mass;

    /**
     * The radius in m
     */
    public final double radius;

    /**
     * The rotational period in seconds
     */
    public final double rotationalPeriod;

    /**
     * the atmosphere at sea level in atm
     */
    public final double atmosphere;

    /**
     * The scale height in meter
     */
    public final double scaleHeight;

    /**
     * The body which this body is orbiting
     */
    public final Body orbitingBody;

    /**
     * The moons
     */
    private final List<Body> moons;

    private Body(String name, double semiMajorAxis, double eccentricity, double inclination,
                 double mass,
                 double radius, double rotationalPeriod,
                 double atmosphere, double scaleHeight, Body orbitingBody) {
        this.name = name;

        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.inclination = inclination;
        this.mass = mass;
        this.radius = radius;

        this.rotationalPeriod = rotationalPeriod;
        this.atmosphere = atmosphere;
        this.scaleHeight = scaleHeight;
        this.orbitingBody = orbitingBody;

        moons = new ArrayList<Body>();

        if(orbitingBody != null) {
            orbitingBody.moons.add(this);
        }
    }

    /**
     * Returns the altitude for a synchronous orbit in m
     * @return
     */
    public double getSynchronousOrbitAltitude() {
        return Math.pow(getGravitationalParameter() / Math.pow(2.0 * Math.PI / rotationalPeriod, 2.0), 1.0 / 3.0) - radius;
    }

    /**
     * Returns the altitude for a semi synchronous orbit in m
     * @return
     */
    public double getSemiSynchronousOrbitAltitude() {
        return Math.pow(getGravitationalParameter() / Math.pow(4.0 * Math.PI / rotationalPeriod, 2.0), 1.0 / 3.0) - radius;
    }

    /**
     * Gets the gravitational parameter in km^3/s^2
     * @return
     */
    public double getGravitationalParameter() {
        return MathContants.gravitationalConstant * mass;
    }

    /**
     * Gets the atmospheric height
     * @return
     */
    public double getAtmosphericHeight() {
        return Math.log(1e-6) * scaleHeight * -1;
    }

    /**
     * Returns the orbital period in seconds
     * @return
     */
    public double getOrbitalPeriod() {
        if(orbitingBody != null) {
            return 2.0 * Math.PI * Math.sqrt(Math.pow(semiMajorAxis, 3.0) / orbitingBody.getGravitationalParameter());
        } else {
            return 0;
        }
    }

    /**
     * Returns the sphere of influence in meters
     * @return
     */
    public double getSOI() {
        if(orbitingBody != null) {
            return semiMajorAxis * Math.pow(mass / orbitingBody.mass, 2.0 / 5.0);
        } else {
            return 0;
        }
    }

    /**
     * Returns the surface gravity in m/s^2
     * @return
     */
    public double getSurfaceGravity() {
        return MathContants.gravitationalConstant * mass / (radius * radius);
    }

    /**
     * Returns an unmodifiable list of all moons / orbiting bodies
     * @return
     */
    public List<Body> getMoons() {
        return Collections.unmodifiableList(moons);
    }

}
