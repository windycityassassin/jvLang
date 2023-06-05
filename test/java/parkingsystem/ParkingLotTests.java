package parkingsystem;

import charge.strategy.ChargeByTypeAndTimeStrategy;
import charge.strategy.ParkingChargeStrategy;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTests {
  /**
   * Test Constructor
   */
  @Test
  public void testParkingLot() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    assertEquals("Lot A", parkingLot.getLotID());
    assertEquals("123 Main St.", parkingLot.getAddress());
    assertEquals(0, parkingLot.getCapacity());
    assertEquals(0, parkingLot.getFee());
    assertEquals(ScanType.ENTRY, parkingLot.getScanType());
  }

  /**
   * Test Entry
   */
  @Test
  public void testEntry() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 100, ScanType.ENTRY, chargeStrategy);
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("ABC123", "permit", "license");
    Car car2 = new Car("ABC123", "permit1", "license");

    ParkingCharge charge = parkingLot.entry(car, OffsetDateTime.now());
    assertEquals(charge.getPermitID(), car.getPermit());
    assertEquals("$1.00", charge.getAmount().toString());

    ParkingCharge charge2 = parkingLot.entry(car2, OffsetDateTime.now());
    assertEquals(charge2.getPermitID(), car2.getPermit());
    assertEquals("$0.80", charge2.getAmount().toString());
  }

  /**
   * Test Register Observer
   */
  @Test
  public void testRegister() {
    ParkingOffice office = new ParkingOffice("test",
        "test", new ArrayList<Customer>(), new ArrayList<Car>(),
        new ArrayList<ParkingLot>(), new ArrayList<ParkingCharge>());
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 1, ScanType.ENTRY, chargeStrategy);

    ParkingObserver observer = new ParkingObserver(office);

    parkingLot.register(observer);

    assertEquals(1, parkingLot.getObservers().size());
  }

  /**
   * Test Unregister Observer
   */
  @Test
  public void testUnregister() {
    ParkingOffice office = new ParkingOffice("test",
        "test", new ArrayList<Customer>(), new ArrayList<Car>(),
        new ArrayList<ParkingLot>(), new ArrayList<ParkingCharge>());
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 1, ScanType.ENTRY, chargeStrategy);

    ParkingObserver observer = new ParkingObserver(office);

    parkingLot.register(observer);
    assertEquals(1, parkingLot.getObservers().size());
    parkingLot.unregister(observer);
    assertEquals(0, parkingLot.getObservers().size());
  }

  /**
   * Test notifyObservers
   */
  @Test
  public void testNotifyObservers() {
    ParkingOffice office = new ParkingOffice("test",
        "test", new ArrayList<Customer>(), new ArrayList<Car>(),
        new ArrayList<ParkingLot>(), new ArrayList<ParkingCharge>());

    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 1, ScanType.ENTRY, chargeStrategy);
    Address customerAddress = new Address();
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

    parkingLot.register(observer);
    parkingLot.notifyObservers(event);

    Money charge = office.getParkingCharges(customer);
    assertEquals(1, charge.getCents());
  }

  /**
   * Test toString
   */
  @Test
  public void testToString() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    String expected = "{" +
        " lotID='Lot A'" +
        ", address='123 Main St.'" +
        ", capacity='0'" +
        ", fee='0'" +
        ", scanType='ENTRY'" +
        "}";

    assertEquals(expected, parkingLot.toString());
  }

  /**
   * Test Equals
   */
  @Test
  public void testEquals() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    ParkingLot parkingLot2 = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    ParkingLot parkingLot3 = new ParkingLot("Lot B", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);

    assertTrue(parkingLot.equals(parkingLot2));
    assertFalse(parkingLot.equals(parkingLot3));
  }

  /**
   * Test HashCode
   */
  @Test
  public void testHashCode() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    ParkingLot parkingLot2 = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    ParkingLot parkingLot3 = new ParkingLot("Lot B", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);

    assertEquals(parkingLot.hashCode(), parkingLot2.hashCode());
    assertNotEquals(parkingLot.hashCode(), parkingLot3.hashCode());
  }

  /**
   * Test get/set lotID
   */
  @Test
  public void testGetSetLotID() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setLotID("test");
    assertEquals("test", parkingLot.getLotID());
  }

  /**
   * Test get/set Address
   */
  @Test
  public void testGetSetAddress() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setAddress("test");
    assertEquals("test", parkingLot.getAddress());
  }

  /**
   * Test get/set Capacity
   */
  @Test
  public void testGetSetCapacity() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setCapacity(99);
    assertEquals(99, parkingLot.getCapacity());
  }

  /**
   * Test get/set fee
   */
  @Test
  public void testGetSetFee() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setFee(1);
    assertEquals(1, parkingLot.getFee());
  }

  /**
   * Test get/set lotID
   */
  @Test
  public void testGetSetScanType() {
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    parkingLot.setScanType(ScanType.ENTRYEXIT);
    assertEquals(ScanType.ENTRYEXIT, parkingLot.getScanType());
  }
}
