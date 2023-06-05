package charge.decorator;

import org.junit.jupiter.api.Test;
import parkingsystem.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayDiscountDecoratorTest {

  @Test
  void getParkingChargeWeekday() {
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 100, ScanType.ENTRY, null);

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "testPermit", "testLicense");
    LocalDate time = LocalDate.now();
    OffsetDateTime offsetTime = OffsetDateTime.of(2022, 10, 3, 20, 0, 0, 0, ZoneOffset.UTC);

    ParkingPermit permit = new ParkingPermit("testLicense", car, time);

    Money charge = new DayDiscountDecorator(new FlatRateCalculator()).getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(100, charge.getCents());
  }

  @Test
  void getParkingChargeWeekend() {
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 100, ScanType.ENTRY, null);

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "testPermit", "testLicense");
    LocalDate time = LocalDate.now();
    OffsetDateTime offsetTime = OffsetDateTime.of(2022, 10, 2, 20, 0, 0, 0, ZoneOffset.UTC);

    ParkingPermit permit = new ParkingPermit("testLicense", car, time);

    Money charge = new DayDiscountDecorator(new FlatRateCalculator()).getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(0, charge.getCents());
  }
}