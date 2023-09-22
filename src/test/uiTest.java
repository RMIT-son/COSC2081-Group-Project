package test;

import main.Users.PortManager;
import main.Users.SystemAdmin;
import main.Users.User;
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
		// Get 1st port
		Port port = ports.get(0);
		User newUser = new PortManager("manager", "manager", port);
		newUser.createUser();
		}
	}
