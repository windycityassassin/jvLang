package parkingsystem;

import charge.strategy.ChargeByTypeAndTimeStrategy;
import charge.strategy.ParkingChargeStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegisterCarCommandTests {
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
  public void testRegisterCarCommand() {
    RegisterCarCommand carCommand = new RegisterCarCommand(parkingOffice);
    assertNotNull(carCommand);
  }

  @Test
  public void testExecuteWithExistingCustomer() {
    RegisterCarCommand carCommand = new RegisterCarCommand(parkingOffice);
    Properties props = new Properties();
    props.put("name", "Jimmy");
    props.put("address", "123 Main St");
    props.put("phone", "1234567890");
    props.put("license", "license2");
    props.put("carType", "SUV");
    String permit = carCommand.execute(props);
    assertNotNull(permit);
  }

  @Test
  public void testExecuteWithNewCustomer() {
    RegisterCarCommand carCommand = new RegisterCarCommand(parkingOffice);
    Properties props = new Properties();
    props.put("name", "Erik");
    props.put("address", "123 Main St");
    props.put("phone", "1234567890");
    props.put("license", "license3");
    props.put("carType", "COMPACT");
    String permit = carCommand.execute(props);
    assertNotNull(permit);
  }

  @Test
  public void testExecuteWithInvalidProps() {
    RegisterCarCommand carCommand = new RegisterCarCommand(parkingOffice);
    Properties props = new Properties();
    props.put("address", "123 Main St");
    props.put("phone", "1234567890");
    props.put("license", "license2");
    props.put("carType", "SUV");
    assertThrows(InvalidParameterException.class, () -> {
      carCommand.execute(props);
    });

  }
}
