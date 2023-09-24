package main;

import main.Users.User;
import main.container.Container;
import main.porttrip.Port;
import main.porttrip.Trip;
import main.vehicle.Vehicle;

import java.util.ArrayList;

import static main.porttrip.Port.readPort;

public class Query {
	public static void main(String[] args) {
		ArrayList<Port> ports = (ArrayList<Port>) readPort();
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) Vehicle.readVehicle();
		ArrayList<User> users = (ArrayList<User>) User.readUser();
		ArrayList<Container> containers = (ArrayList<Container>) Container.readContainer();
		ArrayList<Trip> trips = (ArrayList<Trip>) Trip.readTrip();

		System.out.println("Ports");
		for (Port port : ports) {
			System.out.println(port);
		}
		System.out.println("\nVehicles");
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle);
		}

		System.out.println("\nUsers");
		for (User user : users) {
			System.out.println(user);
		}

		System.out.println("\nContainers");
		for (Container container : containers) {
			System.out.println(container);
		}

		System.out.println("\nTrips");
		for (Trip trip : trips) {
			System.out.println(trip);
		}
		System.out.println("Done");
	}
}
