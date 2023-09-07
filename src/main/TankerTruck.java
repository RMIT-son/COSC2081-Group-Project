package main;

import java.util.Collection;

public class TankerTruck extends Truck {
	public TankerTruck(int tNumber, String name, String fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		super(tNumber, name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
	}
}
