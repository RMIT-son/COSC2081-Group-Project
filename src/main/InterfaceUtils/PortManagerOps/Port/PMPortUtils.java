package main.InterfaceUtils.PortManagerOps.Port;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.displayUtils;
import main.porttrip.Port;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static main.InterfaceUtils.PortManagerOps.PMInterface.portManaging;

public class PMPortUtils {
	public static void view() throws IOException {
		boolean viewMenuSwitch = true;
		ArrayList<Port> viewPortsList = new ArrayList<>(Collections.singleton(portManaging));
		while (viewMenuSwitch) {
			try {
				// View Vehicle Menu Setup
				displayUtils.displayPorts(viewPortsList);
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
	public static void edit() {
		// TODO implement edit Port interface
	}

	public static void loadMenu() {
		// TODO implement delete Port interface
	}

	public static void unloadMenu() {
		// TODO implement delete Port interface
	}

}
