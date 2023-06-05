//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package parkingsystem;

import java.time.LocalDateTime;

public interface DiscountStrategy {
    String getStrategyName();

    Double getDiscount(CarType var1, LocalDateTime var2);

    void setDiscount(Double var1);

    Money getParkingCharge(ParkingLot var1, CarType var2, LocalDateTime var3);
}
