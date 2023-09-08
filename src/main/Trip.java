package main;

import java.time.LocalDate;
import java.util.Collection;

public class Trip {
	protected Vehicle vehicle;
	protected LocalDate departureDate;
	protected LocalDate arrivalDate;
	protected Port departurePort;
	protected Port arrivalPort;
	protected String status;

	public Trip(Vehicle vehicle, LocalDate departureDate, LocalDate arrivalDate, Port departurePort, Port arrivalPort, boolean status) {
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

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
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

//	Updates the status of the trip.
	public void updateStatus(String newStatus) {
		this.status = newStatus;
	}

//	Determines if the trip has been completed.
	public boolean isTripCompleted() {
		return "Completed".equals(status);
	}

//	Lists all containers onboard the vehicle during this trip.
	public Collection<Container> getContainerOnTrips() {
		return vehicle.getContainers();
	}
}
