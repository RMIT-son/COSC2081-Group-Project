package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class User {
	protected String username;
	protected String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean authenticate(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}

	public abstract void displayMenu();
}

class SystemAdmin extends User {

	public SystemAdmin() {
		super("admin", "admin123"); // pre-defined username and password for the system admin
	}

	@Override
	public void displayMenu() {
		System.out.println("System Admin Menu:");
		System.out.println("1. Access all port data");
		System.out.println("2. Manage users");
		// ... Add other options here
	}
}

class PortManager extends User {
	private String portName;

	public PortManager(String portName) {
		super(portName + "_manager", portName + "_pass"); // for simplicity, username and password are derived from port name
		this.portName = portName;
	}

	@Override
	public void displayMenu() {
		System.out.println("Port Manager Menu for " + portName + ":");
		System.out.println("1. Access port data");
		System.out.println("2. Manage port containers");
		// ... Add other options here
	}
}
