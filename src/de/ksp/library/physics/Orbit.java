package de.ksp.library.physics;

/**
 * Created by paul on 2/1/14, 10:34 AM.
 *
 * This class describes an orbit around a body
 */
public class Orbit {

    /**
     * The semi major axis
     */
    private double semiMajorAxis;

    /**
     * The eccentricity
     */
    private double eccentricity;

    /**
     * The orbiting body
     */
    private Body body;

    /**
     * The inclination in degrees
     */
    private double inclination = 0;

    /**
     * The lan in degrees
     */
    private double lan = 0;

    /**
     * The argument of periapsis in degrees
     */
    private double argumentOfPeriapsis = 0;

    /**
     * Empty constructor. Use update methods
     */
    public Orbit() {

    }

    /**
     * Copys an orbit
     * @param orbit
     */
    public Orbit(Orbit orbit) {
        this.eccentricity = orbit.eccentricity;
        this.semiMajorAxis = orbit.semiMajorAxis;
        this.inclination = orbit.inclination;
        this.lan = orbit.lan;
        this.argumentOfPeriapsis = orbit.argumentOfPeriapsis;
        this.body = orbit.body;
    }

    /**
     * Updates the orbit with the given periapsis and apoapsis.
     *
     * No units necessary, but preferable with m.
     * @param body
     * @param periapsis
     * @param apoapsis
     */
    public Orbit updateByPeriapsisApoapsis(Body body, double periapsis, double apoapsis) {
        if(apoapsis < periapsis) {
            double temp = apoapsis;
            apoapsis = periapsis;
            periapsis = temp;
        }
        this.eccentricity = (apoapsis - periapsis) / (apoapsis + periapsis);
        this.semiMajorAxis = (apoapsis + periapsis) / (2.0);
        this.body = body;

        return this;
    }

    /**
     * Updates the orbit with the given eccentricity and semi major axis.
     *
     * No units necessary, but preferable with m.
     * @param body
     * @param eccentricity
     * @param semiMajorAxis
     */
    public Orbit updateByAxisEccentricity(Body body, double eccentricity, double semiMajorAxis) {
        this.eccentricity = eccentricity;
        this.semiMajorAxis = semiMajorAxis;
        this.body = body;

        return this;
    }

    /**
     * Creates a circular orbit with the given altitude
     * @param body
     * @param altitude
     * @return
     */
    public Orbit updateByCircularOrbit(Body body, double altitude) {
        this.eccentricity = 0;
        this.semiMajorAxis = body.radius + altitude;
        this.body = body;

        return this;
    }

    /**
     * Returns the periapsis
     * @return
     */
    public double getPeriapsis() {
        return (1.0 - eccentricity) * semiMajorAxis;
    }

    /**
     * Returns the apoapsis
     * @return
     */
    public double getApoapsis() {
        return (1.0 + eccentricity) * semiMajorAxis;
    }

    /**
     * Returns the semi major axis
     * @return
     */
    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    /**
     * Returns the eccentricity
     * @return
     */
    public double getEccentricity() {
        return eccentricity;
    }

    /**
     * Returns the oribiting body
     * @return
     */
    public Body getBody() {
        return body;
    }

    /**
     * The inclination in degrees
     */
    public double getInclination() {
        return inclination;
    }

    /**
     * Sets the inclination in degrees
     * @param inclination
     */
    public void setInclination(double inclination) {
        this.inclination = inclination;
    }

    /**
     * The lan in degrees
     */
    public double getLan() {
        return lan;
    }

    /**
     * sets the longitude of the ascending node in degrees
     * @param lan
     */
    public void setLan(double lan) {
        this.lan = lan;
    }

    /**
     * The argument of periapsis in degrees
     */
    public double getArgumentOfPeriapsis() {
        return argumentOfPeriapsis;
    }

    /**
     * Sets the argument of periapsis in degrees
     * @param argumentOfPeriapsis
     */
    public void setArgumentOfPeriapsis(double argumentOfPeriapsis) {
        this.argumentOfPeriapsis = argumentOfPeriapsis;
    }

    public double getMeanMotion() {
        return Math.sqrt(body.getGravitationalParameter() / Math.pow(semiMajorAxis, 3.0));
    }

    public String toString() {
        return String.format("(%s | %.2f | %.2f | %.2f | %.2f | %.2f)", body.name, semiMajorAxis, eccentricity, inclination, lan, argumentOfPeriapsis);
    }
}
