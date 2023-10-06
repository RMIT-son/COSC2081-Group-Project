package main;

import main.Users.User;
import main.container.Container;
import main.porttrip.Port;
import main.porttrip.Trip;
import main.vehicle.Vehicle;

import java.util.ArrayList;

import static main.porttrip.Port.readPort;

public class Wipe {
	public static void main(String[] args) {
		ArrayList<Port> ports = (ArrayList<Port>) readPort();
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) Vehicle.readVehicle();
		ArrayList<User> users = (ArrayList<User>) User.readUser();
		ArrayList<Container> containers = (ArrayList<Container>) Container.readContainer();
		ArrayList<Trip> trips = (ArrayList<Trip>) Trip.readTrip();

		for (Port port : ports) {
			port.deletePort();
		}

		for (Vehicle vehicle : vehicles) {
			vehicle.deleteVehicle();
		}

		for (User user : users) {
			user.deleteUser();
		}

		for (Container container : containers) {
			container.deleteContainer();
		}

		for (Trip trip : trips) {
			trip.deleteTrip();
		}

		System.out.println("Done");
	}
}
