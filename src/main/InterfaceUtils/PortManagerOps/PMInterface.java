package main.InterfaceUtils.PortManagerOps;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;

import main.InterfaceUtils.Edit;
import main.InterfaceUtils.PortManagerOps.Containers.PMContainersUtils;
import main.InterfaceUtils.PortManagerOps.Port.PMPortUtils;
import main.InterfaceUtils.PortManagerOps.Stat.PMStatUtils;
import main.InterfaceUtils.PortManagerOps.Trips.PMTripsUtils;
import main.InterfaceUtils.PortManagerOps.Vehicles.PMVehiclesUtils;
import main.InterfaceUtils.displayUtils;
import main.Users.PortManager;
import main.container.Container;
import main.porttrip.Port;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static main.InterfaceUtils.Interface.currentUser;
import static org.fusesource.jansi.Ansi.ansi;

public class PMInterface {
    static PortManager currentPortMana = (PortManager) currentUser;
    public static Port portManaging = currentPortMana.getPortManaging();
    public static boolean portMenuState;
    public static boolean vehiclesMenuState;
    public static boolean containersMenuState;
    public static boolean tripsMenuState;
    public static boolean statMenuState;


    public static void portOPS() {
        portMenuState = true;
        while (portMenuState) {
            try {
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Port CRUD Manager Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("PortOptions")
                        .message("Which action would you like to do?")
                        .newItem("View").text("View Port Details").add()
                        .newItem("Edit").text("Edit Port Details").add()
                        .newItem("Load").text("Load Container to Port").add()
                        .newItem("Unload").text("Unload Container from Port").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult POResult = (ListResult) result.get("PortOptions");
                switch (POResult.getSelectedId()) {
                    case "View":
                        System.out.println("View Port Details has been chosen");
                        displayUtils.displayPorts(List.of(portManaging));
                        break;
                    case "Edit":
                        System.out.println("Edit Port Details has been chosen");
                        Edit.editPort(portManaging);
                        break;
                    case "Load":
                        System.out.println("Load Container to Port has been chosen");
                        PMPortUtils.loadMenu();
                        break;
                    case "Unload":
                        System.out.println("Unload Container from Port has been chosen");
                        PMPortUtils.unloadMenu();
                        break;
                    case "Back":
                        System.out.println(ansi().render( "Returning to Admin Main Menu..."));
                        portMenuState = false;
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
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Vehicles CRUD Manager Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("VehicleOptions")
                        .message("Which action would you like to do?")
                        .newItem("View").text("View Vehicles in Port").add()
                        .newItem("Edit").text("Edit Vehicles").add()
                        .newItem("Load").text("Load Container to Vehicle").add()
                        .newItem("Unload").text("Unload Container from Vehicle").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult VResult = (ListResult) result.get("VehicleOptions");
                switch (VResult.getSelectedId()) {
                    case "View":
                        System.out.println("View Vehicles in Port has been chosen");
                        ArrayList<Vehicle> vehiclesInPort = (ArrayList<Vehicle>) portManaging.getVehicles();
                        System.out.println(ansi().eraseScreen().fg(Ansi.Color.CYAN).render("Current Vehicles in Port:"));
                        displayUtils.displayVehicles(vehiclesInPort);
                        break;
                    case "Edit":
                        System.out.println("Edit Vehicles has been chosen");
                        PMVehiclesUtils.edit();
                        break;
                    case "Load":
                        System.out.println("Load Container to Vehicle has been chosen");
                        PMVehiclesUtils.loadMenu();
                        break;
                    case "Unload":
                        System.out.println("Unload Container from Vehicle has been chosen");
                        PMVehiclesUtils.unloadMenu();
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
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Containers CRUD Manager Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("ContainerOptions")
                        .message("Which action would you like to do?")
                        .newItem("View").text("View Containers in Port").add()
                        .newItem("Create").text("Create New Container").add()
                        .newItem("Edit").text("Edit Containers").add()
                        .newItem("Delete").text("Delete a Container").add()
                        .newItem("LoadVehicle").text("Load Container to Vehicle").add()
                        .newItem("UnloadVehicle").text("Unload Container from Vehicle").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult CResult = (ListResult) result.get("ContainerOptions");
                switch (CResult.getSelectedId()) {
                    case "View":
                        System.out.println("View Containers in Port has been chosen");
                        ArrayList<Container> containersInPort = (ArrayList<Container>) portManaging.getContainers();
                        System.out.println(ansi().eraseScreen().fg(Ansi.Color.CYAN).render("Current Containers in Port:"));
                        displayUtils.displayContainers(containersInPort);
                        break;
                    case "Create":
                        System.out.println("Create a New Container has been chosen");
                        PMContainersUtils.create();
                        break;
                    case "Edit":
                        System.out.println("Edit Containers has been chosen");
                        PMContainersUtils.edit();
                        break;
                    case "Delete":
                        System.out.println("Delete a Container has been chosen");
                        PMContainersUtils.delete();
                        break;
                    case "LoadVehicle":
                        System.out.println("Load Container to Vehicle has been chosen");
                        PMContainersUtils.loadToVehicle();
                        break;
                    case "UnloadVehicle":
                        System.out.println("Unload Container from Vehicle has been chosen");
                        PMContainersUtils.loadToPort();
                        break;
                    case "Back":
                        System.out.println(ansi().render( "Returning to Manager Main Menu..."));
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
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Trips Manager Menu"));
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
                        PMTripsUtils.view();
                        break;
                    case "Schedule":
                        System.out.println("Schedule a new Trip has been chosen");
                        PMTripsUtils.schedule();
                        break;
                    case "Back":
                        System.out.println(ansi().render( "Returning to Manager Main Menu..."));
                        tripsMenuState = false;
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
                System.out.println(ansi().fg(Ansi.Color.BLUE).render("Statistics Manager Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("StatOptions")
                        .message("Which would you like to do?")
                        .newItem("CalcFuel").text("Calculate how much fuel has been used in a day").add()
                        .newItem("CalcWeight").text("Calculate how much weight of each type of container").add()
                        .newItem("CalcDistance").text("Calculate how much distance has been traveled in a day").add()
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
                        PMStatUtils.calcFuel();
                        break;
                    case "CalcWeight":
                        System.out.println("Calculate Weight has been chosen");
                        PMStatUtils.calcWeight();
                        break;
                    case "CalcDistance":
                        System.out.println("Calculate Distance has been chosen");
                        PMStatUtils.calcDistance();
                        break;
                    case "ListShips":
                        System.out.println("List Ships has been chosen");
                        PMStatUtils.listShips();
                        break;
                    case "ListTrip1Day":
                        System.out.println("List Trips in a day has been chosen");
                        PMStatUtils.listTrips1Day();
                        break;
                    case "ListTripMulti":
                        System.out.println("List Trips in a set time has been chosen");
                        PMStatUtils.listTripsMulti();
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