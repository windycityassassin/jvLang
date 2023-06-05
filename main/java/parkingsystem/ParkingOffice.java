package parkingsystem;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParkingOffice {
  private String name;
  private String address;
  private List<Customer> customers;
  private List<Car> cars;
  private List<ParkingLot> lots;
  private List<ParkingCharge> charges;
  private PermitManager permitManager;

  /* Creates a new ParkingOffice object */
  public ParkingOffice(String name, String address, List<Customer> customers, List<Car> cars, List<ParkingLot> lots,
                       List<ParkingCharge> charges) {
    this.name = name;
    this.address = address;
    this.customers = customers;
    this.cars = cars;
    this.lots = lots;
    this.charges = charges;
    this.permitManager = new PermitManager();
  }

  public ParkingOffice(String mainOffice, Address parkingOfficeAddress, TransactionManager transactionManager, PermitManager permitManager) {
  }

  /* Register a new customer to the parking office */
  public Customer register(String name, String address, String phone, Address customerAddress) {
    Customer customer = new Customer(generateCustomerID(), name, address, phone);
    this.customers.add(customer);
    return customer;
  }

  /* Register a new car to the parking office */
  public Car register(Customer owner, String license, CarType type) {
    Car car = permitManager.registerNewCar(license, type, owner);

    this.cars.add(car);
    return car;
  }

  /* Get a Customer by name */
  public Customer getCustomer(String name) {
    for (Customer customer : this.customers) {
      if (customer.getName().equals(name)) {
        return customer;
      }
    }
    return null;
  }

  /* Get a car by license number */
  public Car getCarByLicense(String license) {
    return permitManager.getPermitByLicense(license).getCar();
  }

  /* Get a list of customer IDs */
  public List<String> getCustomerIDs() {
    List<String> output = new ArrayList<String>();
    for (Customer customer : this.customers) {
      output.add(customer.getCustomerID());
    }

    return output;
  }

  /* Get a list of permit IDs */
  public List<String> getPermitIDs() {
    List<String> output = new ArrayList<String>();
    for (Car car : this.cars) {
      output.add(car.getPermit());
    }

    return output;
  }

  /* Get a list of permit IDs for a specific customer */
  public List<String> getPermitIDs(Customer customer) {
    List<String> output = new ArrayList<String>();
    for (Car car : customer.getCars()) {
      output.add(car.getPermit());
    }

    return output;
  }

  /* Add a ParkingCharge to the parking office */
  public Money addCharge(ParkingCharge charge) {
    this.charges.add(charge);
    return charge.getAmount();
  }

  /* Generate a permit string */
  private String generateCustomerID() {
    // TODO Generate Unique String
    return "TestID";
  }

  /* Park a vehicle */
  public ParkingCharge park(OffsetDateTime date, String license, ParkingLot lot) {
    Car car = getCarByLicense(license);
    ParkingCharge charge = lot.entry(car, date);
    addCharge(charge);
    return charge;
  }

  public ParkingCharge park(ParkingEvent event) {
    Car car = event.getPermit().getCar();
    ParkingCharge charge = event.getParkingLot().entry(car, event.getTimeIn());
    addCharge(charge);
    return charge;
  }

  public Money getParkingCharges(String permitID) {
    Money money = new Money();
    for (ParkingCharge charge : this.charges) {
      if (permitID == charge.getPermitID().toString()) {
        money.setCents(money.getCents() + charge.getAmount().getCents());
      }
    }
    return money;
  }

  public Money getParkingCharges(Customer customer) {
    Money money = new Money();

    for (String permitID : getPermitIDs(customer)) {
      for (ParkingCharge charge : this.charges) {
        if (Objects.equals(permitID, charge.getPermitID())) {
          money.setCents(money.getCents() + charge.getAmount().getCents());
        }
      }
    }
    return money;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Customer> getCustomers() {
    return this.customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public List<Car> getCars() {
    return this.cars;
  }

  public void setCars(List<Car> cars) {
    this.cars = cars;
  }

  public List<ParkingLot> getLots() {
    return this.lots;
  }

  public void setLots(List<ParkingLot> lots) {
    this.lots = lots;
  }

  public List<ParkingCharge> getCharges() {
    return this.charges;
  }

  public void setCharges(List<ParkingCharge> charges) {
    this.charges = charges;
  }

  public PermitManager getPermitManager() {
    return this.permitManager;
  }

  public void setPermitManager(PermitManager permitManager) {
    this.permitManager = permitManager;
  }

  public Object register(CarType carType, String license, String customerId) {
    return null;
  }

  public ParkingLot getParkingLot(String parkingLotId) {
    return null;
  }

  public ParkingPermit getParkingPermit(String permitId) {
    return null;
  }

    public void addParkingLot(ParkingLot parkingLotA) {
    }
}
