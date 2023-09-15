package main;

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

	public Port () {}
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

	@Override
	public String toString() {
		return "Port{" +
				"pNumber=" + pNumber +
				", name='" + name + '\'' +
				'}';
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
//	public void createPort() {
//		MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
//		MongoCollection<Document> collection = mongoClient.getDatabase("PMS").getCollection("ports");
//		Document doc = new Document("pNumber", pNumber)
//				.append("name", name)
//				.append("latitude", latitude)
//				.append("longtitude", longitude)
//				.append("strongCapicity", storingCapacity)
//				.append("landingAbility", landingAbility)
//				.append("traffic", Trip.class)
//				.append("vehicles", Vehicle.class)
//				.append("containers", Container.class);
//
//		collection.insertOne(doc);
//	}
//
//	// Read
//	public static Port getPortByNumber(int pNumber) {
//		MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
//		MongoCollection<Document> collection = mongoClient.getDatabase("PMS").getCollection("ports");
//		Document portDoc = collection.find(Filters.eq("pNumber", pNumber)).first();
//		if (portDoc == null) {
//			System.out.println("Port: not exist");
//			return null;
//		}
//		System.out.println("Port: "+ portDoc.toJson());
//		String name = portDoc.getString("name");
//		boolean landingAbility = portDoc.getBoolean("landingAbility");
//		double latitude = portDoc.getDouble("latitude");
//		double longitude = portDoc.getDouble("longitude");
//		double storingCapacity = portDoc.getDouble("storingCapacity");
//		Collection<Trip> trips = portDoc.getList("traffic", Trip.class);
//		Collection<Vehicle> vehicles = portDoc.getList("vehicles", Vehicle.class);
//		Collection<Container> containers = portDoc.getList("containers", Container.class);
//
//		Port port = new Port(pNumber, name, landingAbility, latitude, longitude, storingCapacity, trips, containers, vehicles);
//		return port;
//	}
//
//	// Update
//	public void updatePortName(String newName) {
//		MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
//		MongoCollection<Document> collection = mongoClient.getDatabase("PMS").getCollection("ports");
//		collection.updateOne(
//				Filters.eq("pNumber", pNumber),
//				Updates.set("name", newName));
//	}
//
//	// Delete
//	public void deletePort() {
//		MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
//		MongoCollection<Document> collection = mongoClient.getDatabase("PMS").getCollection("ports");
//		collection.deleteOne(Filters.eq("pNumber", pNumber));
//	}

}
