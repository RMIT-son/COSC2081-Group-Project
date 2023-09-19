package test;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class uiTest {
	public static void main(String[] args) throws InterruptedException {
		AnsiConsole.systemInstall();                                      // #1
		System.out.println(ansi().eraseScreen().render("Simple list example:"));
		boolean state = true;
		// #3
		while (state) {
			try {
				ConsolePrompt prompt = new ConsolePrompt();                     // #2
				PromptBuilder promptBuilder1 = prompt.getPromptBuilder();

				promptBuilder1.createListPrompt()                                // #4
						.name("Options")
						.message("Choose an option?")
						.newItem("1").text("Option 1").add()  // without name (name defaults to text)
						.newItem("2").text("Option 2").add()
						.newItem("3").text("Option 3").add()
						.newItem("4").text("Option 4").add()
						.newItem("exit").text("Exit").add()
						.addPrompt();
				HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder1.build()); // #5
				System.out.println("result = " + result);
				ListResult op1Result = (ListResult) result.get("Option 1");
				switch (op1Result.getSelectedId()) {
					case "1":
						System.out.println("Option 1 has been chosen");
						break;
					case "2":
						System.out.println("Option 2 has been chosen");
						break;
					case "3":
						System.out.println("Option 3 has been chosen");
						break;
					case "4":
						System.out.println("Option 4 has been chosen");
						break;
					case "exit":
						System.out.println("Exiting");
						state = false;
						break;
				}
//			System.out.println("result2 = " + result2);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try {
					TerminalFactory.get().restore();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
