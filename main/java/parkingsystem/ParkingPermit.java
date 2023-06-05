package parkingsystem;

import java.time.LocalDate;

public class ParkingPermit {
  private String id;
  private Car car;
  private LocalDate expirationDate;

  public ParkingPermit(String id, Car car, LocalDate expirationDate) {
    this.id = id;
    this.car = car;
    this.expirationDate = expirationDate;
  }

  public ParkingPermit() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getId() {
    return this.id;
  }

  public Car getCar() {
    return this.car;
  }

  public LocalDate getExpirationDate() {
    return this.expirationDate;
  }

}
