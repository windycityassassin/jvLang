package charge.decorator;

import parkingsystem.Money;
import parkingsystem.ParkingLot;
import parkingsystem.ParkingPermit;

import java.time.OffsetDateTime;

public abstract class ParkingChargeCalculatorDecorator extends ParkingChargeCalculator {
  protected ParkingChargeCalculator parkingChargeCalculator;

  public ParkingChargeCalculatorDecorator(ParkingChargeCalculator parkingChargeCalculator) {
    this.parkingChargeCalculator = parkingChargeCalculator;
  }

  @Override
  public Money getParkingCharge(
      ParkingPermit permit, ParkingLot parkingLot, OffsetDateTime entryTime, OffsetDateTime exitTime
  ) {
    return this.parkingChargeCalculator.getParkingCharge(permit, parkingLot, entryTime, exitTime);
  }
}
