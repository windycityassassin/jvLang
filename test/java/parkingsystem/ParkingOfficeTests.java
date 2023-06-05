package parkingsystem;

import charge.strategy.ChargeByTypeAndTimeStrategy;
import charge.strategy.ParkingChargeStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingOfficeTests {
  private static List<Customer> customers;
  private static List<Car> cars;
  private static List<ParkingLot> lots;
  private static List<ParkingCharge> charges;

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
  }

  /**
   * Test Constructor
   */
  @Test
  public void testParkingOffice() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, cars, lots, charges);
    assertEquals("testOffice", parkingOffice.getName());
    assertEquals("testAddress", parkingOffice.getAddress());
    assertEquals(customers, parkingOffice.getCustomers());
    assertEquals(cars, parkingOffice.getCars());
    assertEquals(lots, parkingOffice.getLots());
    assertEquals(charges, parkingOffice.getCharges());

  }

  /**
   * Test register customer
   */
  @Test
  public void testRegisterCustomer() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", new ArrayList<Customer>(), cars,
        lots, charges);

    Address customerAddress = new Address();
    Customer customer = parkingOffice.register("Jimmy", "123 Main St.", "1234567890", customerAddress);
    List<Customer> customerList = new ArrayList<Customer>();
    customerList.add(customer);

    assertEquals("Jimmy", customer.getName());
    assertEquals("123 Main St.", customer.getAddress());
    assertEquals("1234567890", customer.getPhoneNumber());
    assertEquals(customerList, parkingOffice.getCustomers());
  }

  /**
   * Test register Car
   */
  @Test
  public void testRegisterCar() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, new ArrayList<Car>(),
        lots, charges);
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");

    Car car = parkingOffice.register(customer, "123abc", CarType.COMPACT);
    List<Car> carList = new ArrayList<Car>();
    carList.add(car);

    assertEquals(customer, car.getOwner());
    assertEquals("123abc", car.getLicense());
    assertEquals(CarType.COMPACT, car.getType());
    assertEquals(carList, parkingOffice.getCars());
  }

  /**
   * Test Get Customer
   */
  @Test
  public void testGetCustomer() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, cars, lots, charges);
    Address customerAddress = new Address();
    Customer customer = parkingOffice.register("FindMe", "123 Main St.", "1234567890", customerAddress);

    assertEquals(customer, parkingOffice.getCustomer("FindMe"));
  }

  /**
   * Test Add Charge
   */
  @Test
  public void testAddCharge() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, cars, lots, charges);

    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    ParkingCharge pCharge = new ParkingCharge("testID", "testLot", timestamp, charge);

    assertEquals(charge, parkingOffice.addCharge(pCharge));
  }

  /**
   * Test getCustomerIDs
   */
  @Test
  public void testGetCustomerIDs() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress",
        ParkingOfficeTests.customers, cars, lots, charges);

    List<String> customerIDs = parkingOffice.getCustomerIDs();
    assertTrue(customerIDs.size() == ParkingOfficeTests.customers.size());

    for (Customer customer : ParkingOfficeTests.customers) {
      assertTrue(customerIDs.contains(customer.getCustomerID()));
    }
  }

  /**
   * Test getPermitIDs
   */
  @Test
  public void testGetPermitIDs() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress",
        customers, ParkingOfficeTests.cars, lots, charges);

    List<String> permits = parkingOffice.getPermitIDs();
    assertTrue(permits.size() == ParkingOfficeTests.cars.size());

    for (Car car : ParkingOfficeTests.cars) {
      assertTrue(permits.contains(car.getPermit()));
    }
  }

  /**
   * Test getPermitIDs with Customer param
   */
  @Test
  public void testGetPermitIDsPerCustomer() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress",
        customers, cars, lots, charges);

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");

    Car car = customer.register("123ABC", CarType.SUV, parkingOffice);
    Car car2 = customer.register("ABC123", CarType.SUV, parkingOffice);

    List<String> permits = parkingOffice.getPermitIDs(customer);
    assertTrue(permits.size() == 2);
    assertTrue(permits.contains(car.getPermit()));
    assertTrue(permits.contains(car2.getPermit()));
  }

  /**
   * Test Park method
   */
  @Test
  public void testPark() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress",
        customers, cars, lots, charges);

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");

    parkingOffice.register(customer, "testLicense", CarType.COMPACT);

    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();

    ParkingLot lot = new ParkingLot("Lot A", "123 Main St.", 0, 0, ScanType.ENTRY, chargeStrategy);
    OffsetDateTime time = OffsetDateTime.of(2022, 10, 2, 20, 0, 0, 0, ZoneOffset.UTC);

    ParkingCharge charge = parkingOffice.park(time, "testLicense", lot);

    assertEquals("$0.00", charge.getAmount().toString());
  }

  /**
   * Test getParkingCharges method with PermitID
   */
  @Test
  public void testParkingChargesPermitID() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress",
        customers, cars, lots, ParkingOfficeTests.charges);

    assertEquals("$64.20", parkingOffice.getParkingCharges("TestPermit").toString());
  }

  /**
   * Test getParkingCharges method with Customer
   */
  @Test
  public void testParkingChargesCustomer(Address customerAddress) {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress",
        customers, cars, lots, ParkingOfficeTests.charges);

    Customer customer = parkingOffice.register("Joe", "123 Main St", "1234567890", customerAddress);
    Car car1 = customer.register("test1", CarType.SUV, parkingOffice);
    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    ParkingLot lot = new ParkingLot("Lot A", "123 Main St.", 0, 1000, ScanType.ENTRY, chargeStrategy);

    OffsetDateTime time = OffsetDateTime.of(2022, 10, 2, 20, 0, 0, 0, ZoneOffset.UTC);
    parkingOffice.park(time, car1.getLicense(), lot);
    assertEquals("$10.00", parkingOffice.getParkingCharges(customer).toString());
  }

  /**
   * Test getters and setters
   */
  @Test
  public void testGetSetName() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, cars, lots, charges);

    parkingOffice.setName("test");
    assertEquals("test", parkingOffice.getName());
  }

  @Test
  public void testGetSetAddress() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, cars, lots, charges);

    parkingOffice.setAddress("test");
    assertEquals("test", parkingOffice.getAddress());
  }

  @Test
  public void testGetSetCustomers() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", new ArrayList<Customer>(), cars,
        lots, charges);

    parkingOffice.setCustomers(ParkingOfficeTests.customers);
    ;
    assertEquals(ParkingOfficeTests.customers, parkingOffice.getCustomers());
  }

  @Test
  public void testGetSetCars() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, new ArrayList<Car>(),
        lots, charges);

    parkingOffice.setCars(ParkingOfficeTests.cars);
    assertEquals(ParkingOfficeTests.cars, parkingOffice.getCars());
  }

  @Test
  public void testGetSetLots() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, cars,
        new ArrayList<ParkingLot>(), charges);

    parkingOffice.setLots(ParkingOfficeTests.lots);
    assertEquals(ParkingOfficeTests.lots, parkingOffice.getLots());
  }

  @Test
  public void testGetSetCharges() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", customers, cars, lots,
        new ArrayList<ParkingCharge>());

    parkingOffice.setCharges(ParkingOfficeTests.charges);
    assertEquals(ParkingOfficeTests.charges, parkingOffice.getCharges());
  }
}
