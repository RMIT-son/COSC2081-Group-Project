package test;

import main.vehicle.ReeferTruck;
import main.vehicle.Ship;
import main.vehicle.Vehicle;

import java.util.List;

import static main.vehicle.Vehicle.readVehicle;

public class uiTest {
	public static void main(String[] args) throws InterruptedException {
		Vehicle vehicle1 = new Ship(1, "Ship1", 10, 100, 100, null);
		Vehicle vehicle2 = new Ship(2, "Ship2", 10, 100, 100, null);
		Vehicle vehicle3 = new ReeferTruck(3, "RT3", 10, 100, 100, null);
		Vehicle vehicle4 = new ReeferTruck(4, "RT4", 10, 100, 100, null);
		List<Vehicle> vehicles = readVehicle();
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle);
		}
		vehicle1.deleteVehicle();
		vehicle2.deleteVehicle();
		vehicle3.deleteVehicle();
		vehicle4.deleteVehicle();
		vehicles = readVehicle();
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle);
		}
	}
}
