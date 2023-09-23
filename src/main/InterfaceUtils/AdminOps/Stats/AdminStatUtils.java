package main.InterfaceUtils.AdminOps.Stats;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.NotFoundException;
import main.InterfaceUtils.displayUtils;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static main.porttrip.Trip.getFuelConsumptionADay;
import static main.vehicle.Vehicle.readVehicle;
import static org.fusesource.jansi.Ansi.ansi;

public class AdminStatUtils {
	public static void calcFuel() throws IOException {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) readVehicle();
		try {
			// Vehicle Prompt
			displayUtils.displayVehicles(vehicles);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Vehicle")
					.message("Enter Vehicle name:")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult vehicleInput = (InputResult) result.get("Vehicle");
			String vehicleName = vehicleInput.getInput().trim();
			Vehicle vehicle = null;
			for (Vehicle v : vehicles) {
				if (v.getName().equalsIgnoreCase(vehicleName)) {
					vehicle = v;
				}
			}
			if (vehicle == null) {
				throw new NotFoundException();
			}

			// Distance Prompt
			promptBuilder.createInputPrompt()
					.name("Distance")
					.message("Enter Distance:")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult distanceInput = (InputResult) result.get("Date");
			double distance = Double.parseDouble(distanceInput.getInput().trim());
			double fuel1Day = getFuelConsumptionADay(vehicle, distance);

			// Continue Prompt
			while (true) {
				System.out.println(ansi().fg(Ansi.Color.GREEN).render("Fuel Consumption for 1 day: " + fuel1Day));
				prompt = new ConsolePrompt();
				promptBuilder = prompt.getPromptBuilder();
				promptBuilder.createConfirmPromp()
						.name("Continue")
						.message("Continue?")
						.defaultValue(ConfirmChoice.ConfirmationValue.NO)
						.addPrompt();
				result = prompt.prompt(promptBuilder.build());
				ConfirmResult continueResult = (ConfirmResult) result.get("Continue");
				boolean cont = continueResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES;
				if (!cont) {
					break;
				}
			}

		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter the correctly specified value type."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null value"));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Specified Item Not Found"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void calcWeight() {
		// TODO
	}

	public static void calcDistance() {
		// TODO
	}

	public static void listShips() {
		// TODO
	}

	public static void listTrips1Day() {
		// TODO
	}

	public static void listTripsMulti() {
		// TODO
	}

}
