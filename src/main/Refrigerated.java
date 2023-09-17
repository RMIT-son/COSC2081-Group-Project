package main;

import java.util.HashMap;
import java.util.Map;

public class Refrigerated extends Container {
	private static final Map<Vehicle, Double> FUEL_CONSUMPTION = new HashMap<>();

    static {
        FUEL_CONSUMPTION.put(Vehicle.Ship, 4.5);
        FUEL_CONSUMPTION.put(Vehicle.Truck, 5.4);
    }

    public Refrigerated(int cNumber, double weight) {
        super(cNumber, weight);
    }

    @Override
    public double calculateFuel(Vehicle vehicle, double distance) {
        return FUEL_CONSUMPTION.get(vehicle) * weight * distance;
    }
}