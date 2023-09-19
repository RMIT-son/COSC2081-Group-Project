package main.container;

import main.vehicle.Vehicle;

import java.io.Serializable;

public class Container implements Serializable {
	protected int cNumber;
	protected double weight;
	protected double requiredFuel;

	public Container() {}

	public Container(int cNumber, double weight, double requiredFuel) {
		this.cNumber = cNumber;
		this.weight = weight;
		this.requiredFuel = requiredFuel;
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
