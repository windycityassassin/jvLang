package parkingsystem;

import charge.strategy.ChargeByTypeAndTimeStrategy;
import charge.strategy.ParkingChargeStrategy;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingEventTests {
  /**
   * Test Constructor
   */
  @Test
  public void testParkingEvent() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "testPermit", "testLicense");
    LocalDate time = LocalDate.now();
    OffsetDateTime time2 = OffsetDateTime.now();

    ParkingPermit permit = new ParkingPermit("testLicense", car, time);

    ParkingEvent event = new ParkingEvent.Builder()
        .withParkingLot(parkingLot)
        .withPermit(permit)
        .withTimeIn(time2)
        .withTimeOut(time2)
        .build();

    assertEquals(parkingLot, event.getParkingLot());
    assertEquals(permit, event.getPermit());
    assertEquals(time2, event.getTimeIn());
    assertEquals(time2, event.getTimeOut());
  }
}
