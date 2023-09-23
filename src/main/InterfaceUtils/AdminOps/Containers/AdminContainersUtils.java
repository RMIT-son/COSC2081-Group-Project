package main.InterfaceUtils.AdminOps.Containers;

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

import static main.container.Container.readContainer;
import static main.vehicle.Vehicle.readVehicle;
import static org.fusesource.jansi.Ansi.ansi;
import static main.InterfaceUtils.AdminOps.Containers.CreateContainer.*;

public class AdminContainersUtils {
	public static void  view() throws IOException {
		boolean viewMenuSwitch = true;
		ArrayList<Container> viewContainersList = (ArrayList<Container>) readContainer();
		while (viewMenuSwitch) {
			try {
				// View Container Menu Setup
				displayUtils.displayContainers(viewContainersList);
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
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Container"));
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
		ArrayList<Container> containers = (ArrayList<Container>) readContainer();
		try {
			// Edit Container Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Edit Container"));
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
		ArrayList<Container> containers = (ArrayList<Container>) readContainer();
		try {
			// Delete Port Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Delete Container"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Containers:"));
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
		ArrayList<Container> containers = (ArrayList<Container>) readContainer();
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Load Container"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Containers:"));
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

			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Vehicles:"));
			displayUtils.displayVehicles(readVehicle());
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
			for (Vehicle vehicle : readVehicle()) {
				if (vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
					selectedVehicle = vehicle;
					break;
				}
			}
			if (selectedVehicle == null) {
				throw new NotFoundException();
			}

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
		ArrayList<Container> containers = (ArrayList<Container>) readContainer();
		ArrayList<Port> ports = (ArrayList<Port>) Port.readPort();
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Load Container"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Containers:"));
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

			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Ports:"));
			displayUtils.displayPorts(ports);
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 2 of 2"));
			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("PortsSelect")
					.message("Enter the Port Name you would like to load: ")
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

			selectedPort.loadContainerToPort(selectedContainer);
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
