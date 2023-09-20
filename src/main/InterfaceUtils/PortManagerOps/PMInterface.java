package main.InterfaceUtils.PortManagerOps;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import main.InterfaceUtils.AdminOps.Containers.AdminContainersUtils;
import main.InterfaceUtils.PortManagerOps.Containers.PMContainersUtils;
import main.InterfaceUtils.PortManagerOps.Port.PMPortUtils;
import main.InterfaceUtils.PortManagerOps.Trips.PMTripsUtils;
import main.InterfaceUtils.PortManagerOps.Vehicles.PMVehiclesUtils;
import main.Users.PortManager;
import main.porttrip.Port;
import main.vehicle.Ship;
import main.vehicle.Truck;
import main.vehicle.Vehicle;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static main.InterfaceUtils.Interface.currentUser;
import static org.fusesource.jansi.Ansi.ansi;

public class PMInterface {
    static PortManager currentPortMana = (PortManager) currentUser;
    static Port portManaging = currentPortMana.getPortManaging();
    public static boolean portMenuState;
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
                        .newItem("Name").text("Edit Port Name").add()
                        .newItem("Coords").text("Edit Port Coordinates").add()
                        .newItem("Capacity").text("Edit Storing Capacity").add()
                        .newItem("Landing").text("Edit Landing Ability").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult POResult = (ListResult) result.get("PortOptions");
                switch (POResult.getSelectedId()) {
                    case "Name":
                        System.out.println("Edit Port Name has been chosen");
                        PMPortUtils.editName();
                        break;
                    case "Coords":
                        System.out.println("Edit Port Coords has been chosen");
                        PMPortUtils.editCoords();
                        break;
                    case "Capacity":
                        System.out.println("Edit Port Capacity has been chosen");
                        PMPortUtils.editCapacity();
                        break;
                    case "Landing":
                        System.out.println("Edit Port Landing Ability has been chosen");
                        PMPortUtils.editLanding();
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
        ArrayList<Vehicle> vehiclesInPort = (ArrayList<Vehicle>) portManaging.getVehicles();
        try {
            System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Vehicle CRUD Manager Menu"));
            System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Current Vehicles in Port:"));
            System.out.println(ansi().eraseScreen().fg(Ansi.Color.RED).render("Step 1 of 3"));
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();
            promptBuilder.createInputPrompt()
                    .name("VehiclesSelect")
                    .message("Enter the Vehicle ID you would like Edit")
                    .defaultValue("1")
                    .addPrompt();
            HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
            InputResult vehiclesInput = (InputResult) result.get("VehiclesSelect");
            int selectedVehicleId = Integer.parseInt(vehiclesInput.getInput().trim());

            // Check if the selectedVehicleId is within the valid range.
            if (selectedVehicleId >= 1 && selectedVehicleId <= vehiclesInPort.size()) {
                // Iterate through the list to find the selected vehicle.
                Vehicle selectedVehicle = null;
                for (Vehicle vehicle : vehiclesInPort) {
                    if (vehicle instanceof Truck && ((Truck) vehicle).getTNumber() == selectedVehicleId) {
                        selectedVehicle = vehicle;
                        PMVehiclesUtils.edit(selectedVehicle);
                        break;
                    } else if (vehicle instanceof Ship && ((Ship) vehicle).getSNumber() == selectedVehicleId) {
                        selectedVehicle = vehicle;
                        PMVehiclesUtils.edit(selectedVehicle);
                        break;
                    }
                }

                if (selectedVehicle != null) {
                    PMVehiclesUtils.edit(selectedVehicle);
                } else {
                    System.out.println("The selected vehicle no longer exists.");
                }
            } else {
                System.out.println("Invalid vehicle ID. Please enter a valid ID.");
            }
            // May have to add more code here for menu logic
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer ID.");
        } finally {
            try {
                TerminalFactory.get().restore();
            } catch (Exception e) {
                e.printStackTrace();
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
                        .newItem("Schedule").text("Schedule New Trip").add()
                        .newItem("Edit").text("Edit Trip History").add()
                        .newItem("Delete").text("Delete a Trip").add()
                        .newItem("Back").text("Back").add()
                        .addPrompt();
                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
                ListResult TResult = (ListResult) result.get("TripsOptions");
                switch (TResult.getSelectedId()) {
                    case "Schedule":
                        System.out.println("Schedule a new Trip has been chosen");
                        PMTripsUtils.schedule();
                        break;
                    case "Edit":
                        System.out.println("Edit Trips has been chosen");
                        PMTripsUtils.edit();
                        break;
                    case "Delete":
                        System.out.println("Delete a Trip has been chosen");
                        PMTripsUtils.delete();
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
        //TODO: implement display statistics and let users choose what to do
        statMenuState = true;
        while (statMenuState) {
            try {
                System.out.println(ansi().eraseScreen().fg(Ansi.Color.BLUE).render("Statistics Manager Menu"));
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();
                promptBuilder.createListPrompt()
                        .name("StatOptions")
                        .message("Which would you like to see?")
                        .newItem().text("Placeholder 1").add()
                        .newItem().text("Placeholder 2").add()
                        .newItem("Back").text("Back").add()
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
                    case "Back":
                        System.out.println(ansi().render( "Returning to Manager Main Menu..."));
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