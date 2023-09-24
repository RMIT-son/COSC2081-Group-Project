package main;

import main.Users.PortManager;
import main.Users.SystemAdmin;
import main.Users.User;
import main.container.*;
import main.porttrip.Port;
import main.porttrip.Trip;
import main.vehicle.*;

import java.time.LocalDate;

public class Seed {
	public static void main(String[] args) {
		// TODO
		User admin1 = new SystemAdmin("admin1", "admin123");
		admin1.createUser();
		User admin2 = new SystemAdmin("admin2", "admin123");
		admin2.createUser();
		User admin3 = new SystemAdmin("admin3", "admin123");
		admin3.createUser();

		Port port1 = new Port(1, "London Harbor", true, 51.5074, 0.1278, 5000.0);

		Port port2 = new Port(2, "Paris Dockyard", true, 48.8566, 2.3522, 6500.0);

		Port port3 = new Port(3, "New York Terminal", true, 40.7128, -74.0060, 8000.0);

		Port port4 = new Port(4, "Tokyo Bay", false, 35.6895, 139.6917, 4000.0);

		Port port5 = new Port(5, "Sydney Harbor", false, -33.8688, 151.2093, 7000.0);


		Vehicle ship1 = new Ship(1, "Titanic", 250, 1000, 21000, port1);
		port1.addVehicle(ship1);
		Vehicle ship2 = new Ship(2, "Ship2", 500, 1100, 20500, port2);
		port2.addVehicle(ship2);
		Vehicle ship3 = new Ship(3, "Ship3", 750, 1050, 20050, port3);
		port3.addVehicle(ship3);
		Vehicle ship4 = new Ship(4, "Ship4", 350, 1020, 20250, port4);
		port4.addVehicle(ship4);
		Vehicle ship5 = new Ship(5, "Ship5", 150, 1010, 20300, port5);
		port5.addVehicle(ship5);

		// Sample data for ReeferTrucks
		Vehicle reefer1 = new ReeferTruck(6, "Reefer1", 60, 105, 105, port1);
		port1.addVehicle(reefer1);
		Vehicle reefer2 = new ReeferTruck(7, "Reefer2", 40, 110, 110, port2);
		port2.addVehicle(reefer2);
		Vehicle reefer3 = new ReeferTruck(8, "Reefer3", 20, 115, 115, port3);
		port3.addVehicle(reefer3);
		Vehicle reefer4 = new ReeferTruck(9, "Reefer4", 50, 120, 120, port4);
		port4.addVehicle(reefer4);
		Vehicle reefer5 = new ReeferTruck(10, "Reefer5", 30, 125, 125, port5);
		port5.addVehicle(reefer5);

		// Sample data for BasicTrucks
		Vehicle basic1 = new BasicTruck(11, "Basic1", 70, 220, 210, port1);
		port1.addVehicle(basic1);
		Vehicle basic2 = new BasicTruck(12, "Basic2", 55, 225, 215, port2);
		port2.addVehicle(basic2);
		Vehicle basic3 = new BasicTruck(13, "Basic3", 45, 230, 220, port3);
		port3.addVehicle(basic3);
		Vehicle basic4 = new BasicTruck(14, "Basic4", 65, 235, 225, port4);
		port4.addVehicle(basic4);
		Vehicle basic5 = new BasicTruck(15, "Basic5", 35, 240, 230, port5);
		port5.addVehicle(basic5);

		// Sample data for TankerTrucks
		Vehicle tanker1 = new TankerTruck(16, "Tanker1", 90, 300, 510, port1);
		port1.addVehicle(tanker1);
		Vehicle tanker2 = new TankerTruck(17, "Tanker2", 85, 305, 515, port2);
		port2.addVehicle(tanker2);
		Vehicle tanker3 = new TankerTruck(18, "Tanker3", 75, 310, 520, port3);
		port3.addVehicle(tanker3);
		Vehicle tanker4 = new TankerTruck(19, "Tanker4", 95, 315, 525, port4);
		port4.addVehicle(tanker4);
		Vehicle tanker5 = new TankerTruck(20, "Tanker5", 80, 320, 530, port5);
		port5.addVehicle(tanker5);

		Container openTop1 = new OpenTop(1, 100.0, 10, ship1, null);
		Container openTop2 = new OpenTop(2, 105.5, 12, basic1, null);
		Container openTop3 = new OpenTop(3, 110.2, 14, basic2, null);
		Container openTop4 = new OpenTop(4, 115.7, 16, null, port2);
		Container openTop5 = new OpenTop(5, 120.3, 18, basic3, null);
		Container openTop6 = new OpenTop(6, 125.4, 20, null, port3);

		Container dryStorage1 = new DryStorage(7, 130.8, 25, ship2, null);
		Container dryStorage2 = new DryStorage(8, 135.2, 22, basic1, null);
		Container dryStorage3 = new DryStorage(9, 140.7, 20, basic2, null);
		Container dryStorage4 = new DryStorage(10, 145.3, 18, null, port4);
		Container dryStorage5 = new DryStorage(11, 150.1, 15, basic4, null);
		Container dryStorage6 = new DryStorage(12, 155.9, 12, null, port5);

		Container liquid1 = new Liquid(13, 15.5, 30, tanker1, null);
		Container liquid2 = new Liquid(14, 16.7, 32, tanker2, null);
		Container liquid3 = new Liquid(15, 17.9, 34, tanker3, null);
		Container liquid4 = new Liquid(16, 19.1, 36, tanker4, null);
		Container liquid5 = new Liquid(17, 20.3, 38, tanker5, null);

		Container reefercon1 = new Refrigerated(18, 18.8, 8, reefer1, null);
		Container reefercon2 = new Refrigerated(19, 19.6, 10, reefer2, null);
		Container reefercon3 = new Refrigerated(20, 20.4, 6, reefer3, null);
		Container reefercon4 = new Refrigerated(21, 21.2, 5, reefer4, null);
		Container reefercon5 = new Refrigerated(22, 22.0, 7, reefer5, null);

		Container openSide1 = new OpenSide(23, 22.5, 9, basic3, null);
		Container openSide2 = new OpenSide(24, 23.7, 11, basic4, null);
		Container openSide3 = new OpenSide(25, 24.9, 13, basic5, null);
		Container openSide4 = new OpenSide(26, 26.1, 15, null, port4);
		Container openSide5 = new OpenSide(27, 27.3, 17, null, port5);

		Container dryStorage7 = new DryStorage(28, 160.5, 19, null, port1);
		Container liquid6 = new Liquid(29, 28.5, 21, tanker1, null);
		Container openTop7 = new OpenTop(30, 130.7, 23, null, port2);


		// Sample data for Trips using Ships
		Trip trip1 = new Trip(ship1, LocalDate.parse("2023-09-25"), LocalDate.parse("2023-09-28"), port1, port2);
		trip1.createTrip();

		Trip trip2 = new Trip(ship2, LocalDate.parse("2023-09-26"), LocalDate.parse("2023-09-30"), port2, port3);
		trip2.createTrip();

		Trip trip3 = new Trip(ship3, LocalDate.parse("2023-09-27"), LocalDate.parse("2023-09-30"), port3, port4);
		trip3.createTrip();

		Trip trip4 = new Trip(ship4, LocalDate.parse("2023-10-01"), LocalDate.parse("2023-10-04"), port4, port5);
		trip4.createTrip();

		Trip trip5 = new Trip(ship5, LocalDate.parse("2023-10-02"), LocalDate.parse("2023-10-06"), port5, port1);
		trip5.createTrip();

		Trip trip6 = new Trip(reefer1, LocalDate.parse("2023-10-03"), LocalDate.parse("2023-10-04"), port1, port2);
		trip6.createTrip();

		Trip trip7 = new Trip(reefer2, LocalDate.parse("2023-10-05"), LocalDate.parse("2023-10-06"), port2, port3);
		trip7.createTrip();

		Trip trip8 = new Trip(reefer3, LocalDate.parse("2023-10-07"), LocalDate.parse("2023-10-08"), port3, port4);
		trip8.createTrip();

		Trip trip9 = new Trip(reefer4, LocalDate.parse("2023-10-09"), LocalDate.parse("2023-10-10"), port4, port5);
		trip9.createTrip();

		Trip trip10 = new Trip(reefer5, LocalDate.parse("2023-10-11"), LocalDate.parse("2023-10-12"), port5, port1);
		trip10.createTrip();

		Trip trip11 = new Trip(basic1, LocalDate.parse("2023-10-13"), LocalDate.parse("2023-10-14"), port1, port2);
		trip11.createTrip();

		Trip trip12 = new Trip(basic2, LocalDate.parse("2023-10-15"), LocalDate.parse("2023-10-16"), port2, port3);
		trip12.createTrip();

		Trip trip13 = new Trip(basic3, LocalDate.parse("2023-10-17"), LocalDate.parse("2023-10-18"), port3, port4);
		trip13.createTrip();

		Trip trip14 = new Trip(basic4, LocalDate.parse("2023-10-19"), LocalDate.parse("2023-10-20"), port4, port5);
		trip14.createTrip();

		Trip trip15 = new Trip(basic5, LocalDate.parse("2023-10-21"), LocalDate.parse("2023-10-22"), port5, port1);
		trip15.createTrip();

		Trip trip16 = new Trip(tanker1, LocalDate.parse("2023-10-23"), LocalDate.parse("2023-10-24"), port1, port2);
		trip16.createTrip();

		Trip trip17 = new Trip(tanker2, LocalDate.parse("2023-10-09"), LocalDate.parse("2023-10-11"), port2, port3);
		trip17.createTrip();
		

		Trip trip18 = new Trip(tanker3, LocalDate.parse("2023-10-27"), LocalDate.parse("2023-10-28"), port3, port4);
		trip18.createTrip();

		Trip trip19 = new Trip(tanker4, LocalDate.parse("2023-10-29"), LocalDate.parse("2023-10-30"), port4, port5);
		trip19.createTrip();

		Trip trip20 = new Trip(tanker5, LocalDate.parse("2023-10-31"), LocalDate.parse("2023-11-01"), port5, port1);
		trip20.createTrip();

		Trip trip21 = new Trip(ship1, LocalDate.parse("2023-11-02"), LocalDate.parse("2023-11-05"), port1, port3);
		trip21.createTrip();

		Trip trip22 = new Trip(reefer1, LocalDate.parse("2023-11-06"), LocalDate.parse("2023-11-07"), port1, port4);
		trip22.createTrip();

		Trip trip23 = new Trip(basic1, LocalDate.parse("2023-11-08"), LocalDate.parse("2023-11-09"), port1, port5);
		trip23.createTrip();

		Trip trip24 = new Trip(tanker1, LocalDate.parse("2023-11-10"), LocalDate.parse("2023-11-11"), port1, port2);
		trip24.createTrip();

		Trip trip25 = new Trip(ship2, LocalDate.parse("2023-11-12"), LocalDate.parse("2023-11-15"), port2, port4);
		trip25.createTrip();



		port1.createPort();
		port2.createPort();
		port3.createPort();
		port4.createPort();
		port5.createPort();

		ship1.createVehicle();
		ship2.createVehicle();
		ship3.createVehicle();
		ship4.createVehicle();
		ship5.createVehicle();

		reefer1.createVehicle();
		reefer2.createVehicle();
		reefer3.createVehicle();
		reefer4.createVehicle();
		reefer5.createVehicle();

		basic1.createVehicle();
		basic2.createVehicle();
		basic3.createVehicle();
		basic4.createVehicle();
		basic5.createVehicle();

		tanker1.createVehicle();
		tanker2.createVehicle();
		tanker3.createVehicle();
		tanker4.createVehicle();
		tanker5.createVehicle();

		openTop1.createContainer();
		openTop2.createContainer();
		openTop3.createContainer();
		openTop4.createContainer();
		openTop5.createContainer();
		openTop6.createContainer();
		openTop7.createContainer();

		dryStorage1.createContainer();
		dryStorage2.createContainer();
		dryStorage3.createContainer();
		dryStorage4.createContainer();
		dryStorage5.createContainer();
		dryStorage6.createContainer();
		dryStorage7.createContainer();

		liquid1.createContainer();
		liquid2.createContainer();
		liquid3.createContainer();
		liquid4.createContainer();
		liquid5.createContainer();
		liquid6.createContainer();

		reefercon1.createContainer();
		reefercon2.createContainer();
		reefercon3.createContainer();
		reefercon4.createContainer();
		reefercon5.createContainer();

		openSide1.createContainer();
		openSide2.createContainer();
		openSide3.createContainer();
		openSide4.createContainer();
		openSide5.createContainer();

		// Loading containers onto their vehicles or ports
		ship1.loadContainer(openTop1);
		basic1.loadContainer(openTop2);
		basic2.loadContainer(openTop3);
		port2.loadContainerToPort(openTop4);
		basic3.loadContainer(openTop5);
		port3.loadContainerToPort(openTop6);

		ship2.loadContainer(dryStorage1);
		basic1.loadContainer(dryStorage2);
		basic2.loadContainer(dryStorage3);
		port4.loadContainerToPort(dryStorage4);
		basic4.loadContainer(dryStorage5);
		port5.loadContainerToPort(dryStorage6);

		tanker1.loadContainer(liquid1);
		tanker2.loadContainer(liquid2);
		tanker3.loadContainer(liquid3);
		tanker4.loadContainer(liquid4);
		tanker5.loadContainer(liquid5);

		reefer1.loadContainer(reefercon1);
		reefer2.loadContainer(reefercon2);
		reefer3.loadContainer(reefercon3);
		reefer4.loadContainer(reefercon4);
		reefer5.loadContainer(reefercon5);

		basic3.loadContainer(openSide1);
		basic4.loadContainer(openSide2);
		basic5.loadContainer(openSide3);
		port4.loadContainerToPort(openSide4);
		port5.loadContainerToPort(openSide5);

		port1.loadContainerToPort(dryStorage7);
		tanker1.loadContainer(liquid6);
		port2.loadContainerToPort(openTop7);

		User pm1 = new PortManager("pm1", "pm123", port1);
		User pm2 = new PortManager("pm2", "pm123", port2);
		User pm3 = new PortManager("pm3", "pm123", port3);
		User pm4 = new PortManager("pm4", "pm123", port4);
		User pm5 = new PortManager("pm5", "pm123", port5);
		pm1.createUser();
		pm2.createUser();
		pm3.createUser();
		pm4.createUser();
		pm5.createUser();
	}
}
