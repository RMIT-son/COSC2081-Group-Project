package main.InterfaceUtils.AdminOps;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.AdminOps.Containers.AdminContainersUtils;
import main.InterfaceUtils.AdminOps.Ports.AdminPortUtils;
import main.InterfaceUtils.AdminOps.Trips.AdminTripsUtils;
import main.InterfaceUtils.AdminOps.Users.AdminUsersUtils;
import main.InterfaceUtils.AdminOps.Vehicles.AdminVehiclesUtils;
import main.InterfaceUtils.AdminOps.Stats.AdminStatUtils;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class AdminInterface {
    public static boolean portsMenuState;
    public static boolean vehiclesMenuState;
    public static boolean containersMenuState;
    public static boolean tripsMenuState;
    public static boolean usersMenuState;
    public static boolean statMenuState;
    public static void portsOPS() {
        portsMenuState = true;
        while (portsMenuState) {
	        try {
                System.out.println(ansi().fg(Ansi.Color.RED).render("Ports CRUD Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("PortOptions")
                        .message("Which action would you like to do?")
                        .newItem("View").text("View Ports").add()
                        .newItem("Create").text("Create New Port").add()
                        .newItem("Edit").text("Edit a Port").add()
                        .newItem("Delete").text("Delete a Port").add()
                        .newItem("Load Container").text("Load Containers").add()
                        .newItem("Unload Container").text("Unload Containers").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult POResult = (ListResult) result.get("PortOptions");
                switch (POResult.getSelectedId()) {
                    case "View":
                        System.out.println("View Ports has been chosen");
                        AdminPortUtils.view();
                        break;
                    case "Create":
                        System.out.println("Create a New Port has been chosen");
                        AdminPortUtils.create();
                        break;
                    case "Edit":
                        System.out.println("Edit a Port has been chosen");
                        AdminPortUtils.editChoose();
                        break;
                    case "Delete":
                        System.out.println("Delete a Port has been chosen");
                        AdminPortUtils.delete();
                        break;
                    case "Load Container":
                        System.out.println("Load Containers has been chosen");
                        AdminPortUtils.loadContainerMenu();
                        break;
                    case "Unload Container":
                        System.out.println("Unload Containers has been chosen");
                        AdminPortUtils.unloadContainerMenu();
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
        vehiclesMenuState = true;
        while (vehiclesMenuState) {
            try {
                System.out.println(ansi().fg(Ansi.Color.RED).render("Vehicles CRUD Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("VehicleOptions")
                        .message("Which action would you like to do?")
                        .newItem("View").text("View Vehicles").add()
                        .newItem("Create").text("Create New Vehicle").add()
                        .newItem("Edit").text("Edit Vehicles").add()
                        .newItem("Delete").text("Delete a Vehicle").add()
                        .newItem("Move").text("Move a Vehicle").add()
                        .newItem("Load").text("Load a Vehicle").add()
                        .newItem("Unload").text("Unload a Vehicle").add()
                        .newItem("Refuel").text("Refuel a Vehicle").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult VResult = (ListResult) result.get("VehicleOptions");
                switch (VResult.getSelectedId()) {
                    case "View":
                        System.out.println("View Vehicles has been chosen");
                        AdminVehiclesUtils.view();
                        break;
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
                    case "Move":
                        System.out.println("Move a Vehicle has been chosen");
                        AdminVehiclesUtils.move();
                        break;
                    case "Load":
                        System.out.println("Load a Vehicle has been chosen");
                        AdminVehiclesUtils.loadMenu();
                        break;
                    case "Unload":
                        System.out.println("Unload a Vehicle has been chosen");
                        AdminVehiclesUtils.unloadMenu();
                        break;
                    case "Refuel":
                        System.out.println("Refuel a Vehicle has been chosen");
                        AdminVehiclesUtils.refuel();
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
        containersMenuState = true;
        while (containersMenuState) {
            try {
                System.out.println(ansi().fg(Ansi.Color.RED).render("Containers CRUD Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("ContainerOptions")
                        .message("Which action would you like to do?")
                        .newItem("View").text("View Containers").add()
                        .newItem("Create").text("Create New Container").add()
                        .newItem("Edit").text("Edit Containers").add()
                        .newItem("Delete").text("Delete a Container").add()
                        .newItem("VehicleLoad").text("Load a Container to a Vehicle").add()
                        .newItem("PortLoad").text("Load a Container to a Port").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult CResult = (ListResult) result.get("ContainerOptions");
                switch (CResult.getSelectedId()) {
                    case "View":
                        System.out.println("View Containers has been chosen");
                        AdminContainersUtils.view();
                        break;
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
                    case "VehicleLoad":
                        System.out.println("Load a Container to a Vehicle has been chosen");
                        AdminContainersUtils.loadToVehicle();
                        break;
                    case "PortLoad":
                        System.out.println("Load a Container to a Port has been chosen");
                        AdminContainersUtils.loadToPort();
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

    public static void tripsOPS() {
        tripsMenuState = true;
        while (tripsMenuState) {
            try {
                System.out.println(ansi().fg(Ansi.Color.RED).render("Trips Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("TripsOptions")
                        .message("Which action would you like to do?")
                        .newItem("View").text("View Trips").add()
                        .newItem("Schedule").text("Schedule New Trip").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult TResult = (ListResult) result.get("TripsOptions");
                switch (TResult.getSelectedId()) {
                    case "View":
                        System.out.println("View Trips has been chosen");
                        AdminTripsUtils.view();
                        break;
                    case "Schedule":
                        System.out.println("Schedule a new Trip has been chosen");
                        AdminTripsUtils.schedule();
                        break;
                    case "Back":
                        System.out.println(ansi().render( "Returning to Admin Main Menu..."));
                        tripsMenuState = false;
                        break;
                }
                break;
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
        usersMenuState = true;
        while (usersMenuState) {
            try {
                System.out.println(ansi().fg(Ansi.Color.RED).render("Users CRUD Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("UsersOptions")
                        .message("Which action would you like to do?")
                        .newItem("View").text("View Users").add()
                        .newItem("Create").text("Create New User").add()
                        .newItem("Edit").text("Edit Users").add()
                        .newItem("Delete").text("Delete a User").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult UResult = (ListResult) result.get("UsersOptions");
                switch (UResult.getSelectedId()) {
                    case "View":
                        System.out.println("View Users has been chosen");
                        AdminUsersUtils.view();
                        break;
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
                        usersMenuState = false;
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
        statMenuState = true;
        while (statMenuState) {
            try {
                System.out.println(ansi().fg(Ansi.Color.RED).render("Statistics Admin Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("StatOptions")
                        .message("Which would you like to do?")
                        .newItem("CalcFuel").text("Calculate how much fuel has been used in a day").add()
                        .newItem("CalcWeight").text("Calculate how much weight of each type of container").add()
                        .newItem("CalcDistance").text("Calculate how much distance between 2 ports").add()
                        .newItem("ListShips").text("See all Ships in a Port").add()
                        .newItem("ListTrip1Day").text("See all Trips in a day").add()
                        .newItem("ListTripMulti").text("See all Trips in a set time").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult SResult = (ListResult) result.get("StatOptions");
                switch (SResult.getSelectedId()) {
                    case "CalcFuel":
                        System.out.println("Calculate Fuel has been chosen");
                        AdminStatUtils.calcFuel();
                        break;
                    case "CalcWeight":
                        System.out.println("Calculate Weight has been chosen");
                        AdminStatUtils.calcWeight();
                        break;
                    case "CalcDistance":
                        System.out.println("Calculate Distance has been chosen");
                        AdminStatUtils.calcDistance();
                        break;
                    case "ListShips":
                        System.out.println("List Ships has been chosen");
                        AdminStatUtils.listShips();
                        break;
                    case "ListTrip1Day":
                        System.out.println("List Trips in a day has been chosen");
                        AdminStatUtils.listTrips1Day();
                        break;
                    case "ListTripMulti":
                        System.out.println("List Trips in a set time has been chosen");
                        AdminStatUtils.listTripsMulti();
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