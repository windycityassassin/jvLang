package parkingsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTests {
  /**
   * Test Constructor
   */
  @Test
  public void testMoneyBase() {
    Money money = new Money();
    assertEquals(0, money.getCents());
  }

  /**
   * Test Constructor w/ String input
   */
  @Test
  public void testMoneyString() {
    Money money = new Money("32.10444");
    assertEquals(3210, money.getCents());
  }

  /**
   * Test Constructor w/ Money input
   */
  @Test
  public void testMoneySelf() {
    Money testMoney = new Money();
    testMoney.setCents(3210);
    Money money = new Money(testMoney);
    assertEquals(3210, money.getCents());
  }

  /**
   * Test Constructor w/ int input
   */
  @Test
  public void testMoneyInt() {
    Money money = new Money(1);
    assertEquals(1, money.getCents());
  }

  /**
   * Test toString
   */
  @Test
  public void testToString() {
    Money money = new Money();
    money.setCents(3210);

    String expected = "$32.10";

    assertEquals(expected, money.toString());
  }

  /**
   * Test get/set cents
   */
  @Test
  public void testGetSetCents() {
    Money money = new Money();
    money.setCents(9999);
    assertEquals(9999, money.getCents());
  }

  /**
   * Test get dollars
   */
  @Test
  public void testGetDollars() {
    Money money = new Money();
    money.setCents(9900);
    assertEquals(99.00, money.getDollars());
  }
}
