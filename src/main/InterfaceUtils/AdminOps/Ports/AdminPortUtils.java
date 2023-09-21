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
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class AdminPortUtils {
	public static void create() throws IOException {
		try {
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Create Port Menu"));
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

			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			InputResult nameInput = (InputResult) result.get("Name");
			InputResult storeInput = (InputResult) result.get("Store");
			InputResult latInput = (InputResult) result.get("Latitude");
			InputResult longInput = (InputResult) result.get("Longitude");
			ConfirmResult confirmResult = (ConfirmResult) result.get("Landing");

			int id = Integer.parseInt(idInput.getInput());
			String portName = nameInput.getInput().trim();
			double store = Double.parseDouble(storeInput.getInput().trim());
			double lat = Double.parseDouble(latInput.getInput().trim());
			double lon = Double.parseDouble(longInput.getInput().trim());
			boolean landing = confirmResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES;

			System.out.println("Port{" +
					"id=" + id +
					", name='" + portName + '\'' +
					", landing=" + landing +
					", latitude=" + lat +
					", longitude=" + lon +
					", storingCapacity=" + store +
					", ships=" + null +
					", containers=" + null +
					", users=" + null +
					'}');

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

	public static void editChoose() {
		// TODO implement edit port interface
		ArrayList<Port> ports = new ArrayList<>();
		try {
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Edit Port"));
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Current Ports:"));
			displayUtils.displayPorts(ports);
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Step 1 of 3"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("PortsSelect")
					.message("Enter the Port Name you would like Edit: ")
					.defaultValue("Honda")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult portsInput = (InputResult) result.get("VehiclesSelect");
			String selectedPortName = portsInput.getInput().trim();
			Port selectedPort = null;

			for (Port port : ports) {
				if (port.getName().equalsIgnoreCase(selectedPortName)) {
					selectedPort = port;
					Edit.edit(selectedPort);
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
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter the correctly specified value."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Invalid input. Please enter a non-null input."));
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
