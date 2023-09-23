package main.InterfaceUtils.PortManagerOps.Containers;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.container.*;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.HashMap;

import static main.InterfaceUtils.PortManagerOps.PMInterface.portManaging;
import static org.fusesource.jansi.Ansi.ansi;

public class CreateContainer {
	public static void dryMenu() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Dry Storage Container"));
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
					.defaultValue("0.0")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult weightInput = (InputResult) result.get("Weight");
			double fuel = Double.parseDouble(weightInput.getInput().trim());
			InputResult RFInput = (InputResult) result.get("Fuel");
			double requiredFuel = Double.parseDouble(RFInput.getInput().trim());

			// Create Vehicle
			DryStorage newContainer = new DryStorage(id, fuel, requiredFuel, null, null);
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Container created successfully!"));
			newContainer.createContainer();
			portManaging.loadContainerToPort(newContainer);

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


	public static void liquidMenu() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Create Liquid Container"));
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
					.defaultValue("0.0")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult weightInput = (InputResult) result.get("Weight");
			double fuel = Double.parseDouble(weightInput.getInput().trim());
			InputResult RFInput = (InputResult) result.get("Fuel");
			double requiredFuel = Double.parseDouble(RFInput.getInput().trim());

			// Create Vehicle
			Liquid newContainer = new Liquid(id, fuel, requiredFuel, null, null);
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Container created successfully!"));
			newContainer.createContainer();
			portManaging.loadContainerToPort(newContainer);

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

	public static void refMenu() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Create Refrigerated Container"));
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
					.defaultValue("0.0")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult weightInput = (InputResult) result.get("Weight");
			double fuel = Double.parseDouble(weightInput.getInput().trim());
			InputResult RFInput = (InputResult) result.get("Fuel");
			double requiredFuel = Double.parseDouble(RFInput.getInput().trim());

			// Create Vehicle
			Refrigerated newContainer = new Refrigerated(id, fuel, requiredFuel, null, null);
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Container created successfully!"));
			newContainer.createContainer();
			portManaging.loadContainerToPort(newContainer);

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

	public static void otMenu() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Create Open Top Container"));
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
					.defaultValue("0.0")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult weightInput = (InputResult) result.get("Weight");
			double fuel = Double.parseDouble(weightInput.getInput().trim());
			InputResult RFInput = (InputResult) result.get("Fuel");
			double requiredFuel = Double.parseDouble(RFInput.getInput().trim());

			// Create Vehicle
			OpenTop newContainer = new OpenTop(id, fuel, requiredFuel, null, null);
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Container created successfully!"));
			newContainer.createContainer();
			portManaging.loadContainerToPort(newContainer);

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

	public static void osMenu() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Create Open Side Container"));
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
					.defaultValue("0.0")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult idInput = (InputResult) result.get("Id");
			int id = Integer.parseInt(idInput.getInput().trim());
			InputResult weightInput = (InputResult) result.get("Weight");
			double fuel = Double.parseDouble(weightInput.getInput().trim());
			InputResult RFInput = (InputResult) result.get("Fuel");
			double requiredFuel = Double.parseDouble(RFInput.getInput().trim());

			// Create Vehicle
			OpenSide newContainer = new OpenSide(id, fuel, requiredFuel, null, null);
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Container created successfully!"));
			newContainer.createContainer();
			portManaging.loadContainerToPort(newContainer);

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
