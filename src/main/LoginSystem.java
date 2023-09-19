package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginSystem {
    private static Map<String, User> users = new HashMap<>();

    static {
        users.put("admin", new SystemAdmin());
        users.put("port1_manager", new PortManager("port1"));
        users.put("port2_manager", new PortManager("port2"));
        // ... Add more port managers if needed
    }

    public static boolean authenticate(User user, String username, String password) {
        return user != null && user.username.equals(username) && user.password.equals(password);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (authenticate(user, username, password)) {
            user.displayMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
        scanner.close();
    }
}