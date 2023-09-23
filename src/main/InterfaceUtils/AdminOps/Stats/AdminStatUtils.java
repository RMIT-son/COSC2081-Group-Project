package main.InterfaceUtils.AdminOps.Stats;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.*;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.NotFoundException;
import main.InterfaceUtils.displayUtils;
import main.porttrip.Port;
import main.porttrip.Trip;
import main.vehicle.Ship;
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
import static main.porttrip.Trip.getFuelConsumptionADay;
import static main.porttrip.Trip.listTripsBetweenDates;
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
			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Distance")
					.message("Enter Distance:")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult distanceInput = (InputResult) result.get("Distance");
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
//			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null value"));
			e.printStackTrace();
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

	public static void calcDistance() throws IOException {
		ArrayList<Port> ports = (ArrayList<Port>) Port.readPort();
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Calculate Distance between Ports"));
			displayUtils.displayPorts(ports);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Port 1")
					.message("Enter First Port name:")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult dPortInput = (InputResult) result.get("Port 1");
			String portName1 = dPortInput.getInput().trim();
			Port port1 = null;
			for (Port p : ports) {
				if (p.getName().equalsIgnoreCase(portName1)) {
					port1 = p;
				}
			}
			if (port1 == null) {
				throw new NotFoundException();
			}


			displayUtils.displayPorts(ports);
			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Port 2")
					.message("Enter Arrival Port name:")
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			InputResult aPortInput = (InputResult) result.get("Port 2");
			String portName2 = aPortInput.getInput().trim();
			Port port2 = null;
			for (Port p : ports) {
				if (p.getName().equalsIgnoreCase(portName2)) {
					port2 = p;
				}
			}
			if (port2 == null) {
				throw new NotFoundException();
			}

			double distance = port1.distanceTo(port2);

			// Continue Prompt
			while (true) {
				System.out.println(ansi().fg(Ansi.Color.GREEN).render("Distance between ports: " + distance));
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

	public static void listShips() throws IOException {
		ArrayList<Port> ports = (ArrayList<Port>) Port.readPort();
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("List Ships at Port"));
			displayUtils.displayPorts(ports);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Port")
					.message("Enter Port name:")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult portInput = (InputResult) result.get("Port");
			String portName = portInput.getInput().trim();
			Port port = null;
			for (Port p : ports) {
				if (p.getName().equalsIgnoreCase(portName)) {
					port = p;
				}
			}
			if (port == null) {
				throw new NotFoundException();
			}

			ArrayList<Vehicle> ships = new ArrayList<>();
			for (Vehicle v : port.getVehicles()) {
				if (v instanceof Ship) {
					ships.add(v);
				}
			}
			// Continue Prompt
			while (true) {
				System.out.println(ansi().fg(Ansi.Color.GREEN).render("Ships at Port: "));
				displayUtils.displayVehicles(ships);
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

	public static void listTrips1Day() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("List Trips on Date"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Date")
					.message("Enter Date (yyyy-MM-dd):")
					.addPrompt();
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult dateInput = (InputResult) result.get("Date");
			String dateStr = dateInput.getInput().trim();
			LocalDate parsedDate = null;

			// Define a list of acceptable date formats
			List<String> dateFormats = new ArrayList<>();
			dateFormats.add("yyyy-MM-dd");
			dateFormats.add("dd/MM/yyyy");
			dateFormats.add("dd/MM/yy");
			for (String dateFormat : dateFormats) {
				try {
					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
					parsedDate = LocalDate.parse(dateStr, dateFormatter);
					break; // Exit the loop if a valid format is found
				} catch (DateTimeParseException e) {
					System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid date format. Please enter a date in the format specified format"));
				}
			}

			List<Trip> tripsOnDate = Trip.listTripsOnDate(parsedDate);
			// Continue Prompt
			while (true) {
				System.out.println(ansi().fg(Ansi.Color.GREEN).render("Trips on Date: "));
				displayUtils.displayTrips(tripsOnDate);
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
		} catch (DateTimeParseException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a date in the correct format"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void listTripsMulti() throws IOException {
		try {
		ConsolePrompt prompt = new ConsolePrompt();
		PromptBuilder promptBuilder = prompt.getPromptBuilder();
		promptBuilder.createInputPrompt()
				.name("SDate")
				.message("Enter Start Date (yyyy-MM-dd, dd/MM/yyyy, dd/MM/yy):")
				.addPrompt();
		promptBuilder.createInputPrompt()
				.name("EDate")
				.message("Enter End Date (yyyy-MM-dd, dd/MM/yyyy, dd/MM/yy):")
				.addPrompt();
			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult dDateInput = (InputResult) result.get("SDate");
			String dDate = dDateInput.getInput().trim();
			InputResult aDateInput = (InputResult) result.get("EDate");
			String aDate = aDateInput.getInput().trim();
			LocalDate parsedSDate = null;
			LocalDate parsedEDate = null;

			// Define a list of acceptable date formats
			List<String> dateFormats = new ArrayList<>();
			dateFormats.add("yyyy-MM-dd");
			dateFormats.add("dd/MM/yyyy");
			dateFormats.add("dd/MM/yy");

			for (String dateFormat : dateFormats) {
				try {
					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
					parsedSDate = LocalDate.parse(dDate, dateFormatter);
					parsedEDate = LocalDate.parse(aDate, dateFormatter);
					break; // Exit the loop if a valid format is found
				} catch (DateTimeParseException e) {
					System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid date format. Please enter a date in the format specified format"));
				}
			}
			List<Trip> trips = listTripsBetweenDates(parsedSDate, parsedEDate);
			while (true) {
				System.out.println(ansi().fg(Ansi.Color.GREEN).render("Trips from " + dDate + " to " + aDate + ":"));
				displayUtils.displayTrips(trips);
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
		} catch (DateTimeParseException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a date in the correct format"));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
