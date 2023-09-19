package main.Users;

import main.porttrip.Port;

public class PortManager extends User {
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
