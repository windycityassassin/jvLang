package charge.strategy;

import parkingsystem.Car;
import parkingsystem.CarType;
import parkingsystem.Money;

import java.time.OffsetDateTime;

public class ChargeByTypeAndTimeStrategy implements ParkingChargeStrategy {

  @Override
  public Money calculateCharge(Money baseRate, OffsetDateTime entryTime, Car car) {
    Money rate = new Money(baseRate);
    if (car.getType() == CarType.COMPACT) {
      double discountValue = baseRate.getCents() * .80;
      rate.setCents((long) discountValue);
    }

    if (entryTime.getHour() > 6 && entryTime.getHour() < 19) {
      // Add two dollar surcharge during peak hours
      rate.setCents(baseRate.getCents() + 200);
    }

    return rate;
  }

}
