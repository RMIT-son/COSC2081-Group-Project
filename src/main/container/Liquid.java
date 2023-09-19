package main.container;

import main.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Liquid extends Container {
	private static final Map<Vehicle, Double> fuelConsumption = new HashMap<>();

//    static {
//        fuelConsumption.put(Vehicle.Ship, 4.8);
//        fuelConsumption.put(Vehicle.Truck, 5.3);
//    }

    public Liquid() {
        super();
    }

    public Liquid(int cNumber, double weight, double requiredFuel) {
        super(cNumber, weight, requiredFuel);
    }

    @Override
    public double calculateFuel(Vehicle vehicle, double distance) {
        return fuelConsumption.get(vehicle) * weight * distance;
    }
}