package parkingsystem;

import charge.strategy.ChargeByTypeAndTimeStrategy;
import charge.strategy.ParkingChargeStrategy;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingObserverTests {
  private Address customerAddress;

  /**
   * Test Constructor
   */
  @Test
  public void testParkingObserver() {
    ParkingOffice office = new ParkingOffice("test",
        "test", new ArrayList<Customer>(), new ArrayList<Car>(),
        new ArrayList<ParkingLot>(), new ArrayList<ParkingCharge>());

    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 1, ScanType.ENTRY, chargeStrategy);
    Customer customer = office.register("abcd", "123 Main St.", "1234567890", customerAddress);
    Car car = customer.register("testLicense", CarType.SUV, office);
    LocalDate time = LocalDate.now();
    OffsetDateTime time2 = OffsetDateTime.now();

    ParkingPermit permit = new ParkingPermit("testLicense", car, time);

    ParkingEvent event = new ParkingEvent.Builder()
        .withParkingLot(parkingLot)
        .withPermit(permit)
        .withTimeIn(time2)
        .withTimeOut(time2)
        .build();

    ParkingObserver observer = new ParkingObserver(office);
    observer.notify(event);

    Money charges = office.getParkingCharges(customer);
    assertEquals(1, charges.getCents());
  }
}
