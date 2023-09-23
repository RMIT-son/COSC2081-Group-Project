package main.porttrip;

import main.vehicle.Ship;
import main.vehicle.Vehicle;
import main.container.Container;

import java.io.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Trip implements Serializable {
	protected Vehicle vehicle;
	protected LocalDate departureDate;
	protected LocalDate arrivalDate;
	protected Port departurePort;
	protected Port arrivalPort;
	protected String status;
	private static final String FILENAME = "resources/trips.obj";

	public Trip() {
	}

	public Trip(Vehicle vehicle, LocalDate departureDate, LocalDate arrivalDate, Port departurePort, Port arrivalPort) {

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
		this.updateTrip();
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
		this.updateTrip();
	}

	public Port getDeparturePort() {
		return departurePort;
	}

	public void setDeparturePort(Port departurePort) {
		this.departurePort = departurePort;
		this.updateTrip();
	}

	public Port getArrivalPort() {
		return arrivalPort;
	}

	public void setArrivalPort(Port arrivalPort) {
		this.arrivalPort = arrivalPort;
		this.updateTrip();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		this.updateTrip();
	}

	@Override
	public String toString() {
		return vehicle + " from " + departurePort + " to " + arrivalPort + " on " + departureDate + " to " + arrivalDate;
	}

	//	Calculates the duration of the trip.
	public long getDuration() {
		if (arrivalDate != null) {
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

	public static double getFuelConsumptionADay(Vehicle vehicle, double distance) {
		double totalFuelConsumption = 0.0;

		for (Container container : vehicle.getContainers()) {
			totalFuelConsumption += container.calculateFuel(vehicle, distance);
		}

		return totalFuelConsumption;
	}

	// List all the trips on a given day
	public static List<Trip> listTripsOnDate(LocalDate date) {
		List<Trip> trips = readTrip();
		List<Trip> result = new ArrayList<>();

		for (Trip trip : trips) {
			if ((!trip.getDepartureDate().isBefore(date)) && (!trip.getArrivalDate().isAfter(date))) {
				result.add(trip);
			}
		}
		return result;
	}

		// List all the trips from day A to day B
		public static List<Trip> listTripsBetweenDates (LocalDate startDate, LocalDate endDate){
			List<Trip> trips = readTrip();
			List<Trip> result = new ArrayList<>();

			for (Trip trip : trips) {
				if ((!trip.getDepartureDate().isBefore(startDate) && !trip.getDepartureDate().isAfter(endDate)) &&
						(!trip.getArrivalDate().isBefore(startDate) && !trip.getArrivalDate().isAfter(endDate))) {
					result.add(trip);
				}
			}
			return result;
		}

	public void scheduleTrip(Vehicle vehicle, Port departurePort, Port arrivalPort, LocalDate departureDate, LocalDate arrivalDate) {

		// Check if the provided vehicle is currently at the departure port
		if (vehicle.getCurrentPort() == null || !vehicle.getCurrentPort().equals(departurePort)) {
			System.out.println("The vehicle is not at the departure port.");
			return;
		}

		// Check if the vehicle can move to the arrival port
		if (!departurePort.canMoveTo(arrivalPort)) {
			System.out.println("The vehicle cannot move to the destination port.");
			return;
		}

		// Check if the total weight of all containers inside the vehicle is loadable at the arrival port
		if (vehicle.countWeight() + arrivalPort.getCurrentContainerWeightAtPort()> arrivalPort.getStoringCapacity()) {
			System.out.println("The weight of the vehicle exceeds the destination port's storing capacity.");
			return;
		}

		// Update the trip details with provided parameters
		this.vehicle = vehicle;
		this.departurePort = departurePort;
		this.arrivalPort = arrivalPort;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;

		LocalDate currentDate = LocalDate.now();

		if (currentDate.isBefore(departureDate)) {
			System.out.println("The trip is in the future and hasn't started yet.");
			setStatus("Scheduled");
			return;
		}

		if (currentDate.equals(departureDate) || (currentDate.isAfter(departureDate) && currentDate.isBefore(arrivalDate))) {
			// In transit phase
			setStatus("In Transit");
			vehicle.setCurrentPort(null);
			for (Container c : vehicle.getContainers()) {
				c.setState(Container.ContainerState.Neither);
			}
//			departurePort.removeVehicle(vehicle); // Remove the vehicle from the current port's list of vehicles
			System.out.println(vehicle.getName() + " is currently in transit...");
		}

		if (currentDate.equals(arrivalDate) || currentDate.isAfter(arrivalDate)) {
			// Arrival phase
			vehicle.setCurrentPort(arrivalPort);
			for (Container c : vehicle.getContainers()) {
				c.setState(Container.ContainerState.AtPort);
			}
//			arrivalPort.addVehicle(vehicle);  // Add the vehicle to the arrival port's list of vehicles
			setStatus("Completed");
			System.out.println(vehicle.getName() + " has completed its trip and arrived at " + arrivalPort.getName() + " on " + arrivalDate);
		}
	}

		// Deletes the history of trips that have been completed for over 7 days
		public static void deleteTripsCompletedAfter7Days() {
			List<Trip> trips = readTrip();
			LocalDate currentDate = LocalDate.now();

			trips.removeIf(trip -> trip.isTripCompleted() &&
					trip.getArrivalDate().plusDays(7).isBefore(currentDate));

			saveTrip(trips);
		}

		// Create
		public void createTrip (){
			List<Trip> trips = readTrip();
			trips.add(this);
			saveTrip(trips);
		}

		// Read
		public static List<Trip> readTrip () {
			try {
				FileInputStream fileIn = new FileInputStream(FILENAME);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				List<Trip> trips = (List<Trip>) in.readObject();
				in.close();
				fileIn.close();
				return trips;
			} catch (IOException i) {
				return new ArrayList<>();
			} catch (ClassNotFoundException c) {
				System.out.println("Trip class not found");
				return new ArrayList<>();
			}
		}

		// Update
		public void updateTrip (){
			List<Trip> trips = readTrip();
			for (int i = 0; i < trips.size(); i++) {
				if (Objects.equals(trips.get(i).getVehicle(), this.getVehicle())) {
					trips.set(i, this);
					break;
				}
			}
			saveTrip(trips);
		}

		// Delete
		public void deleteTrip (){
			List<Trip> trips = readTrip();
			trips.removeIf(trip -> Objects.equals(trip.getVehicle(), this.getVehicle()));
			saveTrip(trips);
		}

		private static void saveTrip(List<Trip> trips) {
			try {
				FileOutputStream fileOut = new FileOutputStream(FILENAME);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(trips);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
	}
