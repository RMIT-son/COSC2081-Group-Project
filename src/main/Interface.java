package main;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class Interface {
	public static void run() {
		AnsiConsole.systemInstall();                                      // #1
		System.out.println(ansi().eraseScreen().render("Welcome to the Port Management System"));
		System.out.println(ansi().render("Please login to continue"));
		boolean loginState = true;

		// TODO temp for testing
		boolean uCondition = true;
		boolean pCondition = true;
		User user = new PortManager();

		// LOGIN
		while (loginState) {
			try {
				ConsolePrompt prompt = new ConsolePrompt();
				PromptBuilder promptBuilder = prompt.getPromptBuilder();
				promptBuilder.createInputPrompt()
						.name("Username")
						.message("Please enter your username")
						.defaultValue("John Doe")
//						.addCompleter(new StringsCompleter("Jim", "Jack", "John"))
						.addPrompt();

				promptBuilder.createInputPrompt()
						.name("Password")
						.message("Please enter your Password")
						.defaultValue("pwd12345")
						.mask('*')
						.addPrompt();

				HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
				System.out.println("result = " + result);
				InputResult username = (InputResult) result.get("Username");
				InputResult password = (InputResult) result.get("Password");
				System.out.println("username = " + username.getInput());
				System.out.println("password = " + password.getInput());

				if (uCondition && pCondition) {
					System.out.println(ansi().render("Login successful"));
					loginState = false;
				} else {
					System.out.println(ansi().render("Login failed"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					TerminalFactory.get().restore();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (user instanceof SystemAdmin) {
			System.out.println(ansi().render("Welcome, System Admin"));
			mainMenuAdmin();
		} else if (user instanceof PortManager) {
			System.out.println(ansi().render("Welcome, Port Manager"));
			mainMenuPortManager();
		}
	}

	public static void mainMenuAdmin() {
		boolean menuState = true;
		while (menuState) {
			try {
				ConsolePrompt prompt = new ConsolePrompt();                     // #2
				PromptBuilder promptBuilder = prompt.getPromptBuilder();
				promptBuilder.createListPrompt()                                // #4
						.name("Main")
						.message("Choose an option?")
						.newItem("Ports").text("Ports").add()  // without name (name defaults to text)
						.newItem("Vehicles").text("Vehicles").add()
						.newItem("Containers").text("Containers").add()
						.newItem("Users").text("Users").add()
						.newItem("Statistics").text("Statistics").add()
						.newItem("Exit").text("Exit").add()
						.addPrompt();
				HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
				ListResult mainResult = (ListResult) result.get("Main");
				switch (mainResult.getSelectedId()) {
					case "Ports":
						System.out.println("Ports has been chosen");
						break;
					case "Vehicles":
						System.out.println("Vehicles has been chosen");
						break;
					case "Containers":
						System.out.println("Containers has been chosen");
						break;
					case "Users":
						System.out.println("Users has been chosen");
						break;
					case "Statistics":
						System.out.println("Statistics has been chosen");
						break;
					case "Exit":
						System.out.println("Exiting");
						menuState = false;
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					TerminalFactory.get().restore();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void mainMenuPortManager() {
		boolean menuState = true;
		while (menuState) {
			try {
				ConsolePrompt prompt = new ConsolePrompt();
				PromptBuilder promptBuilder = prompt.getPromptBuilder();
				promptBuilder.createListPrompt()
						.name("Main")
						.message("Choose an option?")
						.newItem("Port").text("Port").add()
						.newItem("Vehicles").text("Vehicles").add()
						.newItem("Containers").text("Containers").add()
						.newItem("Statistics").text("Statistics").add()
						.newItem("Exit").text("Exit").add()
						.addPrompt();
				HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
				ListResult mainResult = (ListResult) result.get("Main");
				switch (mainResult.getSelectedId()) {
					case "Port":
						System.out.println("Port has been chosen");
						break;
					case "Vehicles":
						System.out.println("Vehicles has been chosen");
						break;
					case "Containers":
						System.out.println("Containers has been chosen");
						break;
					case "Statistics":
						System.out.println("Statistics has been chosen");
						break;
					case "Exit":
						System.out.println("Exiting");
						menuState = false;
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					TerminalFactory.get().restore();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}