package main;

import javax.sound.sampled.Port;
import java.awt.*;
import java.util.Collection;

public class Truck extends Vehicle {

    public Truck(String name, String fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
        super(name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
    }
}
