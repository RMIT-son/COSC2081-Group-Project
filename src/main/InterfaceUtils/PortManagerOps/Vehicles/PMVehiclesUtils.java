package main.InterfaceUtils.PortManagerOps.Vehicles;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.Edit;
import main.InterfaceUtils.NotFoundException;
import main.InterfaceUtils.displayUtils;
import main.container.Container;
import main.porttrip.Port;
import main.vehicle.Truck;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static main.InterfaceUtils.PortManagerOps.PMInterface.portManaging;
import static main.porttrip.Port.readPort;
import static main.vehicle.Vehicle.readVehicle;
import static org.fusesource.jansi.Ansi.ansi;

public class PMVehiclesUtils {
	public static void  view() throws IOException {
		boolean viewMenuSwitch = true;
		List<Port> viewPortsList = readPort();
		for (Port port : viewPortsList) {
			if (port.getPNumber() == portManaging.getPNumber()) {
				portManaging = port;
				break;
			}
		}
		ArrayList<Vehicle> vehiclesInPort = (ArrayList<Vehicle>) portManaging.getVehicles();
		System.out.println(vehiclesInPort);
		ArrayList<Vehicle> vehiclesInFile = (ArrayList<Vehicle>) readVehicle();
		System.out.println(vehiclesInFile);
		ArrayList<Vehicle> vehicles = new ArrayList<>();

		for (Vehicle vehicle : vehiclesInFile) {
			for (Vehicle vehicleInPort : vehiclesInPort) {
				if (vehicleInPort.getName().equalsIgnoreCase(vehicle.getName())) {
					vehicles.add(vehicle);
				}
			}
		}
		System.out.println(vehicles);
		while (viewMenuSwitch) {
			try {
				// View Vehicle Menu Setup
				displayUtils.displayVehicles(vehicles);
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
	public static void edit() {
		try {
			ArrayList<Vehicle> vehiclesInPort = (ArrayList<Vehicle>) portManaging.getVehicles();
			ArrayList<Vehicle> vehiclesInFile = (ArrayList<Vehicle>) readVehicle();
			ArrayList<Vehicle> vehicles = new ArrayList<>();

			for (Vehicle vehicle : vehiclesInFile) {
				for (Vehicle vehicleInPort : vehiclesInPort) {
					if (vehicleInPort.getName().equalsIgnoreCase(vehicle.getName())) {
						vehicles.add(vehicle);
					}
				}
			}
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Vehicle CRUD Manager Menu"));
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Current Vehicles in Port:"));
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Step 1 of 3"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like Edit: ")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;

			for (Vehicle vehicle : vehicles) {
				if (vehicle instanceof Truck && vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
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
			System.out.println("Invalid input. Please enter the correct format.");
		} catch (NullPointerException e) {
			System.out.println("Invalid input. Please enter a non-null value.");
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void loadMenu() {
		List<Port> viewPortsList = readPort();
		for (Port port : viewPortsList) {
			if (port.getPNumber() == portManaging.getPNumber()) {
				portManaging = port;
				break;
			}
		}
		ArrayList<Vehicle> vehiclesInPort = (ArrayList<Vehicle>) portManaging.getVehicles();
		ArrayList<Vehicle> vehiclesInFile = (ArrayList<Vehicle>) readVehicle();
		ArrayList<Vehicle> vehicles = new ArrayList<>();

		ArrayList<Container> containersInPort = (ArrayList<Container>) portManaging.getContainers();
		ArrayList<Container> containersInFile = (ArrayList<Container>) Container.readContainer();
		ArrayList<Container> containers = new ArrayList<>();

		for (Vehicle vehicle : vehiclesInFile) {
			for (Vehicle vehicleInPort : vehiclesInPort) {
				if (vehicleInPort.getName().equalsIgnoreCase(vehicle.getName())) {
					vehicles.add(vehicle);
				}
			}
		}

		for (Container container : containersInFile) {
			for (Container containerInPort : containersInPort) {
				if (containerInPort.getCNumber() == container.getCNumber()) {
					containers.add(container);
				}
			}
		}
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Load Container to Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Vehicles:"));
			displayUtils.displayVehicles(vehicles);
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
			for (Container container : containers) {
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
			portManaging.updatePort();

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
		List<Port> viewPortsList = readPort();
		for (Port port : viewPortsList) {
			if (port.getPNumber() == portManaging.getPNumber()) {
				portManaging = port;
				break;
			}
		}
		ArrayList<Vehicle> vehiclesInPort = (ArrayList<Vehicle>) portManaging.getVehicles();
		ArrayList<Vehicle> vehiclesInFile = (ArrayList<Vehicle>) readVehicle();
		ArrayList<Vehicle> vehicles = new ArrayList<>();

		ArrayList<Container> containersInPort = (ArrayList<Container>) portManaging.getContainers();
		ArrayList<Container> containersInFile = (ArrayList<Container>) Container.readContainer();
		ArrayList<Container> containers = new ArrayList<>();

		for (Vehicle vehicle : vehiclesInFile) {
			for (Vehicle vehicleInPort : vehiclesInPort) {
				if (vehicleInPort.getName().equalsIgnoreCase(vehicle.getName())) {
					vehicles.add(vehicle);
				}
			}
		}

		for (Container container : containersInFile) {
			for (Container containerInPort : containersInPort) {
				if (containerInPort.getCNumber() == container.getCNumber()) {
					containers.add(container);
				}
			}
		}
		try {
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
			displayUtils.displayContainers(selectedVehicle.getContainers());
			promptBuilder.createInputPrompt()
					.name("ContainerSelect")
					.message("Enter the Container Id you would like to unload (int): ")
					.addPrompt();

			// Initialize Variables
			result = prompt.prompt(promptBuilder.build());
			InputResult containerInput = (InputResult) result.get("ContainerSelect");
			int selectedContainerId = Integer.parseInt(containerInput.getInput().trim());
			Container selectedContainer = null;

			// Find Container
			for (Container container : containers) {
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
		ArrayList<Vehicle> vehiclesInPort = (ArrayList<Vehicle>) portManaging.getVehicles();
		ArrayList<Vehicle> vehiclesInFile = (ArrayList<Vehicle>) readVehicle();
		ArrayList<Vehicle> vehicles = new ArrayList<>();

		for (Vehicle vehicle : vehiclesInFile) {
			for (Vehicle vehicleInPort : vehiclesInPort) {
				if (vehicleInPort.getName().equalsIgnoreCase(vehicle.getName())) {
					vehicles.add(vehicle);
				}
			}
		}
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
			for (Vehicle vehicle : vehicles) {
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
			portManaging.updatePort();
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

