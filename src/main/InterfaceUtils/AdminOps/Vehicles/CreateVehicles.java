package main.InterfaceUtils.AdminOps.Vehicles;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.vehicle.BasicTruck;
import main.vehicle.ReeferTruck;
import main.vehicle.TankerTruck;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class CreateVehicles {
	public static void basicMenu() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Basic Truck"));
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Id")
					.message("Enter new Truck Number (int): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Name")
					.message("Enter new Truck Name: ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel")
					.message("Enter Current Fuel (double): ")
					.defaultValue("0.0")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel Capacity")
					.message("Enter Fuel Capacity (double): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Carry Capacity")
					.message("Enter Carry Capacity (double): ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult nameInput = (InputResult) result.get("Name");
			String vehicleName = nameInput.getInput().trim();
			InputResult fuelInput = (InputResult) result.get("Fuel");
			double fuel = Double.parseDouble(fuelInput.getInput().trim());
			InputResult FCInput = (InputResult) result.get("Fuel Capacity");
			double fuelCapacity = Double.parseDouble(FCInput.getInput().trim());
			InputResult CCInput = (InputResult) result.get("Carry Capacity");
			double carryCapacity = Double.parseDouble(CCInput.getInput().trim());

			// Create Vehicle
			BasicTruck newVehicle = new BasicTruck(id ,vehicleName, fuel, fuelCapacity, carryCapacity, null);
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Created new vehicle: " + newVehicle.getName()));
			newVehicle.createVehicle();

		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter the correctly specified value type."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null value"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static void reeferMenu() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Reefer Truck"));
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Id")
					.message("Enter new Truck Number (int): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Name")
					.message("Enter new Truck Name: ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel")
					.message("Enter Current Fuel (double): ")
					.defaultValue("0.0")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel Capacity")
					.message("Enter Fuel Capacity (double): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Carry Capacity")
					.message("Enter Carry Capacity (double): ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult nameInput = (InputResult) result.get("Name");
			String vehicleName = nameInput.getInput().trim();
			InputResult fuelInput = (InputResult) result.get("Fuel");
			double fuel = Double.parseDouble(fuelInput.getInput().trim());
			InputResult FCInput = (InputResult) result.get("Fuel Capacity");
			double fuelCapacity = Double.parseDouble(FCInput.getInput().trim());
			InputResult CCInput = (InputResult) result.get("Carry Capacity");
			double carryCapacity = Double.parseDouble(CCInput.getInput().trim());

			// Create Vehicle
			ReeferTruck newVehicle = new ReeferTruck(id ,vehicleName, fuel, fuelCapacity, carryCapacity, null);
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Created new vehicle: " + newVehicle.getName()));
			newVehicle.createVehicle();

		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter the correctly specified value type."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null value"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void tankerMenu() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Tanker Truck"));
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Id")
					.message("Enter new Truck Number (int): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Name")
					.message("Enter new Truck Name: ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel")
					.message("Enter Current Fuel (double): ")
					.defaultValue("0.0")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel Capacity")
					.message("Enter Fuel Capacity (double): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Carry Capacity")
					.message("Enter Carry Capacity (double): ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult nameInput = (InputResult) result.get("Name");
			String vehicleName = nameInput.getInput().trim();
			InputResult fuelInput = (InputResult) result.get("Fuel");
			double fuel = Double.parseDouble(fuelInput.getInput().trim());
			InputResult FCInput = (InputResult) result.get("Fuel Capacity");
			double fuelCapacity = Double.parseDouble(FCInput.getInput().trim());
			InputResult CCInput = (InputResult) result.get("Carry Capacity");
			double carryCapacity = Double.parseDouble(CCInput.getInput().trim());

			// Create Vehicle
			TankerTruck newVehicle = new TankerTruck(id ,vehicleName, fuel, fuelCapacity, carryCapacity, null);
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Created new vehicle: " + newVehicle.getName()));
			newVehicle.createVehicle();

		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter the correctly specified value type."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null value"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void shipMenu() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Ship"));
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Id")
					.message("Enter new Ship Number (int): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Name")
					.message("Enter new Truck Name: ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel")
					.message("Enter Current Fuel (double): ")
					.defaultValue("0.0")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel Capacity")
					.message("Enter Fuel Capacity (double): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Carry Capacity")
					.message("Enter Carry Capacity (double): ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult nameInput = (InputResult) result.get("Name");
			String vehicleName = nameInput.getInput().trim();
			InputResult fuelInput = (InputResult) result.get("Fuel");
			double fuel = Double.parseDouble(fuelInput.getInput().trim());
			InputResult FCInput = (InputResult) result.get("Fuel Capacity");
			double fuelCapacity = Double.parseDouble(FCInput.getInput().trim());
			InputResult CCInput = (InputResult) result.get("Carry Capacity");
			double carryCapacity = Double.parseDouble(CCInput.getInput().trim());

			// Create Vehicle
			TankerTruck newVehicle = new TankerTruck(id ,vehicleName, fuel, fuelCapacity, carryCapacity, null);
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Created new vehicle: " + newVehicle.getName()));
			newVehicle.createVehicle();

		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter the correctly specified value type."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null value"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
