package main;

import java.util.Collection;

public class ReeferTruck extends Truck{
	public ReeferTruck(int tNumber, String name, String fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		super(tNumber, name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
	}
