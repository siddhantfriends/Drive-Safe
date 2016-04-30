package safe.drive.com.drifesafe;

import java.util.List;

/**
 * Created by Milton on 30/04/2016.
 */
public class User {
    private Vehicle vehicle;
    private List<Vehicle> familyVehicles;

    public User(Vehicle vehicle, List<Vehicle> familyVehicles) {
        this.vehicle = vehicle;
        this.familyVehicles = familyVehicles;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public List<Vehicle> getFamilyVehicles() {
        return familyVehicles;
    }

    public void addFamilyVehicle(Vehicle vehicle) {
        this.familyVehicles.add(vehicle);
    }
}
