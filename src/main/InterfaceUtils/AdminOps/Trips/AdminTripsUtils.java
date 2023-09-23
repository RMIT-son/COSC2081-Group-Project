package main.InterfaceUtils.AdminOps.Trips;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.NotFoundException;
import main.InterfaceUtils.displayUtils;
import main.porttrip.Port;
import main.porttrip.Trip;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;


import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static main.porttrip.Trip.readTrip;
import static main.vehicle.Vehicle.readVehicle;
import static org.fusesource.jansi.Ansi.ansi;

public class AdminTripsUtils {

	public static void view() throws IOException {
		boolean viewMenuSwitch = true;
		ArrayList<Trip> viewTripsList = (ArrayList<Trip>) readTrip();
		while (viewMenuSwitch) {
			try {
				// View Trip Menu Setup
				displayUtils.displayTrips(viewTripsList);
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
	public static void schedule() throws IOException {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) readVehicle();
		ArrayList<Port> ports = (ArrayList<Port>) Port.readPort();
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Schedule new Trip"));
			System.out.println("Current Vehicles: ");
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

			// Get Departure Port if not specified
			Port dPort;
			if (vehicle.getCurrentPort() == null) {
				displayUtils.displayPorts(ports);
				prompt = new ConsolePrompt();
				promptBuilder = prompt.getPromptBuilder();
				promptBuilder.createInputPrompt()
						.name("DPort")
						.message("Enter Departure Port name:")
						.addPrompt();
				result = prompt.prompt(promptBuilder.build());
				InputResult dPortInput = (InputResult) result.get("DPort");
				String dPortName = dPortInput.getInput().trim();
				dPort = null;
				for (Port p : ports) {
					if (p.getName().equalsIgnoreCase(dPortName)) {
						dPort = p;
					}
				}
				if (dPort == null) {
					throw new NotFoundException();
				}
			} else {
				dPort = vehicle.getCurrentPort();
			}

			displayUtils.displayPorts(ports);
			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("APort")
					.message("Enter Arrival Port name:")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult aPortInput = (InputResult) result.get("APort");
			String aPortName = aPortInput.getInput().trim();
			Port aPort = null;
			for (Port p : ports) {
				if (p.getName().equalsIgnoreCase(aPortName)) {
					aPort = p;
				}
			}
			if (aPort == null) {
				throw new NotFoundException();
			}

			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("DDate")
					.message("Enter Departure Date (yyyy-MM-dd, dd/MM/yyyy, dd/MM/yy):")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("ADate")
					.message("Enter Arrival Date (yyyy-MM-dd, dd/MM/yyyy, dd/MM/yy):")
					.addPrompt();

			// Initialize Variables
			result = prompt.prompt(promptBuilder.build());
			InputResult dDateInput = (InputResult) result.get("DDate");
			String dDate = dDateInput.getInput().trim();
			InputResult aDateInput = (InputResult) result.get("ADate");
			String aDate = aDateInput.getInput().trim();
			LocalDate parsedDDate = null;
			LocalDate parsedADate = null;

			// Define a list of acceptable date formats
			List<String> dateFormats = new ArrayList<>();
			dateFormats.add("yyyy-MM-dd");
			dateFormats.add("dd/MM/yyyy");
			dateFormats.add("dd/MM/yy");

			for (String dateFormat : dateFormats) {
				try {
					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
					parsedDDate = LocalDate.parse(dDate, dateFormatter);
					parsedADate = LocalDate.parse(aDate, dateFormatter);
					break; // Exit the loop if a valid format is found
				} catch (DateTimeParseException e) {
					System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid date format. Please enter a date in the format specified format"));
				}
			}

			// Create Trip
			Trip newTrip = new Trip(vehicle, parsedDDate, parsedADate, dPort, aPort);
			dPort.addTrip(newTrip);
			aPort.addTrip(newTrip);
			newTrip.createTrip();

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
}
