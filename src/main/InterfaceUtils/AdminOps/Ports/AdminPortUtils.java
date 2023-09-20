package main.InterfaceUtils.AdminOps.Ports;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.PortManagerOps.Vehicles.PMVehiclesUtils;
import main.porttrip.Port;
import main.vehicle.Truck;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class AdminPortUtils {
	public static void create() throws IOException {
		// TODO Finish saving port to database
		try {
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Create Port Menu"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Id")
					.message("Enter new Port Id (int): ")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			promptBuilder.createInputPrompt()
					.name("Name")
					.message("Enter new Port Name: ")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult nameInput = (InputResult) result.get("Name");
			String portName = nameInput.getInput().trim();
			promptBuilder.createInputPrompt()
					.name("Store")
					.message("Enter Storing Capacity (double): ")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult storeInput = (InputResult) result.get("Store");
			double store = Double.parseDouble(storeInput.getInput().trim());
			promptBuilder.createInputPrompt()
					.name("Latitude")
					.message("Enter Latitude (double): ")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult latInput = (InputResult) result.get("Latitude");
		    double lat = Double.parseDouble(latInput.getInput().trim());
			promptBuilder.createInputPrompt()
					.name("Longitude")
					.message("Enter Longitude (double): ")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult longInput = (InputResult) result.get("Longitude");
			double lon = Double.parseDouble(longInput.getInput().trim());
			promptBuilder.createConfirmPromp()
					.name("Landing")
					.message("Does this Port support Landing?")
					.defaultValue(ConfirmChoice.ConfirmationValue.YES)
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			ConfirmResult confirmResult = (ConfirmResult) result.get("Landing");
			boolean landing = confirmResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES;
			Port newPort = new Port(id, portName, landing, lat, lon, store, null, null, null);
			// TODO: newPort.createPort();
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid input.");
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void edit() {
		// TODO implement edit port interface
		ArrayList<Port> Port = new ArrayList<>();
		try {
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Vehicle CRUD Manager Menu"));
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Current Vehicles in Port:"));
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Step 1 of 3"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("PortsSelect")
					.message("Enter the Vehicle Name you would like Edit: ")
					.defaultValue("Honda")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;

//			for (Vehicle vehicle : vehiclesInPort) {
//				if (vehicle instanceof Truck && vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
//					selectedVehicle = vehicle;
//					PMVehiclesUtils.edit(selectedVehicle);
//					break;
//				}
//			}
			if (selectedVehicle == null) {
				System.out.println(ansi().fg(Ansi.Color.RED).render("Vehicle not found"));
			}
			// May have to add more code here for menu logic
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid integer ID.");
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void delete() {
		// TODO implement delete port interface
	}
}
