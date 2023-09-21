package main.porttrip;

import main.container.Liquid;
import main.container.OpenSide;
import main.vehicle.Ship;
import main.vehicle.Truck;
import main.vehicle.Vehicle;
import main.container.Container;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Port implements Serializable, PortOperations {
	protected int pNumber;
	protected String name;
	protected boolean landingAbility;
	protected double latitude;
	protected double longitude;
	protected double storingCapacity;
	protected Collection<Trip> traffic;
	protected Collection<Container> containers;
	protected Collection<Vehicle> vehicles;
	private final String FILENAME = "resources/ports.obj";

	public Port() {
	}

	public Port(int pNumber, String name, boolean landingAbility, double latitude, double longitude, double storingCapacity, Collection<Trip> traffic, Collection<Container> containers, Collection<Vehicle> vehicles) {
		this.pNumber = pNumber;
		this.name = name;
		this.landingAbility = landingAbility;
		this.latitude = latitude;
		this.longitude = longitude;
		this.storingCapacity = storingCapacity;
		this.traffic = traffic;
		this.containers = containers;
		this.vehicles = vehicles;
	}

	public int getPNumber() {
		return pNumber;
	}

	public void setPNumber(int pNumber) {

		this.pNumber = pNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.updatePort();
	}

	public boolean isLandingAbility() {
		return landingAbility;
	}

	public void setLandingAbility(boolean landingAbility) {
		this.landingAbility = landingAbility;
		this.updatePort();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
		this.updatePort();
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
		this.updatePort();
	}


	public double getStoringCapacity() {
		return storingCapacity;
	}

	public void setStoringCapacity(double storingCapacity) {
		this.storingCapacity = storingCapacity;
		this.updatePort();
	}

	public Collection<Trip> getTraffic() {
		return traffic;
	}

	public void setTraffic(Collection<Trip> traffic) {
		this.traffic = traffic;
		this.updatePort();
	}

	public Collection<Container> getContainers() {
		return containers;
	}

	public void setContainers(Collection<Container> containers) {
		this.containers = containers;
		this.updatePort();
	}

	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
		this.updatePort();
	}


	// Calculate current weight of containers
	public double getCurrentContainerWeightAtPort() {
		double totalWeight = 0;
		for (Container container : containers) {
			totalWeight += container.getWeight();
		}
		return totalWeight;
	}

	// Method to get the number of containers
	public int getNumberOfContainers() {
		return containers.size();
	}

	// Method to get the number of vehicles
	public int getNumberOfVehicles() {
		return vehicles.size();
	}

	// Searching container in the port
	public boolean findContainer(int idNumber)
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

	// Searching vehicle in the port
	public boolean findVehicle(int idNumber)
	{

		// Iterating record list
		// using for each loop
		for (Vehicle v : vehicles) {

			// Checking record by id Number
			if (v instanceof Truck && ((Truck) v).getTNumber() == idNumber) {
				System.out.println(v);
				return true;
			} else if (v instanceof Ship && ((Ship) v).getSNumber() == idNumber) {
				System.out.println(v);
				return true;
			}
		}
		return false;
	}

//	Weight check if the container is loadable to the current port
	public boolean loadableContainerToPort(Container container){
		double totalIfWeight = this.getCurrentContainerWeightAtPort() + container.getWeight();
		return totalIfWeight <= this.getStoringCapacity();
	}

	// Load a container to the port with weight check
	public Collection<Container> loadContainerToPort(Container container) {
		if (!findContainer(container.getCNumber())) {
			if (this.loadableContainerToPort(container)) {
				if (container.getState() == Container.ContainerState.Neither) {
					containers.add(container);
					container.setCurrentPort(this);
					this.updatePort();
				} else {
					System.out.println("The capicity is overdosed");
				}
			} else {
				System.out.println("The container already in the port");
			}
		}
		return containers;
	}

//	Remove a container from the port
	public Collection<Container> unloadContainerFromPort(Container container){
		for (Container c : containers){
			if(c.getCNumber() == container.getCNumber()){
				container = c;
			}
		}
		if (container != null){
			if(container.getState() == Container.ContainerState.AtPort){
				containers.remove(container);
				System.out.println("Remove successfully from port");
				container.setCurrentPort(null);
				container.setState(Container.ContainerState.Neither);
				this.updatePort();
			}

		}else {
			System.out.print("Invalid id");
		}
		return containers;
	}

//	Add a vehicle to the port
//	public Collection<Vehicle> addVehicle(Vehicle vehicle) {
//		if (!findVehicle(vehicle.getId())) {
//			this.getVehicles().add(vehicle);
//			this.updatePort();
//		}
//		else {
//			System.out.println("The container already in the port");
//		}
//		return vehicles;
//	}

//	Remove a vehicle from the port
//	public Collection<Vehicle> removeVehicle(Vehicle vehicle) {
//		for (Vehicle v : vehicles) {
//			if(v.getId() == vehicle.getId()) {
//				vehicle = v;
//			}
//		}
//		if (vehicle != null) {
//			this.getVehicles().remove(vehicle);
//			this.updatePort();
//		}
//		else {
//			System.out.print("Invalid id");
//		}
//		return vehicles;
//	}

	@Override
	public String toString() {
		return name;
	}

	public double distanceTo(Port otherPort) {
		final double R = 6371; // Earth's radius in km

		double latDistance = Math.toRadians(otherPort.latitude - this.latitude);
		double lonDistance = Math.toRadians(otherPort.longitude - this.longitude);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(otherPort.latitude))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}

	// Check landing ability with another port
	public boolean canMoveTo(Port otherPort) {
		return this.landingAbility && otherPort.landingAbility;
	}

	// Create
	public void createPort() {
		List<Port> ports = readPort();
		ports.add(this);
		savePort(ports);
	}

	// Read
	public List<Port> readPort() {
		try {
			FileInputStream fileIn = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			List<Port> ports = (List<Port>) in.readObject();
			in.close();
			fileIn.close();
			return ports;
		} catch (IOException i) {
			return new ArrayList<>();
		} catch (ClassNotFoundException c) {
			System.out.println("Port class not found");
			return new ArrayList<>();
		}
	}

	// Update
	public void updatePort() {
		List<Port> ports = readPort();
		for (int i = 0; i < ports.size(); i++) {
			if (Objects.equals(ports.get(i).getPNumber(), this.getPNumber())) {
				ports.set(i, this);
				break;
			}
		}
		savePort(ports);
	}

	// Delete
	public void deletePort() {
		List<Port> ports = readPort();
		ports.removeIf(port -> Objects.equals(port.getPNumber(), this.getPNumber()));
		savePort(ports);
	}

	private void savePort(Collection<Port> ports) {
		try {
			FileOutputStream fileOut = new FileOutputStream(FILENAME);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(ports);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Creating instance of the CRUD class
		Port portCRUD = new Port();

		// Testing Create
//		portCRUD.createPort(port1);
//		portCRUD.createPort(port2);
//
//		// Testing Read
		List<Port> ports = portCRUD.readPort();
//		for (Port port : ports) {
//			System.out.println(port);
//		}
//
//		// Updating port1's name and then saving it
//		port1.setStoringCapacity(6000);
//		portCRUD.updatePort(port1);
//
//		// Testing if the name got updated
//		ports = portCRUD.readPort();
//		for (Port port : ports) {
//			System.out.println(port);
//		}


		// Let's assume you also have a Vehicle class with a default constructor
			Collection<Container> shipContainers = new ArrayList<>();

		// Creating some test data
		Port port1 = new Port(1, "PortA", true, 34.0522, -118.2437, 5000, new ArrayList<>(), shipContainers, new ArrayList<>());
		Port port2 = new Port(2, "PortB", true, 36.7783, -119.4179, 5500, new ArrayList<>(), shipContainers, new ArrayList<>());

		Vehicle vehicle1 = new Ship(5, "Cargo Ship", 3000, 5000, 2000, port1, shipContainers);
		Vehicle vehicle2 = new Vehicle("Truck2", 100, 200, 300, port2, shipContainers);
		// Creating Container instances
		Container container1 = new Liquid(101, 1000, 50, vehicle1, port1, Container.ContainerState.Neither);
		Container container2 = new OpenSide(102, 1500, 75, vehicle2, port2, Container.ContainerState.Neither);
			shipContainers.add(container1);

		// Test adding containers
		portCRUD.loadContainerToPort(container1);
		portCRUD.unloadContainerFromPort(container2);

//		// Test getting current container weight
//		System.out.println("Current weight of containers in port1: " + port2.getCurrentContainerWeight());
//
//		// Test getNumberOfContainers
//		System.out.println("Number of containers in port1: " + port2.getNumberOfContainers());

		port2.getVehicles().add(vehicle1);
		port2.getVehicles().add(vehicle2);

		for (Port port : ports) {
			System.out.println(port);
		}

//		// Test getNumberOfVehicles
//		System.out.println("Number of vehicles in port1: " + port2.getNumberOfVehicles());
//
//		// Test distanceTo
//		System.out.println("Distance from port1 to port2: " + port2.distanceTo(port1) + " km");
//
//		// Test canMoveTo
//		System.out.println("Can move from port1 to port2: " + port2.canMoveTo(port1));

		// Testing Delete
		ports = portCRUD.readPort();
		for (Port port : ports) {
			portCRUD.deletePort();
		}

		ports = portCRUD.readPort();
		for (Port port : ports) {
			System.out.println(port);
		}
	}
}