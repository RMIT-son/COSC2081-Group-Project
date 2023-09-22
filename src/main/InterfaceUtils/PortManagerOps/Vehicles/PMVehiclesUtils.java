package main.InterfaceUtils.PortManagerOps.Vehicles;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.Edit;
import main.vehicle.Truck;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static main.InterfaceUtils.PortManagerOps.PMInterface.portManaging;
import static org.fusesource.jansi.Ansi.ansi;

public class PMVehiclesUtils {
	public static void edit() {
		try {
			ArrayList<Vehicle> vehiclesInPort = (ArrayList<Vehicle>) portManaging.getVehicles();
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Vehicle CRUD Manager Menu"));
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Current Vehicles in Port:"));
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Step 1 of 3"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("VehiclesSelect")
					.message("Enter the Vehicle Name you would like Edit: ")
					.defaultValue("Honda")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
			String selectedVehicleName = vehiclesInput.getInput().trim();
			Vehicle selectedVehicle = null;

			for (Vehicle vehicle : vehiclesInPort) {
				if (vehicle instanceof Truck && vehicle.getName().equalsIgnoreCase(selectedVehicleName)) {
					selectedVehicle = vehicle;
					Edit.editVehicle(selectedVehicle);
					break;
				}
			}
			if (selectedVehicle == null) {
				System.out.println(ansi().fg(Ansi.Color.RED).render("Vehicle not found"));
			}
			// TODO May have to add more code here for menu logic
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
}
