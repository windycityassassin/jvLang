//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package parkingsystem;

import charge.decorator.ParkingChargeCalculator;

import java.time.DayOfWeek;

public class WeekdayDiscount extends ParkingChargeDecorator {
    private Double discountRate = 0.1;

    public WeekdayDiscount(ParkingChargeCalculator calc) {
        super(calc);
    }

    public Double getDiscount(ParkingEvent parkingEvent) {
        if (parkingEvent.getTimeOut() != null) {
            if (this.isWeekday(parkingEvent.getTimeOut().getDayOfWeek())) {
                System.out.println("Weekday discount rate applied");
                return super.getDiscount(parkingEvent) + this.discountRate;
            }
        } else if (this.isWeekday(parkingEvent.getTimeIn().getDayOfWeek())) {
            System.out.println("Weekday discount rate applied");
            return super.getDiscount(parkingEvent) + this.discountRate;
        }

        System.out.println("Discount cannot be applied on weekend");
        return super.getDiscount(parkingEvent);
    }

    private boolean isWeekday(DayOfWeek day) {
        int dayOfWeek = day.getValue();
        return 1 <= dayOfWeek && dayOfWeek <= 5;
    }
}
