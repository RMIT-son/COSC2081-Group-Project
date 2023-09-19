package main;

import main.porttrip.Port;

public abstract class User {
	protected String username;
	protected String password;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean authenticate(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
}

class SystemAdmin extends User {

	public SystemAdmin() {
		super();
	}

	public SystemAdmin(String username, String password) {
		super(username, password);
	}
}

class PortManager extends User {
	private Port portManaging;

	public PortManager() {
	}

	public PortManager(String username, String password, Port portManaging) {
		super(username, password);
		this.portManaging = portManaging;
	}

	public Port getPortManaging() {
		return portManaging;
	}

	public void setPortManaging(Port portManaging) {
		this.portManaging = portManaging;
	}
}
