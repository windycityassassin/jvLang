package charge.decorator.factory;

import charge.decorator.*;

import java.util.List;


public class ParkingChargeCalculatorFactory {
  /**
   * This enumeration represents the available charge calculator objects.
   */
  public enum ChargeDecorator {
    Type,
    Time,
    Day
  }

  /**
   * This method accepts a list of ChargeDecorators and returns a ParkingChargeCalculator
   * with all requested decorators.
   */
  public ParkingChargeCalculator getChargeCalculator(List<ChargeDecorator> chargeDecorators) {
    ParkingChargeCalculator chargeCalculator = new FlatRateCalculator();
    for (ChargeDecorator chargeDecorator : chargeDecorators) {
      switch (chargeDecorator) {
        case Type:
          chargeCalculator = new CompactCarDiscountDecorator(chargeCalculator);
          continue;
        case Time:
          chargeCalculator = new TimeChargeDecorator(chargeCalculator);
          continue;
        case Day:
          chargeCalculator = new DayDiscountDecorator(chargeCalculator);
          continue;
        default:
          return chargeCalculator;
      }
    }
    return chargeCalculator;
  }

  /**
   * This method accepts a single ChargeDecorator and returns a ParkingChargeCalculator
   * with the requested decorator.
   */
  public ParkingChargeCalculator getChargeCalculator(ChargeDecorator chargeDecorator) {
    ParkingChargeCalculator chargeCalculator = new FlatRateCalculator();
    return switch (chargeDecorator) {
      case Type -> new CompactCarDiscountDecorator(chargeCalculator);
      case Time -> new TimeChargeDecorator(chargeCalculator);
      case Day -> new DayDiscountDecorator(chargeCalculator);
      default -> chargeCalculator;
    };
  }
}
