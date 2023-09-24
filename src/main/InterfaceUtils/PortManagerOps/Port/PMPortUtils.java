package main.InterfaceUtils.PortManagerOps.Port;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.NotFoundException;
import main.InterfaceUtils.displayUtils;
import main.container.Container;
import main.porttrip.Port;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static main.InterfaceUtils.PortManagerOps.PMInterface.portManaging;
import static main.container.Container.readContainer;
import static main.porttrip.Port.readPort;
import static main.vehicle.Vehicle.readVehicle;
import static org.fusesource.jansi.Ansi.ansi;

public class PMPortUtils {
	public static void view() throws IOException {
		boolean viewMenuSwitch = true;
		List<Port> viewPortsFileList = readPort();
		for (Port port : viewPortsFileList) {
			if (port.getPNumber() == portManaging.getPNumber()) {
				portManaging = port;
				break;
			}
		}
		ArrayList<Port> viewPortsList = new ArrayList<>(Collections.singleton(portManaging));
		while (viewMenuSwitch) {
			try {
				displayUtils.displayPorts(viewPortsList);
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

	public static void loadMenu() {
		// TODO fix logic of loading and unloading using DB refs
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

		ArrayList<Container> containersInFile = (ArrayList<Container>) Container.readContainer();
		ArrayList<Container> containers = new ArrayList<>();

		for (Vehicle vehicle : vehiclesInFile) {
			for (Vehicle vehicleInPort : vehiclesInPort) {
				if (vehicleInPort.getName().equalsIgnoreCase(vehicle.getName())) {
					vehicles.add(vehicle);
				}
			}
		}

		try {
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			displayUtils.displayVehicles(vehicles);
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like to unload:")
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

			for (Container container : containersInFile) {
				for (Container containerInVehicle : selectedVehicle.getContainers()) {
					if (containerInVehicle.getCNumber() == container.getCNumber()) {
						containers.add(container);
					}
				}
			}

			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			displayUtils.displayContainers(containers);
			promptBuilder.createInputPrompt()
					.name("ContainerSelect")
					.message("Enter the Container Id you would like to load (int):")
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

			// Load Container
			selectedVehicle.unloadContainer(selectedContainer);
			portManaging.loadContainerToPort(selectedContainer);
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
			Port selectedPort = portManaging;
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			displayUtils.displayVehicles(vehicles);
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like to load:")
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
			displayUtils.displayContainers(containers);
			promptBuilder.createInputPrompt()
					.name("ContainerSelect")
					.message("Enter the Container Id you would like to unload (int):")
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

			// Load Container
			selectedPort.unloadContainerFromPort(selectedContainer);
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
}
