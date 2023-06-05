package parkingsystem;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class ParkingChargeTests {
  /**
   * Test Constructor
   */
  @Test
  public void testParkingCharge() {
    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    ParkingCharge pCharge = new ParkingCharge("testID", "testLot", timestamp, charge);
    assertEquals("testID", pCharge.getPermitID());
    assertEquals("testLot", pCharge.getLotID());
    assertEquals(timestamp, pCharge.getIncurred());
    assertEquals(charge, pCharge.getAmount());
  }

  /**
   * Test toString
   */
  @Test
  public void testToString() {
    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    ParkingCharge pCharge = new ParkingCharge("testID", "testLot", timestamp, charge);

    String expected = "{" +
        " permitID='" + "testID" + "'" +
        ", lotID='" + "testLot" + "'" +
        ", incurred='" + timestamp.toString() + "'" +
        ", amount='" + charge.toString() + "'" +
        "}";

    assertEquals(expected, pCharge.toString());
  }

  /**
   * Test Equals
   */
  @Test
  public void testEquals() {
    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    ParkingCharge pCharge = new ParkingCharge("testID", "testLot", timestamp, charge);
    ParkingCharge pCharge2 = new ParkingCharge("testID", "testLot", timestamp, charge);
    ParkingCharge pCharge3 = new ParkingCharge("testID2", "testLot", timestamp, charge);

    assertTrue(pCharge.equals(pCharge2));
    assertFalse(pCharge.equals(pCharge3));
  }

  /**
   * Test HashCode
   */
  @Test
  public void testHashCode() {
    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    ParkingCharge pCharge = new ParkingCharge("testID", "testLot", timestamp, charge);
    ParkingCharge pCharge2 = new ParkingCharge("testID", "testLot", timestamp, charge);
    ParkingCharge pCharge3 = new ParkingCharge("testID2", "testLot", timestamp, charge);

    assertEquals(pCharge.hashCode(), pCharge2.hashCode());
    assertNotEquals(pCharge.hashCode(), pCharge3.hashCode());
  }

  /**
   * Test getters and setters
   */
  @Test
  public void testGetSetPermitID() {
    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    ParkingCharge pCharge = new ParkingCharge("testID", "testLot", timestamp, charge);

    pCharge.setPermitID("newPermit");
    assertEquals("newPermit", pCharge.getPermitID());
  }

  @Test
  public void testGetSetLotID() {
    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    ParkingCharge pCharge = new ParkingCharge("testID", "testLot", timestamp, charge);

    pCharge.setLotID("newLot");
    assertEquals("newLot", pCharge.getLotID());
  }

  @Test
  public void testGetSetIncurred() throws InterruptedException {
    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    ParkingCharge pCharge = new ParkingCharge("testID", "testLot", timestamp, charge);

    Thread.sleep(1000);

    Instant t2 = Instant.now();
    pCharge.setIncurred(t2);
    ;
    assertEquals(t2, pCharge.getIncurred());
  }

  @Test
  public void testGetSetAmount() {
    Instant timestamp = Instant.now();
    Money charge = new Money("32.10");
    ParkingCharge pCharge = new ParkingCharge("testID", "testLot", timestamp, charge);

    Money newCharge = new Money("99.99");
    pCharge.setAmount(newCharge);
    assertEquals(newCharge, pCharge.getAmount());
  }

}
