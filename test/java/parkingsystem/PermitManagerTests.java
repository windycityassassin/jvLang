package parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PermitManagerTests {
  /**
   * Test Generate New Permit
   */
  @Test
  public void testGenerateNewPermit() {
    PermitManager pm = new PermitManager();

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = pm.registerNewCar("testLicense", CarType.COMPACT, customer);
    assertNotNull(car.getPermit());
    assertEquals("testLicense", car.getLicense());
    assertEquals(customer, car.getOwner());
  }

  /**
   * Test Search Permit by License
   */
  @Test
  public void testGetPermitByLicense() {
    PermitManager pm = new PermitManager();

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = pm.registerNewCar("testLicense", CarType.COMPACT, customer);
    assertEquals(car, pm.getPermitByLicense("testLicense").getCar());
    assertEquals(customer, pm.getPermitByLicense("testLicense").getCar().getOwner());
  }

}
