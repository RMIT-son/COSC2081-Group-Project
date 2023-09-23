package main;

import main.Users.SystemAdmin;
import main.Users.User;
import main.container.Container;
import main.container.Liquid;
import main.porttrip.Port;
import main.vehicle.*;

public class Seed {
	public static void main(String[] args) {
		// TODO
		User admin1 = new SystemAdmin("admin1", "admin123");
		admin1.createUser();
		User admin2 = new SystemAdmin("admin2", "admin123");
		admin2.createUser();
		User admin3 = new SystemAdmin("admin3", "admin123");
		admin3.createUser();

		Port port1 = new Port(1, "Hamburg", true , 100, 100, 1000);

		Port port2 = new Port(2, "Rotterdam", true , 40, 60, 10000);

		Port port3 = new Port(3, "Antwerp", true , 50, 70, 20000);

		Port port4 = new Port(4, "Singapore", false , 100, 100, 1400);

		Port port5 = new Port(5, "Shanghai", false , 100, 100, 1500);


		Vehicle ship1 = new Ship(1, "Titanic", 0, 1000, 20000, port1);
		port1.addVehicle(ship1);
 		Vehicle reefer1 = new ReeferTruck(2, "Truckit", 0, 100, 100, port1);
		port1.addVehicle(reefer1);
		Vehicle basic1 = new BasicTruck(3, "Honda", 20, 220, 200, port1);
		port1.addVehicle(basic1);
		Vehicle tanker1 = new TankerTruck(4, "Tanker", 50, 300, 500, port1);
		port1.addVehicle(tanker1);

		Ship ship2 = new Ship(2, "Ship2", 0, 1000, 20000, port2);
		port2.addVehicle(ship2);
		Ship ship3 = new Ship(3, "Ship3", 0, 1000, 20000, port3);
		port3.addVehicle(ship3);
		Ship ship4 = new Ship(4, "Ship4", 0, 1000, 20000, port4);
		port4.addVehicle(ship4);
		Ship ship5 = new Ship(5, "Ship5", 0, 1000, 20000, port5);
		port5.addVehicle(ship5);

		ReeferTruck reefer2 = new ReeferTruck(6, "Reefer2", 0, 100, 100, port2);
		port2.addVehicle(reefer2);
		ReeferTruck reefer3 = new ReeferTruck(7, "Reefer3", 0, 100, 100, port3);
		port3.addVehicle(reefer3);
		ReeferTruck reefer4 = new ReeferTruck(8, "Reefer4", 0, 100, 100, port4);
		port4.addVehicle(reefer4);
		ReeferTruck reefer5 = new ReeferTruck(9, "Reefer5", 0, 100, 100, port5);
		port5.addVehicle(reefer5);

		BasicTruck basic2 = new BasicTruck(10, "Basic2", 20, 220, 200, port2);
		port2.addVehicle(basic2);
		BasicTruck basic3 = new BasicTruck(11, "Basic3", 20, 220, 200, port3);
		port3.addVehicle(basic3);
		BasicTruck basic4 = new BasicTruck(12, "Basic4", 20, 220, 200, port4);
		port4.addVehicle(basic4);
		BasicTruck basic5 = new BasicTruck(13, "Basic5", 20, 220, 200, port5);
		port5.addVehicle(basic5);

		TankerTruck tanker2 = new TankerTruck(14, "Tanker2", 50, 300, 500, port2);
		port2.addVehicle(tanker2);
		TankerTruck tanker3 = new TankerTruck(15, "Tanker3", 50, 300, 500, port3);
		port3.addVehicle(tanker3);
		TankerTruck tanker4 = new TankerTruck(16, "Tanker4", 50, 300, 500, port4);
		port4.addVehicle(tanker4);
		TankerTruck tanker5 = new TankerTruck(17, "Tanker5", 50, 300, 500, port5);
		port5.addVehicle(tanker5);

		Container liquid1 = new Liquid(1, 12, 29, null, port1);





		port1.createPort();
		port2.createPort();
		port3.createPort();
		port4.createPort();
		port5.createPort();
		ship1.createVehicle();
		reefer1.createVehicle();
		ship2.createVehicle();
		reefer2.createVehicle();
		basic2.createVehicle();
		tanker2.createVehicle();
		ship3.createVehicle();
		reefer3.createVehicle();
		basic3.createVehicle();
		tanker3.createVehicle();
		ship4.createVehicle();
		reefer4.createVehicle();
		basic4.createVehicle();
		tanker4.createVehicle();
		ship5.createVehicle();
		reefer5.createVehicle();
		basic5.createVehicle();
		tanker5.createVehicle();
	}
}
