package parkingsystem;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CarTests {
  /**
   * Test Constructor
   */
  @Test
  public void testParkingLot() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "permit", "license");
    assertEquals("permit", car.getPermit());
    assertEquals(LocalDate.now(), car.getPermitExpiration());
    assertEquals("license", car.getLicense());
    assertEquals(CarType.SUV, car.getType());
    assertEquals(customer, car.getOwner());
  }

  /**
   * Test toString
   */
  @Test
  public void testToString() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "permit", "license");

    String expected = "{" +
        " permit='" + car.getPermit() + "'" +
        ", permitExpiration='" + car.getPermitExpiration() + "'" +
        ", license='" + car.getLicense() + "'" +
        ", type='" + car.getType() + "'" +
        ", owner='" + car.getOwner() + "'" +
        "}";

    assertEquals(expected, car.toString());
  }

  /**
   * Test Equals
   */
  @Test
  public void testEquals() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("ABC123", "permit", "license");
    Car car2 = new Car("ABC123", "permit", "license");
    Car car3 = new Car("ABC123", "permit1", "license");

    assertTrue(car.equals(car2));
    assertFalse(car.equals(car3));
  }

  /**
   * Test HashCode
   */
  @Test
  public void testHashCode() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("ABC123", "permit", "license");
    Car car2 = new Car("ABC123", "permit", "license");
    Car car3 = new Car("ABC123", "permit1", "license");

    assertEquals(car.hashCode(), car2.hashCode());
    assertNotEquals(car.hashCode(), car3.hashCode());
  }

  /**
   * Test get/set Permit
   */
  @Test
  public void testGetSetPermit() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "permit", "license");
    car.setPermit("test");
    assertEquals("test", car.getPermit());
  }

  /**
   * Test get/set Permit Expiration
   */
  @Test
  public void testGetSetPermitExpiration() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "permit", "license");
    car.setPermitExpiration(LocalDate.now().plusDays(1));
    assertEquals(LocalDate.now().plusDays(1), car.getPermitExpiration());
  }

  /**
   * Test get/set License
   */
  @Test
  public void testGetSetLicense() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "permit", "license");
    car.setLicense("test");
    assertEquals("test", car.getLicense());
  }

  /**
   * Test get/set Type
   */
  @Test
  public void testGetSetType() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "permit", "license");
    car.setType(CarType.COMPACT);
    assertEquals(CarType.COMPACT, car.getType());
  }

  /**
   * Test get/set Permit
   */
  @Test
  public void testGetSetOwner() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "permit", "license");
    Customer newCustomer = new Customer("test", "test", "test", "test");
    car.setOwner(newCustomer);
    assertEquals(newCustomer, car.getOwner());
  }

}
