package main.InterfaceUtils.AdminOps.Ports;

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
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.*;

import static main.porttrip.Port.readPort;
import static main.vehicle.Vehicle.readVehicle;
import static org.fusesource.jansi.Ansi.ansi;

public class AdminPortUtils {
	public static void view() throws IOException {
		boolean viewMenuSwitch = true;
		ArrayList<Port> viewPortsList = (ArrayList<Port>) readPort();
		while (viewMenuSwitch) {
			try {
				// View Vehicle Menu Setup
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
	public static void create() throws IOException {
		try {
			// Create Port Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Port"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Id")
					.message("Enter new Port Id (int): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Name")
					.message("Enter new Port Name: ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Store")
					.message("Enter Storing Capacity (double): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Latitude")
					.message("Enter Latitude (double): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Longitude")
					.message("Enter Longitude (double): ")
					.addPrompt();
			promptBuilder.createConfirmPromp()
					.name("Landing")
					.message("Does this Port support Landing?")
					.defaultValue(ConfirmChoice.ConfirmationValue.YES)
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult nameInput = (InputResult) result.get("Name");
			String portName = nameInput.getInput().trim();
			InputResult storeInput = (InputResult) result.get("Store");
			double store = Double.parseDouble(storeInput.getInput().trim());
			InputResult latInput = (InputResult) result.get("Latitude");
		    double lat = Double.parseDouble(latInput.getInput().trim());
			InputResult longInput = (InputResult) result.get("Longitude");
			double lon = Double.parseDouble(longInput.getInput().trim());
			ConfirmResult confirmResult = (ConfirmResult) result.get("Landing");
			boolean landing = confirmResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES;

			// Create Port
			Port newPort = new Port(id, portName, landing, lat, lon, store);
			newPort.createPort();
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

	public static void editChoose() {
		// temp ports for testing
		ArrayList<Port> ports = (ArrayList<Port>) readPort();
		try {
			// Edit Port Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Edit Port"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Vehicles in Port:"));
			displayUtils.displayPorts(ports);
			System.out.println(ansi().fg(Ansi.Color.RED).render("Step 1 of 3"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("PortsSelect")
					.message("Enter the Port Name you would like Edit: ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult portsInput = (InputResult) result.get("PortsSelect");
			String selectedPortName = portsInput.getInput().trim();
			Port selectedPort = null;

			// Find Port
			for (Port port : ports) {
				if (port.getName().equalsIgnoreCase(selectedPortName)) {
					selectedPort = port;
					Edit.editPort(selectedPort);
					break;
				}
			}
			if (selectedPort == null) {
				System.out.println(ansi().fg(Ansi.Color.RED).render("Port not found"));
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
		// temp ports for testing
		ArrayList<Port> ports = (ArrayList<Port>) readPort();
		try {
			// Delete Port Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Delete Port"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Ports:"));
			displayUtils.displayPorts(ports);
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("PortsSelect")
					.message("Enter the Port Name you would like Delete: ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
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

			// Delete Port Confirmation
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createConfirmPromp()
					.name("Delete")
					.message("Are you sure you want to delete this Port?")
					.defaultValue(ConfirmChoice.ConfirmationValue.YES)
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			ConfirmResult confirmResult = (ConfirmResult) result.get("Delete");

			// Delete Port
			if (confirmResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES) {
				assert selectedPort != null;
				selectedPort.deletePort();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Port not found"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void loadContainerMenu() {
		ArrayList<Port> ports = (ArrayList<Port>) readPort();
		try {
			// Delete Port Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Load Container to Port"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Ports:"));
			displayUtils.displayPorts(ports);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("PortsSelect")
					.message("Enter the Port Name you would like to load: ")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
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

			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			displayUtils.displayVehicles(selectedPort.getVehicles());
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like to unload: ")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;
			// Find Vehicle
			for (Vehicle vehicle : readVehicle()) {
				if (vehicle.getName().equalsIgnoreCase(selectedVehicleName) &&  selectedPort.findVehicle(selectedVehicleName)) {
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

			// Load Container
			selectedVehicle.unloadContainer(selectedContainer);
			selectedPort.loadContainerToPort(selectedContainer);
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

	public static void unloadContainerMenu() {
		ArrayList<Port> ports = (ArrayList<Port>) readPort();
		try {
			// Delete Port Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Unload Container from Port"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Ports:"));
			displayUtils.displayPorts(ports);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("PortsSelect")
					.message("Enter the Port Name you would like to unload: ")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
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

			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			displayUtils.displayVehicles(selectedPort.getVehicles());
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like to load: ")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;
			// Find Vehicle

			for (Vehicle vehicle : selectedPort.getVehicles()) {
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
			displayUtils.displayContainers(selectedPort.getContainers());
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
			for (Container container : selectedPort.getContainers()) {
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
