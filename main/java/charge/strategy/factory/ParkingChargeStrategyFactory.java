package charge.strategy.factory;

import charge.strategy.ChargeByTypeAndDayStrategy;
import charge.strategy.ChargeByTypeAndTimeStrategy;
import charge.strategy.ParkingChargeStrategy;

public class ParkingChargeStrategyFactory {
  public enum ChargeStrategy {
    TypeAndDay,
    TypeAndTime
  }

  ;

  public ParkingChargeStrategy getChargeStrategy(ChargeStrategy chargeStrategy) {
    switch (chargeStrategy) {
      case TypeAndDay:
        return new ChargeByTypeAndDayStrategy();
      case TypeAndTime:
        return new ChargeByTypeAndTimeStrategy();
      default:
        return null;
    }
  }
}
