package main.container;

import main.porttrip.Port;
import main.vehicle.Ship;
import main.vehicle.Truck;
import main.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Refrigerated extends Container {
    private static final Map<Class<? extends Vehicle>, Double> fuelConsumption = new HashMap<>();

    static {
        fuelConsumption.put(Ship.class, 4.5);
        fuelConsumption.put(Truck.class, 5.4);
    }


    public Refrigerated() {
    }

    public Refrigerated(int cNumber, double weight, double requiredFuel, Vehicle currentVehicle, Port currentPort, ContainerState state) {
        super(cNumber, weight, requiredFuel, currentVehicle, currentPort, state);
    }

    @Override
    public double calculateFuel(Vehicle vehicle, double distance) {
        return fuelConsumption.get(vehicle) * weight * distance;
    }
}