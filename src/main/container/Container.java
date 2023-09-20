package main.container;

import main.porttrip.Port;
import main.vehicle.Vehicle;

import java.io.Serializable;

public class Container implements Serializable {
	protected int cNumber;
	protected double weight;
	protected double requiredFuel;

	protected Vehicle currentVehicle;

	protected Port currentPort;

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
		return "Container{" +
				"cNumber=" + cNumber +
				", weight=" + weight +
				", requiredFuel=" + requiredFuel +
				'}';
	}

	public double calculateFuel(Vehicle vehicle, double distance) {
		return 0;
	}


}
