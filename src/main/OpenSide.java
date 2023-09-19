package main;

import java.util.HashMap;
import java.util.Map;

public class OpenSide extends Container {
    private static final Map<Vehicle, Double> FUEL_CONSUMPTION = new HashMap<>();

    static {
        FUEL_CONSUMPTION.put(Vehicle.Ship, 2.7);
        FUEL_CONSUMPTION.put(Vehicle.Truck, 3.2);
    }

    public OpenSide(int cNumber, double weight) {
        super(cNumber, weight);
    }

    @Override
    public double calculateFuel(Vehicle vehicle, double distance) {
        return FUEL_CONSUMPTION.get(vehicle) * weight * distance;
    }
}