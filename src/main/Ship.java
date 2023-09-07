package main;

import javax.sound.sampled.Port;
import java.util.Collection;

public class Ship extends Vehicle{
    public Ship(String name, String fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
        super(name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
    }
}
