package main;
import org.bson.types.ObjectId;

import java.util.Collection;

public class Port {
	protected int pNumber;
	protected String name;

	protected boolean landingAbility;
	protected double latitude;
	protected double longitude;
	protected double storingCapacity;
	protected Collection<Trip> traffic;
	protected Collection<Container> containers;
	protected Collection<Vehicle> vehicles;

	public Port(int pNumber, String name, boolean landingAbility,double latitude, double longitude, double storingCapacity, Collection<Trip> trips, Collection<Container> containers, Collection<Vehicle> vehicles) {
		this.pNumber = pNumber;
		this.name = name;
		this.landingAbility = landingAbility;
		this.latitude = latitude;
		this.longitude = longitude;
		this.storingCapacity = storingCapacity;
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
}
