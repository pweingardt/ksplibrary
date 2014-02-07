package de.ksp.library.physics;

/**
 * Created by paul on 2/5/14.
 */
public class DeltaV {

    public final double min, max;

    public DeltaV(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public DeltaV(double dv) {
        this.min = this.max = dv;
    }

    public DeltaV add(DeltaV dv) {
        return new DeltaV(min + dv.min, max + dv.max);
    }

}
