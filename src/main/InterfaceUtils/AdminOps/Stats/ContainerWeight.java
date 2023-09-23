package main.InterfaceUtils.AdminOps.Stats;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConfirmResult;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import main.container.*;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static main.container.Container.readContainer;
import static org.fusesource.jansi.Ansi.ansi;

public class ContainerWeight {
	static ArrayList<Container> containers = (ArrayList<Container>) readContainer();
	public static void dryMenu() throws IOException {
		ArrayList<Container> dryContainers = new ArrayList<>();
		for (Container c : containers) {
			if (c instanceof DryStorage) {
				dryContainers.add(c);
			}
		}
		double totalWeight = countTotalContainerWeight(dryContainers);

		while (true) {
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Total Weight of Dry Storage Containers: " + totalWeight));
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
				break;
			}
		}
	}

	public static void liquidMenu() throws IOException {
		ArrayList<Container> liquidContainers = new ArrayList<>();
		for (Container c : containers) {
			if (c instanceof Liquid) {
				liquidContainers.add(c);
			}
		}
		double totalWeight = countTotalContainerWeight(liquidContainers);

		while (true) {
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Total Weight of Liquid Containers: " + totalWeight));
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
				break;
			}
		}
	}

	public static void refMenu() throws IOException {
		ArrayList<Container> refContainers = new ArrayList<>();
		for (Container c : containers) {
			if (c instanceof Refrigerated) {
				refContainers.add(c);
			}
		}
		double totalWeight = countTotalContainerWeight(refContainers);

		while (true) {
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Total Weight of Refrigerated Containers: " + totalWeight));
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
				break;
			}
		}
	}

	public static void osMenu() throws IOException {
		ArrayList<Container> OSContainers = new ArrayList<>();
		for (Container c : containers) {
			if (c instanceof OpenSide) {
				OSContainers.add(c);
			}
		}
		double totalWeight = countTotalContainerWeight(OSContainers);

		while (true) {
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Total Weight of Open Side Containers: " + totalWeight));
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
				break;
			}
		}
	}

	public static void otMenu() throws IOException {
		ArrayList<Container> OTContainers = new ArrayList<>();
		for (Container c : containers) {
			if (c instanceof OpenTop) {
				OTContainers.add(c);
			}
		}
		double totalWeight = countTotalContainerWeight(OTContainers);

		while (true) {
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Total Weight of Open Top Containers: " + totalWeight));
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
				break;
			}
		}
	}

	private static double countTotalContainerWeight(ArrayList<Container> containers){
		int totalWeight = 0;
		for (Container con : containers){
			totalWeight += con.getWeight();
		}
		return  totalWeight;
	}
}
