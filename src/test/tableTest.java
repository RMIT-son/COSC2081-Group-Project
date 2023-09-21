package test;

import main.Users.PortManager;
import main.Users.SystemAdmin;
import main.Users.User;
import main.container.Container;
import main.porttrip.Port;
import main.porttrip.Trip;
import main.vehicle.ReeferTruck;
import main.vehicle.Ship;
import main.vehicle.Vehicle;
import main.InterfaceUtils.displayUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class tableTest {
	public static void main(String[] args) {

		ArrayList<User> users = new ArrayList<>(Arrays.asList(
				new SystemAdmin("admin1", "admin"),
				new SystemAdmin("admin2", "admin"),
				new SystemAdmin("admin3", "admin"),
				new PortManager("manager1", "manager", new Port(1, "Melbourne", true, -37.840935, 144.946457, 1000000)),
				new PortManager("manager2", "manager", new Port(2, "Sydney", true, -33.865143, 151.209900, 1000000)),
				new PortManager("manager3", "manager", new Port(3, "Brisbane", true, -27.470125, 153.021072, 1000000))
		));

		ArrayList<Vehicle> vehicles1 = new ArrayList<>(Arrays.asList(
				new Ship(1, "Ship1", 10, 100, 100, null, null),
				new Ship(2, "Ship2", 10, 100, 100, null, null),
				new ReeferTruck(3, "ReeferTruck1", 10, 100, 100, null, null),
				new ReeferTruck(4, "ReeferTruck2", 10, 100, 100, null, null)
		));

		ArrayList<Trip> trips1 = new ArrayList<>(Arrays.asList(
				new Trip(null, null, null, null, null, null),
				new Trip(null, null, null, null, null, null),
				new Trip(null, null, null, null, null, null)
		));

		ArrayList<Container> containers1 = new ArrayList<>(Arrays.asList(

				new Container(1, 12, 29, null, null, null),
				new Container(2, 12, 29, null, null, null),
				new Container(3, 12, 29, null, null, null),
				new Container(4, 12, 29, null, null, null)
		));

		// ArrayList<Port> ports = new ArrayList<>(Arrays.asList(
		// 		new Port(1, "Melbourne", true, -37.840935, 144.946457, 1000000, null, containers1, vehicles1),
		// 		new Port(2, "Sydney", true, -33.865143, 151.209900, 1000000, trips1, null, vehicles1),
		// 		new Port(3, "Brisbane", true, -27.470125, 153.021072, 1000000, null, null, null),
		// 		new Port(4, "Perth", true, -31.952854, 115.857342, 1000000, trips1, containers1, vehicles1),
		// 		new Port(5, "Adelaide", true, -34.928499, 138.600746, 1000000, null, null, vehicles1)
		// ));

		// displayUtils.displayPorts(ports);
		// displayUtils.displayVehicles(vehicles1);
		// displayUtils.displayContainers(containers1);
		// displayUtils.displayTrips(trips1);
		// displayUtils.displayUsers(users);
	}
}
