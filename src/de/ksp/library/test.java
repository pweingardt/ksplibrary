package de.ksp.library;

import de.ksp.library.physics.Body;
import de.ksp.library.physics.HohmannTransfer;
import de.ksp.library.physics.Launch;
import de.ksp.library.physics.Orbit;

/**
 * Created by paul on 1/31/14, 11:10 PM.
 */
public class test {

    public static void main(String[] args) {
        Launch l = new Launch();
        l.update(Body.Kerbin);

        HohmannTransfer transfer = new HohmannTransfer(l);
        transfer.update(new Orbit().updateByCircularOrbit(Body.Kerbin, 1585180));

        HohmannTransfer transfer2 = new HohmannTransfer(transfer);
        transfer2.update(new Orbit().updateByCircularOrbit(Body.Kerbin, 80000));

        System.out.println("Transfer dv: " + transfer.getDeltaV());
        System.out.println("Transfer dv: " + transfer2.getDeltaV());
    }

}
