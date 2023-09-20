package main.container;

import main.porttrip.Port;
import main.vehicle.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Container implements Serializable {
	protected int cNumber;
	protected double weight;
	protected double requiredFuel;

	protected Vehicle currentVehicle;

	protected Port currentPort;
	private final String FILENAME = "resources/container.obj";

	public enum ContainerState{
		AtPort,
		AtVehicle,
		Neither,

	}

	protected  ContainerState state = ContainerState.Neither;

	public Container() {}

	public Container(int cNumber, double weight, double requiredFuel, Vehicle currentVehicle, Port currentPort, ContainerState state) {
		this.cNumber = cNumber;
		this.weight = weight;
		this.requiredFuel = requiredFuel;
		this.currentVehicle = currentVehicle;
		this.currentPort = currentPort;
		this.state = state;
	}

	public ContainerState getState() {
		return state;
	}

	public void setState(ContainerState state) {
		this.state = state;
	}


	public Vehicle getCurrentVehicle() {
		return currentVehicle;
	}




	public void setCurrentVehicle(Vehicle currentVehicle) {
		if(this.state == ContainerState.AtPort) {
			System.out.println("Error: Container is currently at a port.");
			return;
		}
		this.currentVehicle = currentVehicle;
		this.state = (currentVehicle == null) ? ContainerState.Neither : ContainerState.AtVehicle;
	}

	public Port getCurrentPort() {
		return currentPort;
	}

	public void setCurrentPort(Port currentPort) {
		if(this.state == ContainerState.AtVehicle){
			System.out.println("Error: Container is currently at a vehicle.");
			return ;
		}
		this.currentPort = currentPort;
		this.state = (currentPort == null) ? ContainerState.Neither : ContainerState.AtPort;
	}

	public int getCNumber() {
		return cNumber;
	}

	public void setCNumber(int cNumber) {
		this.cNumber = cNumber;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getRequiredFuel() {
		return requiredFuel;
	}

	public void setRequiredFuel(double requiredFuel) {
		this.requiredFuel = requiredFuel;
	}

	@Override
	public String toString() {
		return Integer.toString(cNumber);
	}

	public double calculateFuel(Vehicle vehicle, double distance) {
		return 0;
	}


	//CRUD

	//Create
	public void createContainer(Container container){
		List<Container> containers = readContainer();
		containers.add(container);
		saveContainer(containers);
	}
	// save
	public void saveContainer(Collection<Container> containers) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
			oos.writeObject(containers);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Read
	public List<Container> readContainer() {
		try {
			FileInputStream fileIn = new FileInputStream(FILENAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			List<Container> containers = (List<Container>) in.readObject();
			in.close();
			fileIn.close();
			return containers;
		} catch (IOException i) {
			return new ArrayList<>();
		} catch (ClassNotFoundException c) {
			System.out.println("Container class not found");
			return new ArrayList<>();
		}
	}

	// Update
	public void updateContainer(Container updatedContainer) {
		List<Container> containers = readContainer();
		for (int i = 0; i < containers.size(); i++) {
			if (Objects.equals(containers.get(i).getCNumber(), updatedContainer.getCNumber())) {
				containers.set(i, updatedContainer);
				break;
			}
		}
		saveContainer(containers);
	}


	//Delete
	public void deleteContainer(Container deletedContainer) {
		List<Container> containers = readContainer();
		containers.removeIf(container -> Objects.equals(container.getCNumber(), deletedContainer.getCNumber()));
		saveContainer(containers);
	}

}
