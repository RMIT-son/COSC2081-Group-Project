package main.InterfaceUtils;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import main.Users.User;
import main.container.Container;
import main.porttrip.Port;
import main.porttrip.Trip;
import main.vehicle.Vehicle;

import java.util.Arrays;
import java.util.Collection;

public class displayUtils {
	public static void displayPorts(Collection<Port> ports) {
		// TODO Finish displayPorts
		System.out.println(AsciiTable.getTable(ports, Arrays.asList(
				new Column().header("pNumber").with(port -> Integer.toString(port.getPNumber())),
				new Column().header("Name").with(Port::getName),
				new Column().header("Latitude").with(port -> Double.toString(port.getLatitude())),
				new Column().header("Longitude").with(port -> Double.toString(port.getLongitude())),
				new Column().header("Capacity").with(port -> Double.toString(port.getStoringCapacity())),
				new Column().header("Landing Ability").with(port -> Boolean.toString(port.isLandingAbility())),
				new Column().header("Vehicles").with(port -> port.getVehicles() != null ? Integer.toString(port.getVehicles().size()) : "N/A"),
				new Column().header("Containers").with(port -> port.getContainers() != null ? Integer.toString(port.getVehicles().size()) : "N/A"),
				new Column().header("Trips").with(port -> port.getTraffic() != null ? Integer.toString(port.getVehicles().size()) : "N/A"))));
	}

	public static void displayVehicles(Collection<Vehicle> vehicles) {
		// TODO Finish displayVehicles
	}

	public static void displayContainers(Collection<Container> containers) {
		// TODO Finish displayContainers
	}

	public static void displayTrips(Collection<Trip> trips) {
		// TODO Finish displayTrips
	}

	public  static void displayUsers(Collection<User> users) {
		// TODO Finish displayUsers
	}
}
