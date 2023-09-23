package main.InterfaceUtils;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import main.Users.PortManager;
import main.Users.SystemAdmin;
import main.Users.User;
import main.container.Container;
import main.porttrip.Port;
import main.porttrip.Trip;
import main.vehicle.Ship;
import main.vehicle.Truck;
import main.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class displayUtils {
	public static void displayPorts(Collection<Port> ports) {
		System.out.println(AsciiTable.getTable(ports, Arrays.asList(
				new Column().header("pNumber").with(port -> Integer.toString(port.getPNumber())),
				new Column().header("Name").with(Port::getName),
				new Column().header("Latitude").with(port -> Double.toString(port.getLatitude())),
				new Column().header("Longitude").with(port -> Double.toString(port.getLongitude())),
				new Column().header("Capacity").with(port -> Double.toString(port.getStoringCapacity())),
				new Column().header("Landing Ability").with(port -> Boolean.toString(port.isLandingAbility())),
				new Column().header("Vehicles").with(port -> port.getVehicles() != null ? Integer.toString(port.getVehicles().size()) : "N/A"),
				new Column().header("Containers").with(port -> port.getContainers() != null ? Integer.toString(port.getContainers().size()) : "N/A"),
				new Column().header("Trips").with(port -> port.getTraffic() != null ? Integer.toString(port.getTraffic().size()) : "N/A"))));
	}

	public static void displayVehicles(Collection<Vehicle> vehicles) {
		System.out.println(AsciiTable.getTable(vehicles, Arrays.asList(
				new Column().header("Number").with(vehicle -> {
					if (vehicle instanceof Truck) {
						return Integer.toString(((Truck) vehicle).getTNumber());
					} else if (vehicle instanceof Ship) {
						return Integer.toString(((Ship) vehicle).getSNumber());
					} else {
						return "N/A";
					}
				}),
				new Column().header("Name").with(Vehicle::getName),
				new Column().header("Current Fuel").with(vehicle -> Double.toString(vehicle.getFuel())),
				new Column().header("Fuel Capacity").with(vehicle -> Double.toString(vehicle.getFuelCapacity())),
				new Column().header("Carry Capacity").with(vehicle -> Double.toString(vehicle.getCarryCapacity())),
				new Column().header("Current Port").with(vehicle -> vehicle.getCurrentPort() != null ? vehicle.getCurrentPort().toString(): "N/A"),
				new Column().header("Containers").with(vehicle -> vehicle.getContainers() != null ? Integer.toString(vehicle.getContainers().size()) : "N/A"))));
	}

	public static void displayContainers(Collection<Container> containers) {
		System.out.println(AsciiTable.getTable(containers, Arrays.asList(
				new Column().header("cNumber").with(container -> Integer.toString(container.getCNumber())),
				new Column().header("Weight").with(container -> Double.toString(container.getWeight())),
				new Column().header("Fuel Required").with(container -> Double.toString(container.getRequiredFuel())),
				new Column().header("Current Port").with(container -> container.getCurrentPort() != null ? container.getCurrentPort().toString() : "N/A"),
				new Column().header("Current Vehicle").with(container -> container.getCurrentVehicle() != null ? container.getCurrentVehicle().toString() : "N/A"))));
	}

	public static void displayTrips(Collection<Trip> trips) {
		// TODO Finish displayTrips
		System.out.println(AsciiTable.getTable(trips, Arrays.asList(
				new Column().header("Trips").with(Trip::toString),
				new Column().header("Status").with(Trip::getStatus))));
	}

	public  static void displayUsers(Collection<User> users) {
		System.out.println(AsciiTable.getTable(users, Arrays.asList(
				new Column().header("Username").with(User::getUsername),
				new Column().header("Password").with(User::getPassword),
				new Column().header("User Type").with(user -> {
					if (user instanceof PortManager) {
						return "Port Manager";
					} else if (user instanceof SystemAdmin) {
						return "System Admin";
					} else {
						return "N/A";
					}
				}),
				new Column().header("Port Managing").with(user -> {
					if (user instanceof PortManager) {
						return ((PortManager) user).getPortManaging().toString();
					} else {
						return "N/A";
					}
				}))));
	}
}
