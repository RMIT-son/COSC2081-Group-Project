package main;

import java.util.HashMap;
import java.util.Map;

public abstract class Container {
    protected int cNumber;
    protected double weight;

    public Container(int cNumber, double weight) {
        this.cNumber = cNumber;
        this.weight = weight;
    }

    public enum Vehicle {
        Ship, Truck
    }

    public abstract double calculateFuel(Vehicle vehicle, double distance);
}

// This is the example of using class Container.java
// public class Main {
//     public static void main(String[] args) {
//         DryStorage container = new DryStorage(1, 1000);
//         double distance = 10;
//         double fuelForShip = container.calculateFuel(Container.Vehicle.Ship, distance);
//         double fuelForTruck = container.calculateFuel(Container.Vehicle.Truck, distance);

//         System.out.println("Fuel required for Ship: " + fuelForShip + " gallons");
//         System.out.println("Fuel required for Truck: " + fuelForTruck + " gallons");
//     }
// }