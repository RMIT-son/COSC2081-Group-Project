import java.util.Collection;

public class Port {
	protected int pNumber;
	protected String name;
	protected double latitude;
	protected double longitude;
	protected double storingCapacity;
	protected int numOfContainers;
	protected int numOfVehicles;
	protected Collection<Trip> trips;
	protected Collection<Container> containers;
	protected Collection<Vehicle> vehicles;

	public Port(int pNumber, String name, double latitude, double longitude, double storingCapacity, int numOfContainers, int numOfVehicles, Collection<Trip> trips, Collection<Container> containers, Collection<Vehicle> vehicles) {
		this.pNumber = pNumber;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.storingCapacity = storingCapacity;
		this.numOfContainers = numOfContainers;
		this.numOfVehicles = numOfVehicles;
		this.trips = trips;
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

	public int getNumOfContainers() {
		return numOfContainers;
	}

	public void setNumOfContainers(int numOfContainers) {
		this.numOfContainers = numOfContainers;
	}

	public int getNumOfVehicles() {
		return numOfVehicles;
	}

	public void setNumOfVehicles(int numOfVehicles) {
		this.numOfVehicles = numOfVehicles;
	}

	public Collection<Trip> getTrips() {
		return trips;
	}

	public void setTrips(Collection<Trip> trips) {
		this.trips = trips;
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
