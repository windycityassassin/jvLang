package parkingsystem;

public interface Subject {
  public void register(ParkingAction observer);

  public void unregister(ParkingAction observer);

  public void notifyObservers(ParkingEvent event);

    Money getFixedDailyRate();
}
