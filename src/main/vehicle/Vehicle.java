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
					updateVehicle(this);
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
				updateVehicle(this);

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
			updateVehicle(this);
		}else{
			System.out.println("Unable to move here");
		}
	}
	//refuel
	public void refuel(){
		if (this.getFuel() < this.getFuelCapacity()){
			this.setFuel(this.getFuelCapacity());
			updateVehicle(this);
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
	public void createVehicle(Vehicle vehicle){
		List<Vehicle> vehicles = readVehicle();
		vehicles.add(vehicle);
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
	public void updateVehicle(Vehicle updatedVehicle) {
		List<Vehicle> vehicles = readVehicle();
		for (int i = 0; i < vehicles.size(); i++) {
			if (Objects.equals(vehicles.get(i).getName(), updatedVehicle.getName())) {
				vehicles.set(i, updatedVehicle);
				break;
			}
		}
		saveVechicle(vehicles);
	}


	//Delete vehicle
	public void deleteVehicle(Vehicle deletedVehicle) {
		List<Vehicle> vehicles = readVehicle();
		vehicles.removeIf(vehicle -> Objects.equals(vehicle.getName(), deletedVehicle.getName()));
		saveVechicle(vehicles);
	}



		//main test crud
		public static void main(String[] args) {
			System.out.println("Starting tests...");

			// Initialize a port
			Port port1 = new Port();
			port1.setStoringCapacity(1000);  // Assuming setter method.

			// Create a Vehicle
			Vehicle vehicle1 = new Vehicle("Truck1", 50, 100, 500, port1, new ArrayList<>());

			// CRU[D] operations on Vehicle
			vehicle1.createVehicle(vehicle1);
			System.out.println("Vehicle created!");
			displayAllVehicles(vehicle1);

			// Update Vehicle
			vehicle1.setFuel(90); // For demonstration purposes

			System.out.println("Vehicle updated!");
			displayAllVehicles(vehicle1);

			// Create Containers and Load them onto Vehicle
			Container container1 = new Container(1, 100, 5, null, null, Container.ContainerState.Neither);
			Container container2 = new Container(2, 150, 8, null, null, Container.ContainerState.Neither);

			vehicle1.loadContainer(container1);
			vehicle1.loadContainer(container2);


			System.out.println("Containers loaded!");
			displayAllVehicles(vehicle1);

			// Unload Container
			vehicle1.unloadContainer(container2);
			System.out.println("Container 2 unloaded!");
			displayAllVehicles(vehicle1);

			// Refuel the Vehicle
			vehicle1.refuel();
			System.out.println("Vehicle refueled!");
			displayAllVehicles(vehicle1);

			// [D]elete Vehicle
			vehicle1.deleteVehicle(vehicle1);
			System.out.println("Vehicle deleted!");
			displayAllVehicles(vehicle1);

			System.out.println("Tests completed!");
		}

		private static void displayAllVehicles(Vehicle vehicle) {
			System.out.println("All vehicles: ");
			for (Vehicle v : vehicle.readVehicle()) {
				System.out.println(v);
			}
		}









}

