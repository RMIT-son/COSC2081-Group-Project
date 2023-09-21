package main.Users;

import main.container.Container;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class User {
	protected String username;
	protected String password;

	private final String FILENAME = "resources/user.obj";

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

	//CRUD

	//Create
	public void createContainer(User user){
		List<User> users = readUser();
		users.add(user);
		saveUser(users);
	}
	// save
	public void saveUser(Collection<User> users) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
			oos.writeObject(users);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Read
	public List<User> readUser() {
		try {
			FileInputStream fileIn = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			List<User> users = (List<User>) in.readObject();
			in.close();
			fileIn.close();
			return users;
		} catch (IOException i) {
			return new ArrayList<>();
		} catch (ClassNotFoundException c) {
			System.out.println("User class not found");
			return new ArrayList<>();
		}
	}

	// Update
	public void updateUser(User updatedUser) {
		List<User> users = readUser();
		for (int i = 0; i < users.size(); i++) {
			if (Objects.equals(users.get(i).getUsername(), updatedUser.getUsername())) {
				users.set(i, updatedUser);
				break;
			}
		}
		saveUser(users);
	}


	//Delete
	public void deleteUser(User deletedUser) {
		List<User> users = readUser();
		users.removeIf(user -> Objects.equals(user.getUsername(), deletedUser.getUsername()));
		saveUser(users);
	}

}

