package serialization;

import parkingsystem.Car;
import parkingsystem.Customer;

public class ParkingClient {

    //Constructor
    public ParkingClient() {

    }

    public ParkingResponse generateParkingResponse(ParkingRequest request, Customer customer, Car car) {

        //Generates a ParkingRequest object
        ParkingRequest json = request.convertCustomerToJSON(customer);

        //Stores JSON String into a ParkingResponse object
        ParkingResponse response = new ParkingResponse(json.toString());
        response.convertCustomerToJSON(customer);

        return response;
    }
}
