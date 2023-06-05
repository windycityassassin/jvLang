package charge.strategy;

import parkingsystem.Car;
import parkingsystem.CarType;
import parkingsystem.Money;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;

public class ChargeByTypeAndDayStrategy implements ParkingChargeStrategy {

  @Override
  public Money calculateCharge(Money baseRate, OffsetDateTime entryTime, Car car) {
    Money rate = new Money(baseRate);
    if (car.getType() == CarType.COMPACT) {
      double discountValue = baseRate.getCents() * .80;
      rate.setCents((long) discountValue);
    }

    if (entryTime.getDayOfWeek() == DayOfWeek.SATURDAY || entryTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
      // Free on Weekends
      rate.setCents(0);

    }

    return rate;
  }

}
