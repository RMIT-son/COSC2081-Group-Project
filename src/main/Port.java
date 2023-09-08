package main;

import java.util.Collection;

public class Port {
	protected int pNumber;
	protected String name;
	protected double latitude;
	protected double longitude;
	protected double storingCapacity;
	protected boolean landingAbility;
	protected Collection<Trip> traffic;
	protected Collection<Container> containers;
	protected Collection<Vehicle> vehicles;

	public Port(int pNumber, String name, double latitude, double longitude, double storingCapacity, Collection<Trip> trips, Collection<Container> containers, Collection<Vehicle> vehicles) {
		this.pNumber = pNumber;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.storingCapacity = storingCapacity;
		this.landingAbility = landingAbility;
		this.traffic = trips;
		this.containers = containers;
		this.vehicles = vehicles;
}
  
	public int getpNumber() {
		return pNumber;
	}

	public void setpNumber(int pNumber) {

		this.pNumber = pNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isLandingAbility() {
		return landingAbility;
	}

	public void setLandingAbility(boolean landingCapacity) {
		this.landingAbility = landingAbility;
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
}
