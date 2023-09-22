package main.InterfaceUtils;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.PortManagerOps.Vehicles.PMVehiclesUtils;
import main.Users.User;
import main.container.Container;
import main.container.DryStorage;
import main.porttrip.Port;
import main.porttrip.Trip;
import main.vehicle.Ship;
import main.vehicle.TankerTruck;
import main.vehicle.Truck;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class Edit {
	public static void editPort(Port port) throws IOException {
		try {
			System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Create Port Menu"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Name")
					.message("Enter new Port Name: ")
					.defaultValue(port.getName())
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Store")
					.message("Enter Storing Capacity (double): ")
					.defaultValue(String.valueOf(port.getStoringCapacity()))
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Latitude")
					.message("Enter Latitude (double): ")
					.defaultValue(String.valueOf(port.getLatitude()))
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Longitude")
					.message("Enter Longitude (double): ")
					.defaultValue(String.valueOf(port.getLongitude()))
					.addPrompt();
			promptBuilder.createConfirmPromp()
					.name("Landing")
					.message("Does this Port support Landing?")
					.defaultValue(ConfirmChoice.ConfirmationValue.YES)
					.addPrompt();

			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult nameInput = (InputResult) result.get("Name");
			InputResult storeInput = (InputResult) result.get("Store");
			InputResult latInput = (InputResult) result.get("Latitude");
			InputResult longInput = (InputResult) result.get("Longitude");
			ConfirmResult confirmResult = (ConfirmResult) result.get("Landing");
			String portName = nameInput.getInput().trim();
			double store = Double.parseDouble(storeInput.getInput().trim());
			double lat = Double.parseDouble(latInput.getInput().trim());
			double lon = Double.parseDouble(longInput.getInput().trim());
			boolean landing = confirmResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES;

			port.setName(portName);
			port.setStoringCapacity(store);
			port.setLatitude(lat);
			port.setLongitude(lon);
			port.setLandingAbility(landing);

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

	public static void editVehicle(Vehicle vehicle) throws IOException {
		try {
			// Edit Vehicle Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Edit Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();

			// Initialize Vehicle Id
			String defaultId;
			if (vehicle instanceof Truck) {
				defaultId = Integer.toString(((Truck) vehicle).getTNumber());
			} else if (vehicle instanceof Ship) {
				defaultId = Integer.toString(((Ship) vehicle).getSNumber());
			} else {
				defaultId = "N/A";
			}

			// Create Prompts
			promptBuilder.createInputPrompt()
					.name("Id")
					.message("Enter new Vehicle Number (int): ")
					.defaultValue(defaultId)
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Name")
					.message("Enter new Vehicle Name: ")
					.defaultValue(vehicle.getName())
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel")
					.message("Enter new Current Fuel (double): ")
					.defaultValue(Double.toString(vehicle.getFuel()))
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel Capacity")
					.message("Enter new Fuel Capacity (double): ")
					.defaultValue(Double.toString(vehicle.getFuelCapacity()))
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Carry Capacity")
					.message("Enter new Carry Capacity (double): ")
					.defaultValue(Double.toString(vehicle.getCarryCapacity()))
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult nameInput = (InputResult) result.get("Name");
			String vehicleName = nameInput.getInput().trim();
			InputResult fuelInput = (InputResult) result.get("Fuel");
			double fuel = Double.parseDouble(fuelInput.getInput().trim());
			InputResult FCInput = (InputResult) result.get("Fuel Capacity");
			double fuelCapacity = Double.parseDouble(FCInput.getInput().trim());
			InputResult CCInput = (InputResult) result.get("Carry Capacity");
			double carryCapacity = Double.parseDouble(CCInput.getInput().trim());

			// Edit Vehicle
			if (vehicle instanceof Truck) {
				((Truck) vehicle).setTNumber(id);
			} else if (vehicle instanceof Ship) {
				((Ship) vehicle).setSNumber(id);
			}
			vehicle.setName(vehicleName);
			vehicle.setFuel(fuel);
			vehicle.setFuelCapacity(fuelCapacity);
			vehicle.setCarryCapacity(carryCapacity);
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

	public static void editContainer(Container container) throws IOException {
		try {
			// Edit Container Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Edit Container"));
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Id")
					.message("Enter new Container Number (int): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Weight")
					.message("Enter new Container Weight (double): ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Fuel")
					.message("Enter new Container required Fuel (double): ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult weightInput = (InputResult) result.get("Weight");
			double fuel = Double.parseDouble(weightInput.getInput().trim());
			InputResult RFInput = (InputResult) result.get("Fuel");
			double requiredFuel = Double.parseDouble(RFInput.getInput().trim());

			// Edit Container
			container.setCNumber(id);
			container.setWeight(fuel);
			container.setRequiredFuel(requiredFuel);

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

	public static void editUser(User user) throws IOException {
		try {
			// Edit Container Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Edit Container"));
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Username")
					.message("Enter new Username: ")
					.defaultValue(user.getUsername())
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Password")
					.message("Enter new Password: ")
					.defaultValue(user.getPassword())
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult usernameInput = (InputResult) result.get("Username");
			String username = usernameInput.getInput().trim();
			InputResult weightInput = (InputResult) result.get("Password");
			String password = weightInput.getInput().trim();

			// Edit User
			user.setUsername(username);
			user.setPassword(password);

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
}
