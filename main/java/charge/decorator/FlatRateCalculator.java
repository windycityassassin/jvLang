package charge.decorator;

import parkingsystem.Money;
import parkingsystem.ParkingLot;
import parkingsystem.ParkingPermit;

import java.time.OffsetDateTime;

public class FlatRateCalculator extends ParkingChargeCalculator {
  @Override
  public Money getParkingCharge(ParkingPermit permit, ParkingLot parkingLot, OffsetDateTime entryTime, OffsetDateTime exitTime) {
    return new Money(parkingLot.getFee());
  }
}
