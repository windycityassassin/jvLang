package charge.strategy;

import org.junit.Test;
import parkingsystem.Car;
import parkingsystem.Customer;
import parkingsystem.Money;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChargeByTypeAndTimeTests {
  /**
   * Test pricing for SUV's vs Compact cars
   */
  @Test
  public void testChargeByType() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("ABC123", "permit", "license");
    Car car2 = new Car("ABC123", "permit1", "license");

    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    OffsetDateTime time = OffsetDateTime.of(2022, 10, 2, 20, 0, 0, 0, ZoneOffset.UTC);
    Money baseMoney = new Money();
    baseMoney.setCents(100);

    Money charge1 = chargeStrategy.calculateCharge(baseMoney, time, car);
    Money charge2 = chargeStrategy.calculateCharge(baseMoney, time, car2);

    assertEquals(100, charge1.getCents());
    assertEquals(80, charge2.getCents());
  }

  /**
   * Test pricing for Peak hours vs Off hours
   */
  @Test
  public void testChargeByTime() {
    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    LocalDate timestamp = LocalDate.now();
    Car car = new Car("ABC123", "permit", "license");

    ParkingChargeStrategy chargeStrategy = new ChargeByTypeAndTimeStrategy();
    OffsetDateTime timePeak = OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC);
    OffsetDateTime timeSlow = OffsetDateTime.of(2022, 10, 2, 20, 0, 0, 0, ZoneOffset.UTC);
    Money baseMoney = new Money();
    baseMoney.setCents(100);

    Money chargeSlow = chargeStrategy.calculateCharge(baseMoney, timeSlow, car);
    Money chargePeak = chargeStrategy.calculateCharge(baseMoney, timePeak, car);

    assertEquals(100, chargeSlow.getCents());
    assertEquals(300, chargePeak.getCents());
  }
}
