package charge.decorator;

import org.junit.jupiter.api.Test;
import parkingsystem.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeChargeDecoratorTest {

  @Test
  void getParkingChargePeakHours() {
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 100, ScanType.ENTRY, null);

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "testPermit", "testLicense");
    LocalDate time = LocalDate.now();
    OffsetDateTime offsetTime = OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC);

    ParkingPermit permit = new ParkingPermit("testLicense", car, time);

    Money charge = new TimeChargeDecorator(new FlatRateCalculator()).getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(300, charge.getCents());
  }

  @Test
  void getParkingChargeOffHours() {
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 100, ScanType.ENTRY, null);

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "testPermit", "testLicense");
    LocalDate time = LocalDate.now();
    OffsetDateTime offsetTime = OffsetDateTime.of(2022, 10, 2, 20, 0, 0, 0, ZoneOffset.UTC);

    ParkingPermit permit = new ParkingPermit("testLicense", car, time);

    Money charge = new TimeChargeDecorator(new FlatRateCalculator()).getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(100, charge.getCents());
  }
}