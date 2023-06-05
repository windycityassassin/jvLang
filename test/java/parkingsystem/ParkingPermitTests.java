package parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ParkingPermitTests {
  /**
   * Test Generate New Permit
   */
  @Test
  public void testParkingPermit() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "testPermit", "testLicense");
    LocalDate time = LocalDate.now();

    ParkingPermit permit = new ParkingPermit("testLicense", car, time);
    assertEquals(permit.getCar(), car);
    assertEquals(permit.getExpirationDate(), time);
    assertEquals(permit.getId(), "testLicense");
  }
}
