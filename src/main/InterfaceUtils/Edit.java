package main.InterfaceUtils;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.porttrip.Port;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class Edit {
	public static void edit(Port port) throws IOException {
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
}
