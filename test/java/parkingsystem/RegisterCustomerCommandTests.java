package parkingsystem;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import charge.strategy.ChargeByTypeAndTimeStrategy;
import charge.strategy.ParkingChargeStrategy;

public class RegisterCustomerCommandTests {
  private static List<Customer> customers;
  private static List<Car> cars;
  private static List<ParkingLot> lots;
  private static   List<ParkingCharge> charges;
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
  public void testRegisterCustomerCommand() {
    RegisterCustomerCommand customerCommand = new RegisterCustomerCommand(parkingOffice);
    assertNotNull(customerCommand);
  }

  @Test
  public void testExecute() {
    RegisterCustomerCommand customerCommand = new RegisterCustomerCommand(parkingOffice);
    Properties props = new Properties();
    props.put("name", "Erik");
    props.put("address", "123 Main St");
    props.put("phone", "1234567890");
    String id = customerCommand.execute(props);
    assertEquals("TestID", id);
  }

  @Test
  public void testExecuteWithInvalidProps() {
    RegisterCarCommand carCommand = new RegisterCarCommand(parkingOffice);
    Properties props = new Properties();
    assertThrows(InvalidParameterException.class, () -> {
      carCommand.execute(props);
    });

  }
}
