package parkingsystem;

import charge.strategy.ChargeByTypeAndTimeStrategy;
import charge.strategy.ParkingChargeStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingServiceTests {
  private static List<Customer> customers;
  private static List<Car> cars;
  private static List<ParkingLot> lots;
  private static List<ParkingCharge> charges;
  private static ParkingOffice parkingOffice;

  /**
   * Fixture Setup
   */
  @BeforeAll
  public static void setUp() {
    customers = new ArrayList<Customer>();
    cars = new ArrayList<Car>();
    lots = new ArrayList<ParkingLot>();
    charges = new ArrayList<ParkingCharge>();
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    customers.add(customer);
    customers.add(new Customer("abcde", "George", "1 Main St.", "0987654321"));

    cars.add(new Car("ABC123", "permit", "license"));
    cars.add(new Car("ABC123", "permit1", "license1"));

    lots.add(new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy));
    lots.add(new ParkingLot("Lot B", "1 Main St.", 0, 0, ScanType.ENTRYEXIT, chargeStrategy));

    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    charges.add(new ParkingCharge("testID", "testLot", timestamp, charge));
    charges.add(new ParkingCharge("testID1", "testLot1", timestamp, charge));
    charges.add(new ParkingCharge("TestPermit", "testLot", timestamp, charge));
    charges.add(new ParkingCharge("TestPermit", "testLot", timestamp, charge));

    parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, cars, lots, charges);
  }

  /**
   * Test Constructor
   */
  @Test
  public void testParkingService() {
    ParkingService parkingService = new ParkingService(parkingOffice);
    assertNotNull(parkingService);
  }

  @Test
  public void testCommandCreation() {
    ParkingService parkingService = new ParkingService(parkingOffice);
    Map<String, Command> commands = parkingService.getCommands();
    assertTrue(commands.size() != 0);
  }

  @Test
  public void testValidCarCommand() throws Exception {
    ParkingService parkingService = new ParkingService(parkingOffice);
    String[] params = {"name=Jimmy", "address=123 Main St", "phone=1234567890", "license=license2",
        "carType=SUV"};
    String result = parkingService.performCommands("CAR", params);
    assertNotNull(result);
  }

  @Test
  public void testInvalidCarCommand() throws Exception {
    ParkingService parkingService = new ParkingService(parkingOffice);
    String[] params = {
        "address=123 Main St", "phone=1234567890", "license=license2", "carType=SUV"};
    assertThrows(InvalidParameterException.class, () -> {
      parkingService.performCommands("CAR", params);
    });
  }

  @Test
  public void testValidCustomerCommand() throws Exception {
    ParkingService parkingService = new ParkingService(parkingOffice);
    String[] params = {"name=Jimmy", "address=123 Main St", "phone=1234567890"};
    String result = parkingService.performCommands("CUSTOMER", params);
    assertEquals("TestID", result);
  }

  @Test
  public void testInvalidCustomerCommand() throws Exception {
    ParkingService parkingService = new ParkingService(parkingOffice);
    String[] params = {
        "address=123 Main St", "phone=1234567890"};
    assertThrows(InvalidParameterException.class, () -> {
      parkingService.performCommands("CAR", params);
    });
  }
}
