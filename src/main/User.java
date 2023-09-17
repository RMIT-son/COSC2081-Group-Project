package main;

public abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public abstract void displayMenu();
}

class SystemAdmin extends User {

    public SystemAdmin() {
        super("admin", "admin123"); // pre-defined username and password for the system admin
    }

    @Override
    public void displayMenu() {
        SystemAdminInterface.show();
    }
}

class PortManager extends User {
    private String portName;

    public PortManager(String portName) {
        super(portName + "_manager", portName + "_pass"); // for simplicity
        this.portName = portName;
    }

    @Override
    public void displayMenu() {
        PortManagerInterface.show(portName);
    }
}