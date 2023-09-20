package main.vehicle;

import main.container.Container;
import main.porttrip.Port;

import java.util.Collection;

public class Ship extends Vehicle {
	protected int sNumber;

	public Ship() {
		super();
	}

	public Ship(int sNumber, String name, double fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		super(name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
		this.sNumber = sNumber;
	}

	public int getSNumber() {
		return sNumber;
	}

	public void setSNumber(int sNumber) {
		this.sNumber = sNumber;
	}
}
