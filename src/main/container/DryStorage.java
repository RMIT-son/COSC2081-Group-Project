package main.container;
import main.porttrip.Port;
import main.vehicle.Ship;
import main.vehicle.Truck;
import main.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class DryStorage extends Container {
    private static final Map<Class<? extends Vehicle>, Double> fuelConsumption = new HashMap<>();

    static {
        fuelConsumption.put(Ship.class, 3.5);
        fuelConsumption.put(Truck.class, 4.6);
    }

    public DryStorage() {
        super();
    }

    public DryStorage(int cNumber, double weight, double requiredFuel, Vehicle currentVehicle, Port currentPort) {
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