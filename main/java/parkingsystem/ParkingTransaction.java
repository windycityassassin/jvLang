//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package parkingsystem;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

public final class ParkingTransaction implements Serializable {
    private final LocalDateTime date;
    private final ParkingPermit permit;
    private final ParkingLot parkingLot;
    private final Money chargedAmount;

    public ParkingTransaction(OffsetDateTime date, ParkingPermit permit, ParkingLot parkingLot, Money chargedAmount) {
        this.date = date.toLocalDateTime();
        this.permit = permit;
        this.parkingLot = parkingLot;
        this.chargedAmount = chargedAmount;
    }

    public ParkingTransaction(LocalDateTime date, ParkingPermit parkingPermit, ParkingLot parkingLot, Money chargedAmount, ParkingPermit permit, ParkingLot parkingLot1, Money chargedAmount1) {
        this.permit = permit;
        this.parkingLot = parkingLot1;
        this.chargedAmount = chargedAmount1;
        this.date = null;
    }

    public ParkingPermit getPermit() {
        return this.permit;
    }

    public Money getChargedAmount() {
        return this.chargedAmount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            ParkingTransaction other = (ParkingTransaction)obj;
            if (!Objects.equals(this.date, other.date)) {
                return false;
            } else if (!Objects.equals(this.permit, other.permit)) {
                return false;
            } else if (!Objects.equals(this.parkingLot, other.parkingLot)) {
                return false;
            } else {
                return Objects.equals(this.chargedAmount, other.chargedAmount);
            }
        }
    }
}
