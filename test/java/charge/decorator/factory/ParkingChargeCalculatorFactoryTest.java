package charge.decorator.factory;

import charge.decorator.ParkingChargeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parkingsystem.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingChargeCalculatorFactoryTest {
  private ParkingLot parkingLot;
  private ParkingPermit permit;
  private OffsetDateTime offsetTime;

  @BeforeEach
  public void setup() {
    this.parkingLot = new ParkingLot("Lot A", "123 Main St.", 0, 100, ScanType.ENTRY, null);

    Customer customer = new Customer("abcd", "Jimmy", "123 Main St.", "1234567890");
    Car car = new Car("ABC123", "testPermit", "testLicense");
    LocalDate time = LocalDate.now();
    this.offsetTime = OffsetDateTime.now();

    this.permit = new ParkingPermit("testLicense", car, time);
  }

  @Test
  void testChargeByType() {
    ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory();
    ParkingChargeCalculator calculator = factory.getChargeCalculator(ParkingChargeCalculatorFactory.ChargeDecorator.Type);
    Money charge = calculator.getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(80, charge.getCents());
  }

  @Test
  void testChargeByTime() {
    ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory();
    ParkingChargeCalculator calculator = factory.getChargeCalculator(ParkingChargeCalculatorFactory.ChargeDecorator.Time);
    OffsetDateTime offsetTime = OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC);
    Money charge = calculator.getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(300, charge.getCents());
  }

  @Test
  void testChargeByDay() {
    ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory();
    ParkingChargeCalculator calculator = factory.getChargeCalculator(ParkingChargeCalculatorFactory.ChargeDecorator.Day);
    OffsetDateTime offsetTime = OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC);
    Money charge = calculator.getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(0, charge.getCents());
  }

  @Test
  void testChargeByTypeAndDay() {
    ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory();
    ParkingChargeCalculator calculator = factory.getChargeCalculator(
        Arrays.asList(ParkingChargeCalculatorFactory.ChargeDecorator.Day, ParkingChargeCalculatorFactory.ChargeDecorator.Type)
    );
    OffsetDateTime offsetTime = OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC);
    Money charge = calculator.getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(0, charge.getCents());
  }

  @Test
  void testChargeByTypeAndTime() {
    ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory();
    ParkingChargeCalculator calculator = factory.getChargeCalculator(
        Arrays.asList(ParkingChargeCalculatorFactory.ChargeDecorator.Time, ParkingChargeCalculatorFactory.ChargeDecorator.Type)
    );
    OffsetDateTime offsetTime = OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC);
    Money charge = calculator.getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(240, charge.getCents());
  }

  @Test
  void testChargeByTimeAndDay() {
    ParkingChargeCalculatorFactory factory = new ParkingChargeCalculatorFactory();
    ParkingChargeCalculator calculator = factory.getChargeCalculator(
        Arrays.asList(ParkingChargeCalculatorFactory.ChargeDecorator.Day, ParkingChargeCalculatorFactory.ChargeDecorator.Time)
    );
    OffsetDateTime offsetTime = OffsetDateTime.of(2022, 10, 2, 10, 0, 0, 0, ZoneOffset.UTC);
    Money charge = calculator.getParkingCharge(permit, parkingLot, offsetTime, offsetTime);

    assertEquals(200, charge.getCents());
  }
}