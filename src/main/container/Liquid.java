package main.container;

import main.vehicle.Ship;
import main.vehicle.Truck;
import main.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Liquid extends Container {
	private static final Map<Class<? extends Vehicle>, Double> fuelConsumption = new HashMap<>();

    static {
        fuelConsumption.put(Ship.class, 4.8);
        fuelConsumption.put(Truck.class, 5.3);
    }

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