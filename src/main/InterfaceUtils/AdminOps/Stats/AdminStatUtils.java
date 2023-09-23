package main.InterfaceUtils.AdminOps.Stats;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.*;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.NotFoundException;
import main.InterfaceUtils.displayUtils;
import main.container.Container;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static main.InterfaceUtils.AdminOps.Stats.ContainerWeight.*;
import static main.container.Container.readContainer;
import static main.porttrip.Trip.getFuelConsumptionADay;
import static main.vehicle.Vehicle.readVehicle;
import static org.fusesource.jansi.Ansi.ansi;

public class AdminStatUtils {
	public static void calcFuel() throws IOException {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) readVehicle();
		try {
			// Vehicle Prompt
			System.out.println(ansi().fg(Ansi.Color.RED).render("Calculate Fuel Consumption"));
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

	public static void calcWeight() throws IOException {
		// Menu Setup
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Calculate Container Weight"));
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

			//Menu Switch
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
