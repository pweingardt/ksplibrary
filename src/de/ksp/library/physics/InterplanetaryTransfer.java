package de.ksp.library.physics;

/**
 * Created by paul on 2/5/14.
 */
public class InterplanetaryTransfer extends Maneuver {

    private Body targetBody;

    public InterplanetaryTransfer update(Body target) {
        this.targetBody = target;
        return this;
    }

    @Override
    public DeltaV getDeltaV(Orbit from) {
        if(from.getBody().orbitingBody != targetBody.orbitingBody) {
            return new DeltaV(0);
        }

        Body bfrom  = from.getBody();
        Body parent = targetBody.orbitingBody;
        double r1 = from.getBody().semiMajorAxis;
        double r2 = targetBody.semiMajorAxis;
        double mu = bfrom.getGravitationalParameter();
        double mu2 = parent.getGravitationalParameter();
        double rpark = from.getSemiMajorAxis();
        double rsoi = from.getBody().getSOI();

        double th = Math.PI * Math.sqrt(Math.pow(r1 + r2, 3) / (8.0 * mu2));

        double pa = 180 - Math.sqrt(mu2 / r2) * th / r2 * 180.0 / Math.PI;

        System.out.println("Phase-Angle: " + pa);

        double dv = Math.sqrt(mu2 / r1) * (Math.sqrt(2.0 * r2 / (r1 + r2)) - 1);

        double v1 = Math.sqrt((rpark * (rsoi * dv * dv - 2.0 * mu) + 2.0 * rsoi * mu) / (rpark * rsoi));

        System.out.println("DeltaV: " + v1);

        double vorbit = Math.sqrt(mu / rpark);
        double ec = v1 * v1 / 2.0 - mu / rpark;
        double h2 = rpark * rpark * v1 * v1;
        double e = Math.sqrt(1 + (2.0 * ec * h2) / (mu * mu));
        double omega = 180.0 - Math.acos(1.0 / e) * 360.0 / (2.0 * Math.PI);

        System.out.println("Ejection-Angle: " + omega);

        return new DeltaV(v1 - vorbit);
    }

    @Override
    public Orbit getTargetOrbit(Orbit from) {
        return null;
    }
}
