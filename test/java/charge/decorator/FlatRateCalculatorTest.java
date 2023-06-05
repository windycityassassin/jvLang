package charge.decorator;

import org.junit.jupiter.api.Test;
import parkingsystem.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlatRateCalculatorTest {

  @Test
  void getParkingCharge() {
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 1, ScanType.ENTRY, null);

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "testPermit", "testLicense");
    LocalDate time = LocalDate.now();
    OffsetDateTime offsetTime = OffsetDateTime.now();

    ParkingPermit permit = new ParkingPermit("testLicense", car, time);

    Money charge = new FlatRateCalculator().getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(1, charge.getCents());
  }
}