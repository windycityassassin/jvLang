package parkingsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
  private long cents;

  /* Creates a new Money object defaulted to 0 cents */
  public Money() {
    this.cents = 0;
  }

  /* Creates a new Money object from an input String */
  public Money(String dollarValueString) {
    double doubleValue = Double.parseDouble(dollarValueString) * 100;
    this.cents = (long) doubleValue;
  }

  public Money(int cents) {
    this.cents = cents;
  }

  /* Creates a new Money object from another Money object */
  public Money(Money money) {
    this.cents = money.getCents();
  }

  public long getCents() {
    return this.cents;
  }

  public void setCents(long cents) {
    this.cents = cents;
  }

  /* Converts cents to dollar */
  public double getDollars() {
    double doubleCents = (double) this.cents / 100;
    BigDecimal bd = new BigDecimal(doubleCents).setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  /* To String Override */
  @Override
  public String toString() {
    double dollars = (double) this.cents / 100;
    return "$" + String.format("%.2f", dollars);
  }

  public long getAmount() {
    return 0;
  }
}
