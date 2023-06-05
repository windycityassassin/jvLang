package parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ScanTypeTests {
  /**
   * Test Types
   */
  @Test
  public void testScanType() {
    assertEquals("ENTRY", ScanType.ENTRY.toString());
    assertEquals("ENTRYEXIT", ScanType.ENTRYEXIT.toString());
  }
}
