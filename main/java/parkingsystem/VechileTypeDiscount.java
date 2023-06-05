//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package parkingsystem;

import charge.decorator.ParkingChargeCalculator;

class VehicleTypeDiscount extends ParkingChargeDecorator {
    private Double discountRate = 0.1;

    public VehicleTypeDiscount(ParkingChargeCalculator calc) {
        super(calc);
    }

    public Double getDiscount(ParkingEvent parkingEvent) {
        if (this.isCompactCar(parkingEvent.getPermit().getCar().getType())) {
            System.out.println("Compact car discount rate applied");
            return super.getDiscount(parkingEvent) + this.discountRate;
        } else {
            System.out.println("Vehicle type discount cannot be applied to this vehicle");
            return super.getDiscount(parkingEvent);
        }
    }

    private boolean isCompactCar(CarType carType) {
        return carType == CarType.COMPACT;
    }
}
