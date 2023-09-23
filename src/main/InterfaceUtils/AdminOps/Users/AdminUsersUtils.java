package main.InterfaceUtils.AdminOps.Users;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.*;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.Edit;
import main.InterfaceUtils.NotFoundException;
import main.InterfaceUtils.displayUtils;
import main.Users.User;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static main.InterfaceUtils.AdminOps.Users.CreateUsers.*;
import static main.Users.User.readUser;
import static org.fusesource.jansi.Ansi.ansi;


public class AdminUsersUtils {
	public static void view() throws IOException {
		boolean viewMenuSwitch = true;
		ArrayList<User> viewUsersList = (ArrayList<User>) readUser();
		while (viewMenuSwitch) {
			try {
				// View User Menu Setup
				displayUtils.displayUsers(viewUsersList);
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
	public static void create() throws IOException {
		try {
			// Create Users Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Create User"));
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createListPrompt()
					.name("Type")
					.message("Select User Type:")
					.newItem("Admin").text("Admin").add()
					.newItem("Port Manager").text("Port Manager").add()
					.newItem("Back").text("Back").add()
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			ListResult typeResult = (ListResult) result.get("Type");

			// Create Users Menu Switch
			switch (typeResult.getSelectedId()) {
				case "Admin":
					Admin();
					break;
				case "Port Manager":
					PM();
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
		ArrayList<User> users = (ArrayList<User>) readUser();
		try {
			// Edit User Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Edit User"));
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			displayUtils.displayUsers(users);
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("UsersSelect")
					.message("Enter the User's Username you would like Edit:")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult usersInput = (InputResult) result.get("UsersSelect");
			String selectedUsername = usersInput.getInput().trim();
			User selectedUser = null;

			// Find User
			for (User user : users) {
				if (user.getUsername().equalsIgnoreCase(selectedUsername)) {
					selectedUser = user;
					Edit.editUser(selectedUser);
					break;
				}
			}
			if (selectedUser == null) {
				System.out.println(ansi().fg(Ansi.Color.RED).render("User not found"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a non-null input."));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void delete() {
		ArrayList<User> users = (ArrayList<User>) readUser();
		try {
			// Delete Port Menu Setup
			System.out.println(ansi().fg(Ansi.Color.RED).render("Delete User"));
			System.out.println(ansi().fg(Ansi.Color.RED).render("Current Users: "));
			displayUtils.displayUsers(users);
			System.out.println(ansi().fg(Ansi.Color.YELLOW).render("Step 1 of 2"));
			ConsolePrompt prompt = new ConsolePrompt();
			PromptBuilder promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createInputPrompt()
					.name("UsersSelect")
					.message("Enter the User Name you would like Delete:")
					.addPrompt();

			// Initialize Variables
			HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
			InputResult usersInput = (InputResult) result.get("UsersSelect");
			String selectedUsername = usersInput.getInput().trim();
			User selectedUser = null;

			// Find Port
			for (User user : users) {
				if (user.getUsername().equalsIgnoreCase(selectedUsername)) {
					selectedUser = user;
					Edit.editUser(selectedUser);
					break;
				}
			}
			if (selectedUser == null) {
				throw new NotFoundException();
			}

			// Delete Port Confirmation
			System.out.println(ansi().fg(Ansi.Color.GREEN).render("Step 2 of 2"));
			prompt = new ConsolePrompt();
			promptBuilder = prompt.getPromptBuilder();
			promptBuilder.createConfirmPromp()
					.name("Delete")
					.message("Are you sure you want to delete this User?")
					.defaultValue(ConfirmChoice.ConfirmationValue.YES)
					.addPrompt();
			result = prompt.prompt(promptBuilder.build());
			ConfirmResult confirmResult = (ConfirmResult) result.get("Delete");

			// Delete Port
			if (confirmResult.getConfirmed() == ConfirmChoice.ConfirmationValue.YES) {
				assert selectedUser != null;
				selectedUser.deleteUser();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).render("Invalid input. Please enter a valid input."));
		} catch (NullPointerException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("Invalid input. Please enter a non-null input."));
		} catch (NotFoundException e) {
			System.out.println(ansi().fg(Ansi.Color.RED).bold().render("User not found."));
		} finally {
			try {
				TerminalFactory.get().restore();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
