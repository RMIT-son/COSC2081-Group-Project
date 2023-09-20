package main.vehicle;

import main.container.Container;
import main.porttrip.Port;

import java.io.*;
import java.util.*;

public class Vehicle implements Serializable, VehicleOperations {

	protected String name;
	protected double fuel;
	protected double fuelCapacity;
	protected double carryCapacity;
	protected Port currentPort;
	protected Collection<Container> containers;

	private final String FILENAME = "resources/vehicle.obj";

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
		double totalIfWeight = this.countWeight() + container.getWeight();
		return totalIfWeight <= this.getCarryCapacity();
	}

	// Load container (Similar to create C)
	public void loadContainer(Container container){
		if(!find(container.getCNumber())){
			if(this.loadableContainer(container)){
				if(container.getState() == Container.ContainerState.Neither){
					containers.add(container);
					container.setCurrentVehicle(this);
				}

			}else{
				System.out.println("The capicity is overdosed");
			}
		}else {
			System.out.println("The container already on the vehicle");
		}
	}

	//unload container(user input container id)(similar to delete D)
	public void unloadContainer(Container container){
		Container condel = null;
		for (Container c : containers){
			if(c.getCNumber() == container.getCNumber()){
				condel = c;
			}
		}
		if (condel != null){
			if(container.getState() == Container.ContainerState.AtVehicle){
				containers.remove(condel);
				System.out.println("Remove successfully");
				container.setCurrentVehicle(null);
				container.setState(Container.ContainerState.Neither);
			}

		}else {
			System.out.print("Invalid id");
		}
	}

	// finding container R
	public Container findingContainer(Container container){
		for(Container c : containers){
			if(c.getCNumber() == container.getCNumber()){
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



	//CRUD vehicle

	//Create vehicle
	public void createVehicle(){
		List<Vehicle> vehicles = readVehicle();
		vehicles.add(this);
		saveVechicle(vehicles);
	}
	// save all vehicles
	public void saveVechicle(Collection<Vehicle> vehicles) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
			oos.writeObject(vehicles);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Read
	public List<Vehicle> readVehicle() {
		try {
			FileInputStream fileIn = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			List<Vehicle> vehicles = (List<Vehicle>) in.readObject();
			in.close();
			fileIn.close();
			return vehicles;
		} catch (IOException i) {
			return new ArrayList<>();
		} catch (ClassNotFoundException c) {
			System.out.println("Vehicles class not found");
			return new ArrayList<>();
		}
	}

	// Update
	public void updatePort() {
		List<Vehicle> vehicles = readVehicle();
		for (int i = 0; i < vehicles.size(); i++) {
			if (Objects.equals(vehicles.get(i).getName(), this.getName())) {
				vehicles.set(i, this);
				break;
			}
		}
		saveVechicle(vehicles);
	}


	//Delete vehicle
	public void deleteVehicle() {
		List<Vehicle> vehicles = readVehicle();
		vehicles.removeIf(vehicle -> Objects.equals(vehicle.getName(), this.getName()));
		saveVechicle(vehicles);
	}




		public static void main(String[] args) {
			// Create a Port instance
			Port port1 = new Port(); // Assuming Port class has a default constructor and necessary setters.
			port1.setStoringCapacity(1000);  // Assuming setter method.


			// Create a Vehicle instance
			Vehicle vehicle1 = new Vehicle("Truck1", 50, 100, 500, port1, null);

			// Testing CRUD operations

			// Create
			vehicle1.createVehicle();
			System.out.println("Vehicle created!");

			// Read
			System.out.println("All vehicles: ");
			for (Vehicle v : vehicle1.readVehicle()) {
				System.out.println(v);
			}

			// Update: just for demonstration, updating the current port
			vehicle1.setCurrentPort(new Port());  // Moving to a new port (for the sake of testing).
			vehicle1.updatePort();
			System.out.println("Vehicle updated!");

			// Read to verify update
			System.out.println("All vehicles after update: ");
			for (Vehicle v : vehicle1.readVehicle()) {
				System.out.println(v);
			}

			List<Vehicle> readVehicles = vehicle1.readVehicle();

			// Display the read vehicles
			if (readVehicles.isEmpty()) {
				System.out.println("No vehicles found in the file.");
			} else {
				System.out.println("Vehicles read from the file:");
				for (Vehicle vehicle : readVehicles) {
					System.out.println(vehicle);
				}

				// Delete
				vehicle1.deleteVehicle();
				System.out.println("Vehicle deleted!");

				// Read to verify deletion
				System.out.println("All vehicles after deletion: ");
				for (Vehicle v : vehicle1.readVehicle()) {
					System.out.println(v);
				}

			}


		}
}

