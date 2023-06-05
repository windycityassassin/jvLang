package charge.strategy;

import parkingsystem.Car;
import parkingsystem.Money;

import java.time.OffsetDateTime;

public interface ParkingChargeStrategy {
  public Money calculateCharge(Money baseRate, OffsetDateTime entryTime, Car car);
}
