package main.InterfaceUtils.AdminOps.Ports;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.Edit;
import main.InterfaceUtils.displayUtils;
import main.porttrip.Port;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static main.porttrip.Port.readPort;
import static org.fusesource.jansi.Ansi.ansi;

public class AdminPortUtils {
	static boolean viewMenuSwitch = true;

	public static void view() throws IOException {
		ArrayList<Port> viewPortsList = (ArrayList<Port>) readPort();
		while (viewMenuSwitch) {
			try {
				// View Vehicle Menu Setup
				System.out.println(ansi().fg(Ansi.Color.RED).render("View Ports"));
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
					.defaultValue("Hamburg")
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
			// May have to add more code here for menu logic
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
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
					.message("Enter the Port Name you would like Edit: ")
					.defaultValue("Hamburg")
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
				System.out.println(ansi().fg(Ansi.Color.RED).render("Port not found"));
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
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
