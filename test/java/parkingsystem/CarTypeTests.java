package parkingsystem;

/**
* Tests for CarType.java
*
* @author  Erik Grafton
* @version 1.0
* @since   July 11, 2021
*/

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CarTypeTests {
  /**
   * Test Types
   */
  @Test
  public void testCustomer() {
    assertEquals("SUV", CarType.SUV.toString());
    assertEquals("COMPACT", CarType.COMPACT.toString());
  }
}
