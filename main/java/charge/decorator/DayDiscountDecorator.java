package charge.decorator;

import parkingsystem.Money;
import parkingsystem.ParkingLot;
import parkingsystem.ParkingPermit;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;

public class DayDiscountDecorator extends ParkingChargeCalculatorDecorator {
  public DayDiscountDecorator(ParkingChargeCalculator parkingChargeCalculator) {
    super(parkingChargeCalculator);
  }

  @Override
  public Money getParkingCharge(ParkingPermit permit, ParkingLot parkingLot, OffsetDateTime entryTime, OffsetDateTime exitTime) {
    Money rate = super.getParkingCharge(permit, parkingLot, entryTime, exitTime);

    if (entryTime.getDayOfWeek() == DayOfWeek.SATURDAY || entryTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
      // Free on Weekends
      rate.setCents(0);
    }
    return rate;
  }
}
