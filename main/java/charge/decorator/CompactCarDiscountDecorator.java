package charge.decorator;


import parkingsystem.CarType;
import parkingsystem.Money;
import parkingsystem.ParkingLot;
import parkingsystem.ParkingPermit;

import java.time.OffsetDateTime;

public class CompactCarDiscountDecorator extends ParkingChargeCalculatorDecorator {
  public CompactCarDiscountDecorator(ParkingChargeCalculator parkingChargeCalculator) {
    super(parkingChargeCalculator);
  }

  @Override
  public Money getParkingCharge(ParkingPermit permit, ParkingLot parkingLot, OffsetDateTime entryTime, OffsetDateTime exitTime) {
    Money rate = super.getParkingCharge(permit, parkingLot, entryTime, exitTime);

    if (permit.getCar().getType() == CarType.COMPACT) {
      double discountValue = rate.getCents() * .80;
      rate.setCents((long) discountValue);
    }
    return rate;
  }
}
