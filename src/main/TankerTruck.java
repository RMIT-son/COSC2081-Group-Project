package main;

import javax.sound.sampled.Port;
import java.awt.*;
import java.util.Collection;

public class TankerTruck extends Truck {
    public TankerTruck(String name, String fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
        super(name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
    }
}
