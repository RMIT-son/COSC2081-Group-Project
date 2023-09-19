package main.InterfaceUtils.AdminOps;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.AdminOps.Containers.AdminContainersUtils;
import main.InterfaceUtils.AdminOps.Ports.AdminPortUtils;
import main.InterfaceUtils.AdminOps.Users.AdminUsersUtils;
import main.InterfaceUtils.AdminOps.Vehicles.AdminVehiclesUtils;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class AdminInterface {
    public static boolean portsMenuState = true;
    public static boolean vehiclesMenuState = true;
    public static boolean containersMenuState = true;
    public static boolean usersMenuState = true;
    public static boolean statMenuState = true;
    public static void portsOPS() {
        while (portsMenuState) {
            try {
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Ports CRUD Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("PortOptions")
                        .message("Which action would you like to do?")
                        .newItem("Create").text("Create New Port").add()
                        .newItem("Edit").text("Edit a Port").add()
                        .newItem("Delete").text("Delete a Port").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult POResult = (ListResult) result.get("PortOptions");
                switch (POResult.getSelectedId()) {
                    case "Create":
                        System.out.println("Create a New Port has been chosen");
                        AdminPortUtils.create();
                        break;
                    case "Edit":
                        System.out.println("Edit a Port has been chosen");
                        AdminPortUtils.edit();
                        break;
                    case "Delete":
                        System.out.println("Delete a Port has been chosen");
                        AdminPortUtils.delete();
                        break;
                    case "Back":
                        System.out.println(ansi().render( "Returning to Admin Main Menu..."));
                        portsMenuState = false;
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

    public static void vehiclesOPS() {
        while (vehiclesMenuState) {
            try {
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Vehicles CRUD Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("VehicleOptions")
                        .message("Which action would you like to do?")
                        .newItem("Create").text("Create New Vehicle").add()
                        .newItem("Edit").text("Edit Vehicles").add()
                        .newItem("Delete").text("Delete a Vehicle").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult VResult = (ListResult) result.get("PortOptions");
                switch (VResult.getSelectedId()) {
                    case "Create":
                        System.out.println("Create a New Vehicle has been chosen");
                        AdminVehiclesUtils.create();
                        break;
                    case "Edit":
                        System.out.println("Edit Vehicles has been chosen");
                        AdminVehiclesUtils.edit();
                        break;
                    case "Delete":
                        System.out.println("Delete a Vehicle has been chosen");
                        AdminVehiclesUtils.delete();
                        break;
                    case "Back":
                        System.out.println(ansi().render( "Returning to Admin Main Menu..."));
                        vehiclesMenuState = false;
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

    public static void containersOPS() {
        while (containersMenuState) {
            try {
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Containers CRUD Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("ContainerOptions")
                        .message("Which action would you like to do?")
                        .newItem("Create").text("Create New Container").add()
                        .newItem("Edit").text("Edit Containers").add()
                        .newItem("Delete").text("Delete a Container").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult CResult = (ListResult) result.get("ContainerOptions");
                switch (CResult.getSelectedId()) {
                    case "Create":
                        System.out.println("Create a New Container has been chosen");
                        AdminContainersUtils.create();
                        break;
                    case "Edit":
                        System.out.println("Edit Containers has been chosen");
                        AdminContainersUtils.edit();
                        break;
                    case "Delete":
                        System.out.println("Delete a Container has been chosen");
                        AdminContainersUtils.delete();
                        break;
                    case "Back":
                        System.out.println(ansi().render( "Returning to Admin Main Menu..."));
                        containersMenuState = false;
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

    public static void usersOPS() {
        while (usersMenuState) {
            try {
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Users CRUD Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("UsersOptions")
                        .message("Which action would you like to do?")
                        .newItem("Create").text("Create New User").add()
                        .newItem("Edit").text("Edit Users").add()
                        .newItem("Delete").text("Delete a User").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult UResult = (ListResult) result.get("UserOptions");
                switch (UResult.getSelectedId()) {
                    case "Create":
                        System.out.println("Create a New User has been chosen");
                        AdminUsersUtils.create();
                        break;
                    case "Edit":
                        System.out.println("Edit Users has been chosen");
                        AdminUsersUtils.edit();
                        break;
                    case "Delete":
                        System.out.println("Delete a User has been chosen");
                        AdminUsersUtils.delete();
                        break;
                    case "Back":
                        System.out.println(ansi().render( "Returning to Admin Main Menu..."));
                        vehiclesMenuState = false;
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

    public static void statOPS() {
        while (statMenuState) {
            try {
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Statistics Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("StatOptions")
                        .message("Which would you like to see?")
                        .newItem("").text("Placeholder 1").add()
                        .newItem("").text("Placeholder 2").add()
                        .newItem("").text("Placeholder 3").add()
                        .newItem("").text("Placeholder 4").add()
                        .newItem("").text("Placeholder 5").add()
                        .newItem("").text("Placeholder 6").add()
                        .newItem("").text("Placeholder 7").add()
                        .newItem("").text("Placeholder 8").add()
                        .newItem("").text("Placeholder 9").add()
                        .newItem("").text("Placeholder 10").add()
                        .newItem("").text("Placeholder 11").add()
                        .newItem("").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult SResult = (ListResult) result.get("StatOptions");
                switch (SResult.getSelectedId()) {
                    case "Placeholder 1":
                        System.out.println("Placeholder 1 has been chosen");
                        break;
                    case "Placeholder 2":
                        System.out.println("Placeholder 2 has been chosen");
                        break;
                    case "Placeholder 3":
                        System.out.println("Placeholder 3 has been chosen");
                        break;
                    case "Placeholder 4":
                        System.out.println("Placeholder 4 has been chosen");
                        break;
                    case "Placeholder 5":
                        System.out.println("Placeholder 5 has been chosen");
                        break;
                    case "Placeholder 6":
                        System.out.println("Placeholder 6 has been chosen");
                        break;
                    case "Placeholder 7":
                        System.out.println("Placeholder 7 has been chosen");
                        break;
                    case "Placeholder 8":
                        System.out.println("Placeholder 8 has been chosen");
                        break;
                    case "Placeholder 9":
                        System.out.println("Placeholder 9 has been chosen");
                        break;
                    case "Placeholder 10":
                        System.out.println("Placeholder 10 has been chosen");
                        break;
                    case "Placeholder 11":
                        System.out.println("Placeholder 11 has been chosen");
                        break;
                    case "Back":
                        System.out.println(ansi().render( "Returning to Admin Main Menu..."));
                        statMenuState = false;
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