package main;

import java.util.Collection;

public class Truck extends Vehicle {
	protected int tNumber;

	public Truck(int tNumber, String name, String fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		super(name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
		this.tNumber = tNumber;
	}

	public int getTNumber() {
		return tNumber;
	}

	public void setTNumber(int tNumber) {
		this.tNumber = tNumber;
	}
}

