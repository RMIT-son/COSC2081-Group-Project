package main.InterfaceUtils.AdminOps.Vehicles;

import de.codeshelf.consoleui.prompt.*;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.HashMap;

import static main.InterfaceUtils.AdminOps.Vehicles.CreateMenus.*;
import static org.fusesource.jansi.Ansi.ansi;

public class AdminVehiclesUtils {
	public static void create() throws IOException {
		try {
			// Create Vehicle Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createListPrompt()
					.name("Type")
					.message("Select Vehicle Type: ")
					.newItem("Basic").text("Basic Truck").add()
					.newItem("Reefer").text("Reefer Truck").add()
					.newItem("Tanker").text("Tanker Truck").add()
					.newItem("Ship").text("Ship").add()
					.newItem("Back").text("Back").add()
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			ListResult typeResult = (ListResult) result.get("Type");

			// Create Vehicle Menu Switch
			switch (typeResult.getSelectedId()) {
				case "Basic":
					basicMenu();
					break;
				case "Reefer":
					reeferMenu();
					break;
				case "Tanker":
					tankerMenu();
					break;
				case "Ship":
					shipMenu();
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

	public static void edit() {
		// TODO implement edit vehicle interface
		try {
			// Edit Vehicle Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Edit Vehicle"));
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void delete() {
		// TODO implement delete vehicle interface
	}
}
