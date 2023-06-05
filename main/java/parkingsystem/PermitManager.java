package parkingsystem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class PermitManager {
  private HashMap<String, ParkingPermit> permits;

  /* Generates a PermitManager object */
  public PermitManager(HashMap<String, ParkingPermit> permits) {
    this.permits = permits;
  }

  /* Generates an empty PermitManager object with an empty list of permits */
  public PermitManager() {
    permits = new HashMap<String, ParkingPermit>();
  }

  /* Get all permits */
  public HashMap<String, ParkingPermit> getPermits() {
    return this.permits;
  }

  public void setPermits(HashMap<String, ParkingPermit> permits) {
    this.permits = permits;
  }

  /* Register a new car while assigning it a ParkingPermit */
  public Car registerNewCar(String license, CarType type, Customer owner) {
    ParkingPermit permit = new ParkingPermit();
    permit.setId(UUID.randomUUID().toString());
    permit.setExpirationDate(LocalDate.now().plusDays(365));
    Car car = new Car("ABC123", permit.getId(), license);
    permit.setCar(car);
    permits.put(car.getLicense(), permit);
    return car;
  }

  /* Get a ParkingPermit by License if it exists */
  public ParkingPermit getPermitByLicense(String license) {
    if (permits.containsKey(license)) {
      return permits.get(license);
    }
    return null;
  }
}
