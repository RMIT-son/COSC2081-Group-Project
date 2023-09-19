package main.container;

import main.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class OpenTop extends Container {
    private static final Map<Vehicle, Double> fuelConsumption = new HashMap<>();

//    static {
//        fuelConsumption.put(Vehicle.Ship, 2.8);
//        fuelConsumption.put(Vehicle.Truck, 3.2);
//    }


    public OpenTop() {
    }

    public OpenTop(int cNumber, double weight, double requiredFuel) {
        super(cNumber, weight, requiredFuel);
    }

    @Override
    public double calculateFuel(Vehicle vehicle, double distance) {
        return fuelConsumption.get(vehicle) * weight * distance;
    }
}