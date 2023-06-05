package parkingsystem;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class ParkingEvent {
  private ParkingLot parkingLot;
  private OffsetDateTime timeIn;
  private OffsetDateTime timeOut;
  private ParkingPermit permit;

  public ParkingLot getParkingLot() {
    return parkingLot;
  }

  public OffsetDateTime getTimeIn() {
    return timeIn;
  }

  public OffsetDateTime getTimeOut() {
    return timeOut;
  }

  public ParkingPermit getPermit() {
    return permit;
  }

  public ParkingEvent(ParkingLot parkingLot, LocalDateTime dateTimeIn, LocalDateTime dateTimeOut, ParkingPermit permit) {
  }

  private ParkingEvent(Builder builder) {
    this.parkingLot = builder.parkingLot;
    this.timeIn = builder.timeIn;
    this.timeOut = builder.timeOut;
    this.permit = builder.permit;
  }

  public static class Builder {
    private ParkingLot parkingLot;
    private OffsetDateTime timeIn;
    private OffsetDateTime timeOut;
    private ParkingPermit permit;

    public Builder withParkingLot(ParkingLot parkingLot) {
      this.parkingLot = parkingLot;
      return this;
    }

    public Builder withTimeIn(OffsetDateTime timeIn) {
      this.timeIn = timeIn;
      return this;
    }

    public Builder withTimeOut(OffsetDateTime timeOut) {
      this.timeOut = timeOut;
      return this;
    }

    public Builder withPermit(ParkingPermit permit) {
      this.permit = permit;
      return this;
    }

    public ParkingEvent build() {
      return new ParkingEvent(this);
    }
  }
}
