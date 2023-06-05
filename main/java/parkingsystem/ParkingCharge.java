package parkingsystem;

import java.time.Instant;
import java.util.Objects;

public class ParkingCharge {
  private String permitID;
  private String lotID;
  private Instant incurred;
  private Money amount;

  /* Creates a new ParkingCharge object */
  public ParkingCharge(String permitID, String lotID, Instant incurred, Money amount) {
    this.permitID = permitID;
    this.lotID = lotID;
    this.incurred = incurred;
    this.amount = amount;
  }

    public ParkingCharge() {

    }

    public String getPermitID() {
    return this.permitID;
  }

  public void setPermitID(String permitID) {
    this.permitID = permitID;
  }

  public String getLotID() {
    return this.lotID;
  }

  public void setLotID(String lotID) {
    this.lotID = lotID;
  }

  public Instant getIncurred() {
    return this.incurred;
  }

  public void setIncurred(Instant incurred) {
    this.incurred = incurred;
  }

  public Money getAmount() {
    return this.amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }

  /* Override toString() */
  @Override
  public String toString() {
    return "{" +
        " permitID='" + getPermitID() + "'" +
        ", lotID='" + getLotID() + "'" +
        ", incurred='" + getIncurred().toString() + "'" +
        ", amount='" + getAmount().toString() + "'" +
        "}";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof ParkingCharge)) {
      return false;
    }

    ParkingCharge parkingCharge = (ParkingCharge) obj;

    return (this.permitID == parkingCharge.permitID &&
        this.lotID == parkingCharge.lotID &&
        this.incurred == parkingCharge.incurred &&
        this.amount == parkingCharge.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(permitID, lotID, incurred, amount);
  }

  protected Double getDiscount(ParkingEvent parkingEvent) {
    return null;
  }
}
