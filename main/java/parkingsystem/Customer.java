package parkingsystem;

import java.util.ArrayList;
import java.util.Objects;

public class Customer {
  private String customerID;
  private String name;
  private String address;
  private String phoneNumber;
  private ArrayList<Car> cars;

  /* Creates a new Customer object */
  public Customer(String customerID, String name, String address, String phoneNumber) {
    this.customerID = customerID;
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.cars = new ArrayList<Car>();
  }

  private Customer(CustomerBuilder builder) {
    this.customerID = builder.customerID;
    this.name = builder.name;
    this.address = builder.address;
    this.phoneNumber = builder.phoneNumber;
    this.cars = builder.cars;
  }

    public Customer(String johnDoe, String mail) {
    }

    /* Registers a Car object to the Customer */
  public Car register(String license, CarType type, ParkingOffice parkingOffice) {
    Car car = parkingOffice.register(this, license, type);
    addCar(car);
    return car;
  }

  /* Add Car object to Customer Car arraylist */
  private void addCar(Car car) {
    this.cars.add(car);
  }

  /* Getters / Setters */
  public String getCustomerID() {
    return this.customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
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

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public ArrayList<Car> getCars() {
    return this.cars;
  }

  public void setCars(ArrayList<Car> cars) {
    this.cars = cars;
  }

  /* To String Override */
  @Override
  public String toString() {
    return "{" +
        " customerID='" + getCustomerID() + "'" +
        ", name='" + getName() + "'" +
        ", address='" + getAddress() + "'" +
        ", phoneNumber='" + getPhoneNumber() + "'" +
        "}";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    Customer customer = (Customer) obj;
    return (this.customerID == customer.customerID &&
        this.name == customer.name &&
        this.address == customer.address &&
        this.phoneNumber == customer.phoneNumber &&
        this.cars.equals(customer.cars));
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerID, name, address, phoneNumber, cars);
  }

  public Object getEmail() {
    return null;
  }

  public static class CustomerBuilder {
    private String customerID;
    private String name;
    private String address;
    private String phoneNumber;
    private ArrayList<Car> cars;

    public CustomerBuilder withCustomerID(String customerID) {
      this.customerID = customerID;
      return this;
    }

    public CustomerBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public CustomerBuilder withAddress(String address) {
      this.address = address;
      return this;
    }

    public CustomerBuilder withPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public CustomerBuilder withCars(ArrayList<Car> cars) {
      this.cars = cars;
      return this;
    }

    public Customer build() {
      Customer customer = new Customer(this);
      return customer;
    }
  }

}
