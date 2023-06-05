package parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CustomerTests {
  /**
   * Test Constructor
   */
  @Test
  public void testCustomer() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    assertEquals("abcd", customer.getCustomerID());
    assertEquals("Jimmy", customer.getName());
    assertEquals("123 Main St.", customer.getAddress());
    assertEquals("1234567890", customer.getPhoneNumber());
  }

  /**
   * Test register
   */
  @Test
  public void testRegistry() {
    ParkingOffice parkingOffice = new ParkingOffice("testOffice", "testAddress", new ArrayList<Customer>(),
        new ArrayList<Car>(), new ArrayList<ParkingLot>(), new ArrayList<ParkingCharge>());

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    customer.register("123ABC", CarType.COMPACT, parkingOffice);
    ArrayList<Car> cars = customer.getCars();
    Car car = cars.get(0);
    assertEquals("123ABC", car.getLicense());
    assertEquals(CarType.COMPACT, car.getType());
  }

  /**
   * Test toString
   */
  @Test
  public void testToString() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");

    String expected = "{" +
        " customerID='" + "abcd" + "'" +
        ", name='" + "Jimmy" + "'" +
        ", address='" + "123 Main St." + "'" +
        ", phoneNumber='" + "1234567890" + "'" +
        "}";

    assertEquals(expected, customer.toString());
  }

  /**
   * Test Equals
   */
  @Test
  public void testEquals() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Customer customer2 = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Customer customer3 = new Customer("abcd", "Tom", "123 Main St.", "1234567890");

    assertTrue(customer.equals(customer2));
    assertFalse(customer.equals(customer3));
  }

  /**
   * Test HashCode
   */
  @Test
  public void testHashCode() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Customer customer2 = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Customer customer3 = new Customer("abcd", "Tom", "123 Main St.", "1234567890");

    assertEquals(customer.hashCode(), customer2.hashCode());
    assertNotEquals(customer.hashCode(), customer3.hashCode());
  }

  /**
   * Test get/set customerID
   */
  @Test
  public void testGetSetCustomerID() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    customer.setCustomerID("test");
    assertEquals("test", customer.getCustomerID());
  }

  /**
   * Test get/set name
   */
  @Test
  public void testGetSetName() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    customer.setName("test");
    assertEquals("test", customer.getName());
  }

  /**
   * Test get/set address
   */
  @Test
  public void testGetSetAddress() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    customer.setAddress("test");
    assertEquals("test", customer.getAddress());
  }

  /**
   * Test get/set phone number
   */
  @Test
  public void testGetSetPhoneNumber() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    customer.setPhoneNumber("test");
    assertEquals("test", customer.getPhoneNumber());
  }
}
