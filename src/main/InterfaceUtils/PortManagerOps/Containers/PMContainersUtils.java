package main.InterfaceUtils.PortManagerOps.Containers;

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
import java.util.List;

import static main.InterfaceUtils.PortManagerOps.PMInterface.portManaging;
import static main.InterfaceUtils.PortManagerOps.Containers.CreateContainer.*;
import static main.porttrip.Port.readPort;
import static main.vehicle.Vehicle.readVehicle;
import static org.fusesource.jansi.Ansi.ansi;

public class PMContainersUtils {
	public static void  view() throws IOException {
		boolean viewMenuSwitch = true;
		List<Port> viewPortsList = readPort();
		for (Port port : viewPortsList) {
			if (port.getPNumber() == portManaging.getPNumber()) {
				portManaging = port;
				break;
			}
		}


		ArrayList<Container> containersInPort = (ArrayList<Container>) portManaging.getContainers();
		ArrayList<Container> containersInFile = (ArrayList<Container>) Container.readContainer();
		ArrayList<Container> containers = new ArrayList<>();
		for (Container container : containersInFile) {
			for (Container containerInPort : containersInPort) {
				if (container.getCNumber() == containerInPort.getCNumber()) {
					containers.add(container);
				}
			}
		}

		while (viewMenuSwitch) {
			try {
				// View Container Menu Setup
				displayUtils.displayContainers(containers);
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
			// Create Containers Menu Setup
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Create Container"));
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createListPrompt()
					.name("Type")
					.message("Select Container Type:")
					.newItem("Dry").text("Dry Storage").add()
					.newItem("Liquid").text("Liquid").add()
					.newItem("OS").text("Open Side").add()
					.newItem("OT").text("Open Top").add()
					.newItem("Ref").text("Refrigerated").add()
					.newItem("Back").text("Back").add()
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			ListResult typeResult = (ListResult) result.get("Type");

			// Create Containers Menu Switch
			switch (typeResult.getSelectedId()) {
				case "Dry":
					dryMenu();
					break;
				case "Liquid":
					liquidMenu();
					break;
				case "OS":
					osMenu();
					break;
				case "OT":
					otMenu();
					break;
				case "Ref":
					refMenu();
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
		List<Port> viewPortsList = readPort();
		for (Port port : viewPortsList) {
			if (port.getPNumber() == portManaging.getPNumber()) {
				portManaging = port;
				break;
			}
		}

		ArrayList<Container> containersInPort = (ArrayList<Container>) portManaging.getContainers();
		ArrayList<Container> containersInFile = (ArrayList<Container>) Container.readContainer();
		ArrayList<Container> containers = new ArrayList<>();
		for (Container container : containersInFile) {
			for (Container containerInPort : containersInPort) {
				if (container.getCNumber() == containerInPort.getCNumber()) {
					containers.add(container);
				}
			}
		}
		try {
			// Edit Container Menu Setup
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Edit Container"));
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			displayUtils.displayContainers(containers);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("ContainersSelect")
					.message("Enter the Container ID you would like Edit: ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult containersInput = (InputResult) result.get("ContainersSelect");
			int selectedContainerId = Integer.parseInt(containersInput.getInput().trim());
			Container selectedContainer = null;

			// Find Container
			for (Container container : containers) {
				if (container.getCNumber() == selectedContainerId) {
					selectedContainer = container;
					Edit.editContainer(selectedContainer);
					break;
				}
			}
			if (selectedContainer == null) {
				System.out.println(ansi().fg(Ansi.Color.RED).render("Container not found"));
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
		List<Port> viewPortsList = readPort();
		for (Port port : viewPortsList) {
			if (port.getPNumber() == portManaging.getPNumber()) {
				portManaging = port;
				break;
			}
		}


		ArrayList<Container> containersInPort = (ArrayList<Container>) portManaging.getContainers();
		ArrayList<Container> containersInFile = (ArrayList<Container>) Container.readContainer();
		ArrayList<Container> containers = new ArrayList<>();
		for (Container container : containersInFile) {
			for (Container containerInPort : containersInPort) {
				if (container.getCNumber() == containerInPort.getCNumber()) {
					containers.add(container);
				}
			}
		}
		try {
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Delete Container"));
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Current Containers:"));
			displayUtils.displayContainers(containers);
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("ContainersSelect")
					.message("Enter the Container Name you would like Edit:")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult containersInput = (InputResult) result.get("ContainersSelect");
			int selectedContainerId = Integer.parseInt(containersInput.getInput().trim());
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

			// Delete Port Confirmation
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createConfirmPromp()
					.name("Delete")
					.message("Are you sure you want to delete this Container?")
					.defaultValue(ConfirmChoice.ConfirmationValue.YES)
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			ConfirmResult confirmResult = (ConfirmResult) result.get("Delete");

			// Delete Container
			if (confirmResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES) {
				assert selectedContainer != null;
				selectedContainer.deleteContainer();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Container not found."));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void loadToVehicle() {
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
				if (container.getCNumber() == containerInPort.getCNumber()) {
					containers.add(container);
				}
			}
		}
		try {
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Load Container"));
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Current Containers:"));
			displayUtils.displayContainers(containers);
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("ContainersSelect")
					.message("Enter the Container Name you would like Edit:")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult containersInput = (InputResult) result.get("ContainersSelect");
			int selectedContainerId = Integer.parseInt(containersInput.getInput().trim());
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

			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Current Vehicles:"));
			displayUtils.displayVehicles(portManaging.getVehicles());
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 2 of 2"));
			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like to load: ")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
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

			// Load Container
			portManaging.unloadContainerFromPort(selectedContainer);
			selectedVehicle.loadContainer(selectedContainer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Container not found."));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void loadToPort() {
		ArrayList<Container> containers = (ArrayList<Container>) portManaging.getContainers();
		try {
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Load Container"));
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Current Containers:"));
			displayUtils.displayContainers(containers);
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("ContainersSelect")
					.message("Enter the Container Name you would like Edit:")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult containersInput = (InputResult) result.get("ContainersSelect");
			int selectedContainerId = Integer.parseInt(containersInput.getInput().trim());
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

			portManaging.loadContainerToPort(selectedContainer);
			portManaging.updatePort();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Container not found."));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

