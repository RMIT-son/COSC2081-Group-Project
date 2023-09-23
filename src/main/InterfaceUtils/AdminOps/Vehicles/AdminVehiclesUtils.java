package main.InterfaceUtils.AdminOps.Vehicles;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.*;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.Edit;
import main.InterfaceUtils.NotFoundException;
import main.InterfaceUtils.displayUtils;
import main.container.Container;
import main.porttrip.Port;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static main.InterfaceUtils.AdminOps.Vehicles.CreateVehicles.*;
import static main.porttrip.Port.readPort;
import static main.vehicle.Vehicle.readVehicle;
import static org.fusesource.jansi.Ansi.ansi;

public class AdminVehiclesUtils {
	public static void  view() throws IOException {
		boolean viewMenuSwitch = true;
		ArrayList<Vehicle> viewVehiclesList = (ArrayList<Vehicle>) readVehicle();
		while (viewMenuSwitch) {
			try {
				// View Vehicle Menu Setup
				displayUtils.displayVehicles(viewVehiclesList);
				ConsolePrompt prompt = new ConsolePrompt();
				PromptBuilder promptBuilder = prompt.getPromptBuilder();
				promptBuilder.createConfirmPromp()
						.name("Continue")
						.message("Continue?")
						.defaultValue(ConfirmChoice.ConfirmationValue.NO)
						.addPrompt();
				HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
				ConfirmResult continueResult = (ConfirmResult) result.get("Continue");
				boolean cont = continueResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES;
				if (!cont) {
					viewMenuSwitch = false;
				}
			} finally {
				try {
					TerminalFactory.get().restore();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void create() throws IOException {
		try {
			// Create Vehicle Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createListPrompt()
					.name("Type")
					.message("Select Vehicle Type: ")
					.newItem("Basic").text("Basic Truck").add()
					.newItem("Reefer").text("Reefer Truck").add()
					.newItem("Tanker").text("Tanker Truck").add()
					.newItem("Ship").text("Ship").add()
					.newItem("Back").text("Back").add()
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			ListResult typeResult = (ListResult) result.get("Type");

			// Create Vehicle Menu Switch
			switch (typeResult.getSelectedId()) {
				case "Basic":
					basicMenu();
					break;
				case "Reefer":
					reeferMenu();
					break;
				case "Tanker":
					tankerMenu();
					break;
				case "Ship":
					shipMenu();
					break;
				case "Back":
					break;
			}
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void edit() {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) readVehicle();
		try {
			// Edit Vehicle Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Edit Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			displayUtils.displayVehicles(vehicles);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like Edit: ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;

			// Find Vehicle
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
					selectedVehicle = vehicle;
					Edit.editVehicle(selectedVehicle);
					break;
				}
			}
			if (selectedVehicle == null) {
				System.out.println(ansi().fg(Ansi.Color.RED).render("Vehicle not found"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null input."));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void delete() {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) readVehicle();
		try {
			// Delete Port Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Delete Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Vehicles: "));
			displayUtils.displayVehicles(vehicles);
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like Edit: ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;

			// Find Port
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
					selectedVehicle = vehicle;
					break;
				}
			}
			if (selectedVehicle == null) {
				throw new NotFoundException();
			}

			// Delete Port Confirmation
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createConfirmPromp()
					.name("Delete")
					.message("Are you sure you want to delete this Vehicle?")
					.defaultValue(ConfirmChoice.ConfirmationValue.YES)
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			ConfirmResult confirmResult = (ConfirmResult) result.get("Delete");

			// Delete Port
			if (confirmResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES) {
				selectedVehicle.deleteVehicle();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Vehicle not found."));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void move() throws IOException {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) readVehicle();
		ArrayList<Port> ports = (ArrayList<Port>) readPort();
		try {
			// Delete Vehicle Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Move Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Vehicles:"));
			displayUtils.displayVehicles(vehicles);
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like to load: ")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;

			// Find Vehicle
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
					selectedVehicle = vehicle;
					break;
				}
			}
			if (selectedVehicle == null) {
				throw new NotFoundException();
			}

			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("PortsSelect")
					.message("Enter the Port Name you would like to unload: ")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult portsInput = (InputResult) result.get("PortsSelect");
			String selectedPortName = portsInput.getInput().trim();
			Port selectedPort = null;

			// Find Port
			for (Port port : ports) {
				if (port.getName().equalsIgnoreCase(selectedPortName)) {
					selectedPort = port;
					break;
				}
			}
			if (selectedPort == null) {
				throw new NotFoundException();
			}
			selectedVehicle.movePort(selectedPort);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Selected Input not found"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void loadMenu() {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) readVehicle();
		try {
			// Delete Vehicle Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Load Container to Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Vehicles:"));
			displayUtils.displayVehicles(vehicles);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like to unload: ")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;
			// Find Vehicle
			for (Vehicle vehicle : readVehicle()) {
				if (vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
					selectedVehicle = vehicle;
					break;
				}
			}
			if (selectedVehicle == null) {
				throw new NotFoundException();
			}

			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			displayUtils.displayContainers(selectedVehicle.getContainers());
			promptBuilder.createInputPrompt()
					.name("ContainerSelect")
					.message("Enter the Container Id you would like to load (int): ")
					.addPrompt();

			// Initialize Variables
			result = prompt.prompt(promptBuilder.build());
			InputResult containerInput = (InputResult) result.get("ContainerSelect");
			int selectedContainerId = Integer.parseInt(containerInput.getInput().trim());
			Container selectedContainer = null;

			// Find Container
			for (Container container : selectedVehicle.getContainers()) {
				if (container.getCNumber() == selectedContainerId) {
					selectedContainer = container;
					break;
				}
			}
			if (selectedContainer == null) {
				throw new NotFoundException();
			}

			Port currentVehiclePort = null;

			for (Port port : readPort()) {
				if (port.getName().equalsIgnoreCase(selectedVehicle.getCurrentPort().getName())) {
					currentVehiclePort = port;
					break;
				}
			}
			if (currentVehiclePort == null) {
				throw new NotFoundException();
			}

			// Load Container
			currentVehiclePort.unloadContainerFromPort(selectedContainer);
			selectedVehicle.loadContainer(selectedContainer);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Selected Input not found"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void unloadMenu() {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) readVehicle();
		try {
			// Delete Vehicle Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Unload Container from Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Vehicles:"));
			displayUtils.displayVehicles(vehicles);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like to unload: ")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;
			// Find Vehicle
			for (Vehicle vehicle : readVehicle()) {
				if (vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
					selectedVehicle = vehicle;
					break;
				}
			}
			if (selectedVehicle == null) {
				throw new NotFoundException();
			}

			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			displayUtils.displayContainers(selectedVehicle.getContainers());
			promptBuilder.createInputPrompt()
					.name("ContainerSelect")
					.message("Enter the Container Id you would like to load (int): ")
					.addPrompt();

			// Initialize Variables
			result = prompt.prompt(promptBuilder.build());
			InputResult containerInput = (InputResult) result.get("ContainerSelect");
			int selectedContainerId = Integer.parseInt(containerInput.getInput().trim());
			Container selectedContainer = null;

			// Find Container
			for (Container container : selectedVehicle.getContainers()) {
				if (container.getCNumber() == selectedContainerId) {
					selectedContainer = container;
					break;
				}
			}
			if (selectedContainer == null) {
				throw new NotFoundException();
			}

			Port currentVehiclePort = null;

			for (Port port : readPort()) {
				if (port.getName().equalsIgnoreCase(selectedVehicle.getCurrentPort().getName())) {
					currentVehiclePort = port;
					break;
				}
			}
			if (currentVehiclePort == null) {
				throw new NotFoundException();
			}

			// Load Container
			selectedVehicle.unloadContainer(selectedContainer);
			currentVehiclePort.loadContainerToPort(selectedContainer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Selected Input not found"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void refuel() {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) readVehicle();
		try {
			// Delete Vehicle Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Refuel Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Vehicles:"));
			displayUtils.displayVehicles(vehicles);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like to refuel: ")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;

			// Find Vehicle
			for (Vehicle vehicle : readVehicle()) {
				if (vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
					selectedVehicle = vehicle;
					break;
				}
			}
			if (selectedVehicle == null) {
				throw new NotFoundException();
			}

			// Refuel Vehicle
			selectedVehicle.refuel();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Selected Input not found"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
