package test;

import main.Users.PortManager;
import main.Users.SystemAdmin;
import main.Users.User;
import main.container.Container;
import main.container.Liquid;
import main.porttrip.Port;
import main.vehicle.ReeferTruck;
import main.vehicle.Ship;
import main.vehicle.Vehicle;
import org.fusesource.jansi.AnsiConsole;

import java.util.List;

import static main.vehicle.Vehicle.readVehicle;

public class uiTest {
	public static void main(String[] args) throws InterruptedException {
		List<Port> ports = Port.readPort();
//		List<Vehicle> vehicles = readVehicle();
////		 Get 1st port
//		Port port2 = new Port(2, "Port2", true, 100, 100, 100);
//		port2.createPort();
//		Vehicle newShip = new Ship(1, "Ship1", 10, 100, 100, null);
//		newShip.createVehicle();
//		Container newLiquid = new Liquid(1, 12, 29, null, null);
//    	newLiquid.createContainer();
//		Port port = ports.get(0);
//		port.loadContainerToPort(newLiquid);
//		port.addVehicle(newShip);
//		User newPM = new PortManager("manager", "manager", port);
//		newPM.createUser();
//		User newAdmin = new SystemAdmin("admin", "admin");
//		newAdmin.createUser();
////
//		List<User> users = User.readUser();
//		System.out.println("Users: " + users.size());
//		for (User user : users) {
//			System.out.println(user);
//		}
//
//		for (Vehicle vehicle : vehicles) {
//			System.out.println(vehicle.getContainers());
		for (Port port : ports) {
			System.out.println(port);
		}

	}
}

