package main.container;

import main.porttrip.Port;
import main.vehicle.Ship;
import main.vehicle.Truck;
import main.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class OpenSide extends Container {
    private static final Map<Class<? extends Vehicle>, Double> fuelConsumption = new HashMap<>();

    static {
        fuelConsumption.put(Ship.class, 2.7);
        fuelConsumption.put(Truck.class, 3.2);
    }

    public OpenSide() {
        super();
    }

    public OpenSide(int cNumber, double weight, double requiredFuel, Vehicle currentVehicle, Port currentPort) {
        super(cNumber, weight, requiredFuel, currentVehicle, currentPort);
    }

    @Override
    public double calculateFuel(Vehicle vehicle, double distance) {
        Double consumptionRate = 0.0;
        for (Map.Entry<Class<? extends Vehicle>, Double> entry : fuelConsumption.entrySet()) {
            if (entry.getKey().isAssignableFrom(vehicle.getClass())) {
                consumptionRate = entry.getValue();
                break;
            }
        }
        return consumptionRate * weight * distance;
    }
}