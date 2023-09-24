package main.vehicle;

import main.container.*;
import main.porttrip.Port;

import java.util.ArrayList;
import java.util.Collection;


public class ReeferTruck extends Truck implements carryAble {
	public ReeferTruck(int tNumber, String name, double fuel, double fuelCapacity, double carryCapacity, Port currentPort) {
		super(tNumber, name, fuel, fuelCapacity, carryCapacity, currentPort);
		this.containers = new ArrayList<>();
	}
	@Override
	public boolean carryAble(Container container) {
		return container instanceof DryStorage || container instanceof OpenTop || container instanceof OpenSide || container instanceof Refrigerated;
	}
	@Override
	public void loadContainer(Container container){

		if(!this.carryAble(container)){
			System.out.println("Wrong type of storage");
			return;
		}
		super.loadContainer(container);
	}
}
