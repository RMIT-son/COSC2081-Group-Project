package main;

import java.util.Collection;

public class Ship extends Vehicle{
	protected int sNumber;
	public Ship(int sNumber, String name, String fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		super(name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
		this.sNumber = sNumber;
	}
