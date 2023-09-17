package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Vehicle implements Serializable {
	protected String name;
	protected double fuel;
	protected double fuelCapacity;
	protected double carryCapacity;
	protected Port currentPort;
	protected Collection<Container> containers;

	public Vehicle() {}

	public Vehicle(String name, double fuel, double fuelCapacity, double carryCapacity, Port currentPort, Collection<Container> containers) {
		this.name = name;
		this.fuel = fuel;
		this.fuelCapacity = fuelCapacity;
		this.carryCapacity = carryCapacity;
		this.currentPort = currentPort;
		this.containers = containers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public double getFuelCapacity() {
		return fuelCapacity;
	}

	public void setFuelCapacity(double fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}

	public double getCarryCapacity() {
		return carryCapacity;
	}

	public void setCarryCapacity(double carryCapacity) {
		this.carryCapacity = carryCapacity;
	}

	public Port getCurrentPort() {
		return currentPort;
	}

	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}

	public Collection<Container> getContainers() {
		return containers;
	}

	public void setContainers(Collection<Container> containers) {
		this.containers = containers;
	}



	public String display() {
		return null;
	};

	@Override
	public String toString() {

		return "Vehicle{" +
				"name='" + name + '\'' +
				", fuel='" + fuel + '\'' +
				", fuelCapacity=" + fuelCapacity +
				", carryCapacity=" + carryCapacity +
				", currentPort=" + currentPort +
				", containers=" + containers +
				'}';
	}

	// Searching container
	public boolean find(int idNumber)
	{

		// Iterating record list
		// using for each loop
		for (Container c : containers) {

			// Checking record by id Number
			if (c.getCNumber() == idNumber) {

				System.out.println(c);
				return true;
			}
		}
		return false;
	}
	//loadable container
	public boolean loadableContainer(Container container){
		double totalIfWeight = this.countWeight() + container.getWeight();return totalIfWeight <= this.getCarryCapacity();
	}

	// Load container (Similar to create C)
	public void loadContainer(Container container){
		if(!find(container.getCNumber())){
			if(this.loadableContainer(container)){
				containers.add(container);
			}else{
				System.out.println("The capicity is overdosed");
			}
		}else {
			System.out.println("The container already on the vehicle");
		}
	}

	//unload container(user input container id)(similar to delete D)
	public void unloadContainer(int cNumber){
		Container condel = null;
		for (Container c : containers){
			if(c.getCNumber() == cNumber){
				condel = c;
			}
		}
		if (condel != null){
			containers.remove(condel);
			System.out.println("Remove successfully");
		}else {
			System.out.print("Invalid id");
		}
	}

	// finding container R
	public Container findingContainer(int cNumber){
		for(Container c : containers){
			if(c.getCNumber() == cNumber){
				return c;
			}
		}
		return null;
	}

	// calculate weight
	public double countWeight(){
		int totalWeight = 0;
		for (Container con : containers){
			totalWeight += con.getWeight();
		}
		return  totalWeight;
	}
	// checking ability move to a port
	public boolean checkPortWeightAvailibity(Port port){
		double total = this.countWeight();
		return total <= port.getStoringCapacity();
	}

	//move to the port
	public void movePort(Port port){
		if (this.checkPortWeightAvailibity(port)){
			this.setCurrentPort(port);
		}else{
			System.out.println("Unable to move here");
		}
	}
	//refuel
	public void refuel(){
		if (this.getFuel() < this.getFuelCapacity()){
			this.setFuel(this.getFuelCapacity());
		}else{
			System.out.println("The fuel is full");
		}
	}
  
	//number of container
	public int checkConNumb(){
		return this.getContainers().size();
	}

	public static void main(String[] args) {
		// Mock some data for Containers and Ports
		Container container1 = new Container(1, 100, 50);
		Container container2 = new Container(2, 20, 60);
		Container container3 = new Container(3, 30, 80);
		Container container4 = new Container(4, 110, 100);


		// Create a Vehicle with some initial data
		Vehicle vehicle = new Vehicle("Vehicle1", 200, 300, 250, null, new ArrayList<>());
		Vehicle vehicle1 = new Vehicle("Vehicle1", 200, 300, 250, null, new ArrayList<>());
		// Display initial state

		//System.out.println(vehicle);

		// Load containers to the vehicle
		System.out.println("\nLoading Containers...");
		vehicle.loadContainer(container1);
		vehicle.loadContainer(container2);
		vehicle.loadContainer(container3);

		vehicle1.loadContainer(container1);
		vehicle1.loadContainer(container2);
		vehicle1.loadContainer(container4);
		// Display the state after loading containers
		System.out.println(vehicle.countWeight());
		System.out.println(vehicle1.countWeight());
	}
}

