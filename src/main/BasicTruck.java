package main;

import java.util.Collection;

public class BasicTruck extends Truck{
	public BasicTruck(int tNumber, String name, double fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		super(tNumber, name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
	}
}
