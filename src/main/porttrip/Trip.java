package main.porttrip;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import main.vehicle.Vehicle;
import main.container.Container;
import org.bson.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

public class Trip {
	protected Vehicle vehicle;
	protected LocalDate departureDate;
	protected LocalDate arrivalDate;
	protected Port departurePort;
	protected Port arrivalPort;
	protected String status;

	public Trip() {}

	public Trip(Vehicle vehicle, LocalDate departureDate, LocalDate arrivalDate, Port departurePort, Port arrivalPort, String status) {

		this.vehicle = vehicle;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.departurePort = departurePort;
		this.arrivalPort = arrivalPort;
		this.status = "Scheduled";  // default status
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setDates(LocalDate departureDate, LocalDate arrivalDate) throws IllegalArgumentException {
		if (departureDate.isAfter(arrivalDate)) {
			throw new IllegalArgumentException("Departure date cannot be after arrival date");
		}

		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}

	public Port getDeparturePort() {
		return departurePort;
	}

	public void setDeparturePort(Port departurePort) {
		this.departurePort = departurePort;
	}

	public Port getArrivalPort() {
		return arrivalPort;
	}

	public void setArrivalPort(Port arrivalPort) {
		this.arrivalPort = arrivalPort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	Calculates the duration of the trip.
	public long getDuration() {
		if(arrivalDate != null) {
			return arrivalDate.getDayOfYear() - departureDate.getDayOfYear();
		}
		return -1;
	}


//	Determines if the trip has been completed.
	public boolean isTripCompleted() {
		return "Completed".equals(status);
	}

//	Lists all containers onboard the vehicle during this trip.
	public Collection<Container> getContainerOnTrips() {
		return vehicle.getContainers();
	}

	public static LocalDate convertDateToLocalDate(Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	// Create
	public void createTrip() {
		MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
		MongoCollection<Document> collection = mongoClient.getDatabase("PMS").getCollection("trips");
		Document doc = new Document("vehicle", vehicle)
				.append("departureDate", departureDate)
				.append("arrivalDate", arrivalDate)
				.append("departurePort", departurePort)
				.append("arrivalPort", arrivalPort)
				.append("status", status);

		collection.insertOne(doc);
	}

	// Read
	public static Trip getTripByVehicle(Vehicle vehicle) {
		MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
		MongoCollection<Document> collection = mongoClient.getDatabase("PMS").getCollection("trips");
		Document tripDoc = collection.find(Filters.eq("vehicle", vehicle)).first();
		if (tripDoc == null) {
			System.out.println("Trip: not exist");
			return null;
		}
		System.out.println("Trip: " + tripDoc.toJson());

		LocalDate departureDate = convertDateToLocalDate(tripDoc.getDate("departureDate"));
		LocalDate arrivalDate = convertDateToLocalDate(tripDoc.getDate("arrivalDate"));
		Port departurePort = (Port) tripDoc.get("departurePort");
		Port arrivalPort = (Port) tripDoc.get("arrivalPort");
		String status = tripDoc.getString("status");
		Trip trip = new Trip(vehicle, departureDate, arrivalDate, departurePort, arrivalPort, status);
		return trip;
	}

	// Update
	public void updateTripStatus(String newStatus) {
		MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
		MongoCollection<Document> collection = mongoClient.getDatabase("PMS").getCollection("trips");
		collection.updateOne(
				Filters.eq("vehicle", vehicle),
				Updates.set("status", newStatus));
	}

	// Delete
	public void deleteTrip() {
		MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));
		MongoCollection<Document> collection = mongoClient.getDatabase("PMS").getCollection("trips");
		collection.deleteOne(Filters.eq("vehicle", vehicle));
	}

//	private static void deleteOldTrips() {
//		try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
//			MongoCollection<Document> collection = mongoClient.getDatabase("PMS").getCollection("trips");
//
//			// Calculate the cutoff date for deletion
//			Date cutoffDate = Date.from(
//					LocalDate.now().minusDays(7).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()
//			);
//
//			// Delete trips older than 7 days based on arrivalDate
//			collection.deleteMany(Filters.lt("arrivalDate", cutoffDate));
//		}
//	}
}
