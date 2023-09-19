package main.container;

import main.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Liquid extends Container {
	private static final Map<Vehicle, Double> FUEL_CONSUMPTION = new HashMap<>();

    static {
        FUEL_CONSUMPTION.put(Vehicle.Ship, 4.8);
        FUEL_CONSUMPTION.put(Vehicle.Truck, 5.3);
    }

    public Liquid(int cNumber, double weight) {
        super(cNumber, weight);
    }

    @Override
    public double calculateFuel(Vehicle vehicle, double distance) {
        return FUEL_CONSUMPTION.get(vehicle) * weight * distance;
    }
}