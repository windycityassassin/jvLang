package parkingsystem;

import charge.decorator.factory.ParkingChargeCalculatorFactory;
import charge.strategy.ParkingChargeStrategy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingLot implements Subject {
  private String lotID;
  private String address;
  private Integer capacity;
  private Integer fee;
  private ScanType scanType;
  private ParkingChargeStrategy chargeStrategy;
  private List<ParkingAction> observers;

  /* Creates a new ParkingLot object */
  public ParkingLot(String lotID, String address, Integer capacity, Integer fee, ScanType scanType,
                    ParkingChargeStrategy chargeStrategy) {
    this.lotID = lotID;
    this.address = address;
    this.capacity = capacity;
    this.fee = fee;
    this.scanType = scanType;
    this.chargeStrategy = chargeStrategy;
    this.observers = new ArrayList<ParkingAction>();
  }

  public ParkingLot(String number, String lotC, Address parkingLotAddressC, ParkingChargeCalculatorFactory parkingChargeCalculatorFactory) {
  }

  public ParkingLot(String number, String lotB, Address parkingLotAddressB, ParkingChargeCalculatorFactory weekdayCartypeDiscount, ParkingChargeCalculatorFactory parkingChargeCalculatorFactory) {
  }

  /*
   * The primary method of the Parking Lot, used to calculate a ParkingCharge upon
   * entry
   */
  public ParkingCharge entry(Car car, OffsetDateTime time) {
    Money money = new Money();
    money.setCents(fee);
    money = chargeStrategy.calculateCharge(money, time, car);
    ParkingCharge charge = new ParkingCharge(car.getPermit(), this.lotID, Instant.now(), money);
    return charge;
  }

  /* Getters / Setters */

  public ParkingChargeStrategy getChargeStrategy() {
    return this.chargeStrategy;
  }

  public void setChargeStrategy(ParkingChargeStrategy chargeStrategy) {
    this.chargeStrategy = chargeStrategy;
  }

  public List<ParkingAction> getObservers() {
    return this.observers;
  }

  public String getLotID() {
    return this.lotID;
  }

  public void setLotID(String lotID) {
    this.lotID = lotID;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getCapacity() {
    return this.capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public Integer getFee() {
    return this.fee;
  }

  public void setFee(Integer fee) {
    this.fee = fee;
  }

  public ScanType getScanType() {
    return this.scanType;
  }

  public void setScanType(ScanType scanType) {
    this.scanType = scanType;
  }

  /* Override To String */
  @Override
  public String toString() {
    return "{" +
        " lotID='" + getLotID() + "'" +
        ", address='" + getAddress() + "'" +
        ", capacity='" + getCapacity() + "'" +
        ", fee='" + getFee() + "'" +
        ", scanType='" + getScanType() + "'" +
        "}";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    ParkingLot parkingLot = (ParkingLot) obj;
    return (this.lotID == parkingLot.lotID &&
        this.address == parkingLot.address &&
        this.capacity == parkingLot.capacity &&
        this.fee == parkingLot.fee &&
        this.scanType == parkingLot.scanType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lotID, address, capacity, fee, scanType);
  }

  @Override
  public void register(ParkingAction observer) {
    observers.add(observer);
  }

  @Override
  public void unregister(ParkingAction observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(ParkingEvent event) {
    for (ParkingAction observer : observers) {
      observer.notify(event);
    }
  }

  @Override
  public Money getFixedDailyRate() {
    return null;
  }

  public Money getParkingCharge(ParkingEvent event) {
      return null;
    }

  public Object getName() {
    return null;
  }

  public Object getDiscountStrategy() {
    return null;
  }

  public int getLotId() {
    return 0;
  }
}
