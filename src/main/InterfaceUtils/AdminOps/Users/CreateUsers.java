package main.InterfaceUtils.AdminOps.Users;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.Users.PortManager;
import main.Users.SystemAdmin;
import main.container.DryStorage;
import main.porttrip.Port;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class CreateUsers {
	public static void Admin() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Admin"));
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Username")
					.message("Enter new Admin Username")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Password")
					.message("Enter new Admin Password ")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult usernameInput = (InputResult) result.get("Username");
			String username = usernameInput.getInput().trim();
			InputResult weightInput = (InputResult) result.get("Password");
			String password = weightInput.getInput().trim();

			// Create Vehicle
			SystemAdmin newAdmin = new SystemAdmin(username, password);
			newAdmin.createUser();

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

	public static void PM() throws IOException {
		try {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Admin"));
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("Username")
					.message("Enter new Manager Username")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Password")
					.message("Enter new Manager Password ")
					.addPrompt();
			promptBuilder.createInputPrompt()
					.name("Port")
					.message("Enter Port Name Managing")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult usernameInput = (InputResult) result.get("Username");
			String username = usernameInput.getInput().trim();
			InputResult weightInput = (InputResult) result.get("Password");
			String password = weightInput.getInput().trim();
			InputResult portInput = (InputResult) result.get("Port");
			String port = portInput.getInput().trim();

			ArrayList<Port> ports = (ArrayList<Port>) Port.readPort();
			Port selectedPort = null;
			for (Port p : ports) {
				if (p.getName().equalsIgnoreCase(port)) {
					selectedPort = p;
					break;
				}
			}
			// Create Vehicle
			PortManager newPM = new PortManager(username, password, selectedPort);
			newPM.createUser();

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
