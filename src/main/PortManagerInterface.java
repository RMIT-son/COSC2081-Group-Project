package main;

public class PortManagerInterface {
    public static void show(String portName) {
        System.out.println("Port Manager Menu for " + portName + ":");
        System.out.println("1. Access port data");
        System.out.println("2. Manage port containers");
        // ... handle menu options here
    }
}