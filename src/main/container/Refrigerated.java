package main.container;

import main.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Refrigerated extends Container {
	private static final Map<Vehicle, Double> fuelConsumption = new HashMap<>();

    static {
        fuelConsumption.put(Vehicle.Ship, 4.5);
        fuelConsumption.put(Vehicle.Truck, 5.4);
    }

    public Refrigerated(int cNumber, double weight) {
        super(cNumber, weight);
    }

    @Override
    public double calculateFuel(Vehicle vehicle, double distance) {
        return fuelConsumption.get(vehicle) * weight * distance;
    }
}