package main;

import java.util.Collection;

public class Truck extends Vehicle {
	protected int tNumber;

	public Truck() {}

	public Truck(int tNumber, String name, double fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		super(name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
		this.tNumber = tNumber;
	}
  
	public int getTNumber() {
		return tNumber;
	}

	public void setTNumber(int tNumber) {
		this.tNumber = tNumber;
	}
	//check landing ability
	public boolean truckLandingAbility(Port port){
		return port.isLandingAbility();
	}
	// with truck need to check 2 times beofre move to port
	@Override
	public void movePort(Port port){
		if (!this.truckLandingAbility(port)){
			System.out.println("Not available landing");
			return;
		}
		super.movePort(port);
	}
}
