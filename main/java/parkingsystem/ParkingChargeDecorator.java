//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package parkingsystem;

import charge.decorator.ParkingChargeCalculator;

import java.io.Serializable;

public abstract class ParkingChargeDecorator extends ParkingCharge implements Serializable {
    private ParkingCharge calc;

    public ParkingChargeDecorator(ParkingChargeCalculator calc) {
        super();
        this.calc = this.calc;
    }

    public Double getDiscount(ParkingEvent parkingEvent) {
        return this.calc.getDiscount(parkingEvent);
    }
}
