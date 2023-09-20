package main.porttrip;

import main.vehicle.Vehicle;
import main.container.Container;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Collection;

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
	}

	public boolean isLandingAbility() {
		return landingAbility;
	}

	public void setLandingAbility(boolean landingAbility) {
		this.landingAbility = landingAbility;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getStoringCapacity() {
		return storingCapacity;
	}

	public void setStoringCapacity(double storingCapacity) {
		this.storingCapacity = storingCapacity;
	}

	public Collection<Trip> getTraffic() {
		return traffic;
	}

	public void setTraffic(Collection<Trip> traffic) {
		this.traffic = traffic;
	}

	public Collection<Container> getContainers() {
		return containers;
	}

	public void setContainers(Collection<Container> containers) {
		this.containers = containers;
	}

	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}


	// Calculate current weight of containers
	public double getCurrentContainerWeight() {
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

	// Add a container with weight check
	public boolean addContainer(Container container) {
		if (getCurrentContainerWeight() + container.getWeight() <= storingCapacity) {
			containers.add(container);
			return true;
		} else {
			System.out.println("Exceeds storage capacity!");
			return false;
		}
	}

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
	public void createPort(Port port) {
		List<Port> ports = readPort();
		ports.add(port);
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
	public void updatePort(Port updatedPort) {
		List<Port> ports = readPort();
		for (int i = 0; i < ports.size(); i++) {
			if (ports.get(i).getPNumber() == updatedPort.getPNumber()) {
				ports.set(i, updatedPort);
				break;
			}
		}
		savePort(ports);
	}

	// Delete
	public void deletePort(Port portToDelete) {
		List<Port> ports = readPort();
		ports.removeIf(port -> port.getPNumber() == portToDelete.getPNumber());
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

	/*public static void main(String[] args) {
		// Creating instance of the CRUD class
		Port portCRUD = new Port();

		// Creating some test data
		Port port1 = new Port(1, "PortA", true, 34.0522, -118.2437, 5000, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		Port port2 = new Port(2, "PortB", true, 36.7783, -119.4179, 5500, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

		// Testing Create
		portCRUD.createPort(port1);
		portCRUD.createPort(port2);

		// Testing Read
		List<Port> ports = portCRUD.readPort();
		for (Port port : ports) {
			System.out.println(port);
		}

		// Updating port1's name and then saving it
		port1.setStoringCapacity(6000);
		portCRUD.updatePort(port1);

		// Testing if the name got updated
		ports = portCRUD.readPort();
		for (Port port : ports) {
			System.out.println(port);
		}

		// Creating Container instances
		Container container1 = new Container(101, 1000, 50);
		Container container2 = new Container(102, 1500, 75);

		// Test adding containers
		System.out.println("Adding container1 to port1: " + port2.addContainer(container1));
		System.out.println("Adding container2 to port1: " + port2.addContainer(container2));

		// Test getting current container weight
		System.out.println("Current weight of containers in port1: " + port2.getCurrentContainerWeight());

		// Test getNumberOfContainers
		System.out.println("Number of containers in port1: " + port2.getNumberOfContainers());

		// Let's assume you also have a Vehicle class with a default constructor
		Vehicle vehicle1 = new Vehicle();
		Vehicle vehicle2 = new Vehicle();

		port2.getVehicles().add(vehicle1);
		port2.getVehicles().add(vehicle2);

		// Test getNumberOfVehicles
		System.out.println("Number of vehicles in port1: " + port2.getNumberOfVehicles());

		// Test distanceTo
		System.out.println("Distance from port1 to port2: " + port2.distanceTo(port1) + " km");

		// Test canMoveTo
		System.out.println("Can move from port1 to port2: " + port2.canMoveTo(port1));

		// Testing Delete
		ports = portCRUD.readPort();
		for (Port port : ports) {
			portCRUD.deletePort(port);
		}

		ports = portCRUD.readPort();
		for (Port port : ports) {
			System.out.println(port);
		}
	}*/
}