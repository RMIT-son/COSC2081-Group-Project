package main;

import java.util.Collection;

public class TankerTruck extends Truck implements carryAble{
	public TankerTruck(int tNumber, String name, double fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		super(tNumber, name, fuel, fuelCapacity, carryCapacity, currentPort, containers);
	}
	@Override
	public boolean carryAble(Container container) {
		if (container instanceof DryStorage || container instanceof OpenTop || container instanceof  OpenSide || container instanceof Liquid){
			return true;
		}else{
			return false;
		}
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

