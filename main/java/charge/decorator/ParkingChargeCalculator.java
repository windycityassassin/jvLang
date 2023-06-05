package charge.decorator;

import parkingsystem.Money;
import parkingsystem.ParkingLot;
import parkingsystem.ParkingPermit;

import java.time.OffsetDateTime;

public abstract class ParkingChargeCalculator {
  public ParkingChargeCalculator() {
  }

  public abstract Money getParkingCharge(
      ParkingPermit permit, ParkingLot parkingLot, OffsetDateTime entryTime, OffsetDateTime exitTime
  );
}
