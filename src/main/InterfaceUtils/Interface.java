package main.InterfaceUtils;

import com.github.lalyos.jfiglet.FigletFont;
import main.Users.PortManager;
import main.Users.SystemAdmin;
import main.Users.User;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import jline.console.completer.StringsCompleter;
import main.InterfaceUtils.AdminOps.AdminInterface;
import main.InterfaceUtils.PortManagerOps.PMInterface;
import main.porttrip.Port;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class Interface {
	public static User currentUser = null;
	public static void run() {
		// Setup
		String PMS;
		AnsiConsole.systemInstall();
		try {
			PMS = FigletFont.convertOneLine("Port Management System");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(ansi().fg(Ansi.Color.CYAN).eraseScreen().render(PMS + "Ver 1.0.0"));
		System.out.println(ansi().render("Please login to continue"));


		// temp for testing for prototyping
		Port port = new Port(1, "Hamburg", true, 53.551086, 9.993682, 1000000, null, null, null);
		User mana = new PortManager("manager", "pwd12345", port);
		User admin = new SystemAdmin("admin", "pwd12345");
		ArrayList<User> users = new ArrayList<>();
		users.add(mana);
		users.add(admin);


		// LOGIN
		while (true) {
			try {
				boolean loginState = true;
				ConsolePrompt prompt = new ConsolePrompt();
				PromptBuilder promptBuilder = prompt.getPromptBuilder();

				// create a prompt for username and password
				promptBuilder.createInputPrompt()
						.name("Username")
						.message("Please enter your username")
						.defaultValue("John Doe")
						.addCompleter(new StringsCompleter("manager", "admin"))
						.addPrompt();
				promptBuilder.createInputPrompt()
						.name("Password")
						.message("Please enter your Password")
						.defaultValue("pwd12345")
						.mask('*')
						.addPrompt();

				// getting result of the prompt and casting it to usable type
				HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
				InputResult userInput = (InputResult) result.get("Username");
				InputResult passInput = (InputResult) result.get("Password");
				String username = userInput.getInput().trim();
				String password = passInput.getInput().trim();

				// logging for testing
				System.out.println("username={"+username+"}");
				System.out.println("password={"+password+"}");
				System.out.println("result = " + result);


				for (User user : users) {
					// validate user credentials
					boolean valid = username.equals(user.getUsername()) && password.equals(user.getPassword());
					if (valid) {
						System.out.println(ansi().fg(Ansi.Color.GREEN).eraseScreen().render("Login successful"));
						currentUser = user;  // set the current user to the user that logged in
						loginState = false;  // exit the login loop
					}
				}
				// if login failed, print error message
				if (!loginState) {
					break; // Exit the outer loop (loginState)
				} else {
					System.out.println(ansi().fg(Ansi.Color.RED).render("Login failed"));
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

		if (currentUser instanceof SystemAdmin) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Welcome, System Admin"));
			mainMenuAdmin();
		} else if (currentUser instanceof PortManager) {
			System.out.println(ansi().fg(Ansi.Color.BLUE).render("Welcome, Port Manager"));
			mainMenuPortManager();
		}
	}

	public static void mainMenuAdmin() {
		boolean menuState = true;
		while (menuState) {
			try {
				ConsolePrompt prompt = new ConsolePrompt();
				PromptBuilder promptBuilder = prompt.getPromptBuilder();
				promptBuilder.createListPrompt()
						.name("Main")
						.message("Which component would you like to manage")
						.newItem("Ports").text("Ports").add()
						.newItem("Vehicles").text("Vehicles").add()
						.newItem("Containers").text("Containers").add()
						.newItem("Trips").text("Trips").add()
						.newItem("Users").text("Users").add()
						.newItem("Statistics").text("Statistics").add()
						.newItem("Exit").text("Exit").add()
						.addPrompt();
				HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
				ListResult mainResult = (ListResult) result.get("Main");
				switch (mainResult.getSelectedId()) {
					case "Ports":
						System.out.println("Ports has been chosen");
						AdminInterface.portsOPS();
						break;
					case "Vehicles":
						System.out.println("Vehicles has been chosen");
						AdminInterface.vehiclesOPS();
						break;
					case "Containers":
						System.out.println("Containers has been chosen");
						AdminInterface.containersOPS();
						break;
					case "Trips":
						System.out.println("Trips has been chosen");
						AdminInterface.tripsOPS();
						break;
					case "Users":
						System.out.println("main.Users has been chosen");
						AdminInterface.usersOPS();
						break;
					case "Statistics":
						System.out.println("Statistics has been chosen");
						AdminInterface.statOPS();
						break;
					case "Exit":
						System.out.println("Exiting...");
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
						.message("Which component would you like to manage")
						.newItem("Port").text("Port").add()
						.newItem("Vehicles").text("Vehicles").add()
						.newItem("Containers").text("Containers").add()
						.newItem("Trips").text("Trips").add()
						.newItem("Statistics").text("Statistics").add()
						.newItem("Exit").text("Exit").add()
						.addPrompt();
				HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
				ListResult mainResult = (ListResult) result.get("Main");
				switch (mainResult.getSelectedId()) {
					case "Port":
						System.out.println("Port has been chosen");
						PMInterface.portOPS();
						break;
					case "Vehicles":
						System.out.println("Vehicles has been chosen");
						PMInterface.vehiclesOPS();
						break;
					case "Containers":
						System.out.println("Containers has been chosen");
						PMInterface.containersOPS();
						break;
					case "Trips":
						System.out.println("Trips has been chosen");
						PMInterface.tripsOPS();
						break;
					case "Statistics":
						System.out.println("Statistics has been chosen");
						PMInterface.statOPS();
						break;
					case "Exit":
						System.out.println("Exiting...");
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