package main.vehicle;

import main.container.*;
import main.porttrip.Port;

import java.util.Collection;

public class TankerTruck extends Truck implements carryAble {
	public TankerTruck(int tNumber, String name, double fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		super(tNumber, name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
	}
	@Override
	public boolean carryAble(Container container) {
		return container instanceof DryStorage || container instanceof OpenTop || container instanceof OpenSide || container instanceof Liquid;
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
